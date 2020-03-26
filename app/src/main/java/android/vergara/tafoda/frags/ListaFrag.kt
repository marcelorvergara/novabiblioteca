package android.vergara.tafoda.frags



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.vergara.tafoda.Model.Note
import android.vergara.tafoda.R
import android.vergara.tafoda.ViewModel.LivroViewModel
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewDebug
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_lista.*

/**
 * A simple [Fragment] subclass.
 */
class ListaFrag : Fragment() {

    lateinit var umLivroViewModel: LivroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let{
            umLivroViewModel = ViewModelProviders.of(it).get(LivroViewModel::class.java)
        }
        txtTitulo.setText(umLivroViewModel.um_livro.title)
        txtDesc.setText(umLivroViewModel.um_livro.description)
        txtAutor.setText(umLivroViewModel.um_livro.autor)
        txtResumo.setText(umLivroViewModel.um_livro.resumo)
        txtPag.setText(umLivroViewModel.um_livro.paginas)

        if(txtTitulo.getText().toString() == "")
        {
            // not null not empty
            Toast.makeText(this.context!!.applicationContext,"É necessário clicar em um livro na Home",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.homeFrag)
        }
        btnEditar.setOnClickListener{
            findNavController().navigate(R.id.editFrag)
        }


        //intent implícito Share livro
        btnShare.setOnClickListener {

            try {
                val destinos =
                    arrayOf("marcelo.vergara@al.infnet.edu.br")
                val shareTitulo = txtTitulo.text
                val shareDesc = txtDesc.text
                val shareAutor = txtAutor.text
                val shareResumo = txtResumo.text
                val sharePag = txtPag.text

                val shareIntent = Intent()
                shareIntent.type = "message/rfc822"
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(Intent.EXTRA_EMAIL, destinos)
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Veja esse Livro: $shareTitulo do $shareAutor")
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Descrição: $shareDesc \n\n $shareResumo\n\n Páginas:$sharePag")
                startActivity(Intent.createChooser(shareIntent, "Compartilhar via"))
            } catch (e: Exception) {
                Toast.makeText(this.context!!.applicationContext, "Cliente de e-mail não disponível", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
