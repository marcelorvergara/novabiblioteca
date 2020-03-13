package android.vergara.tafoda.frags



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
        txtTitulo.setText(umLivroViewModel.um_livro.title.toString())
        txtDesc.setText(umLivroViewModel.um_livro.description)
        txtAutor.setText(umLivroViewModel.um_livro.autor)
        txtResumo.setText(umLivroViewModel.um_livro.resumo)
        var pg: String = umLivroViewModel.um_livro.paginas.toString()
        txtPag.setText(" " + pg)

        if(txtTitulo.getText().toString() == "")
        {
            // not null not empty
            Toast.makeText(this.context!!.applicationContext,"É necessário clicar em um livro na Home",Toast.LENGTH_LONG).show()
            txtTitulo.setText("Nenhum livro selecionado")
        }else {
            //null or empty
            Toast.makeText(this.context!!.applicationContext,"Ok, livro selecionado com sucesso",Toast.LENGTH_SHORT).show()
        }
        btnEditar.setOnClickListener{
            findNavController().navigate(R.id.editFrag)
        }

        //  if (umLivroViewModel!!.um_livro != null)
        // Log.i("LivroViewModel", "Livro não é nulo porra: ${umLivroViewModel!!.um_livro.paginas}")
        // else
        // Log.i("LivroViewModel", "Livro é nulo")
    }
}
