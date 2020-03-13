package android.vergara.tafoda.frags


import android.os.Bundle
import android.vergara.tafoda.Model.Note
import android.vergara.tafoda.R
import android.vergara.tafoda.ViewModel.LivroViewModel
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_lista.*

/**
 * A simple [Fragment] subclass.
 */
class EditFrag : Fragment(){

    lateinit var umLivroViewModel: LivroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let{
            umLivroViewModel = ViewModelProviders.of(it).get(LivroViewModel::class.java)
        }
        edtTitulo.setText(umLivroViewModel.um_livro.title.toString())
        edtDesc.setText(umLivroViewModel.um_livro.description)
        edtAutor.setText(umLivroViewModel.um_livro.autor)
        edtResumo.setText(umLivroViewModel.um_livro.resumo)
        var pg: String = umLivroViewModel.um_livro.paginas.toString()
        edtPag.setText(pg)

        if(edtTitulo.getText().toString() == "")
        {
            // se vazio
            Toast.makeText(this.context!!.applicationContext,"É necessário selecionar um livro na Lista",
                Toast.LENGTH_LONG).show()
            edtTitulo.setText("Nenhum livro selecionado")
        }else {
            // não está vazio
            Toast.makeText(this.context!!.applicationContext,"Ok, livro selecionado com sucesso ${umLivroViewModel.um_livro.index}",
                Toast.LENGTH_SHORT).show()
        }
        btnSalvar.setOnClickListener{
            var titulo = edtTitulo.text.toString()
            var descricao = edtDesc.text.toString()
            var autor = edtAutor.text.toString()
            var resumo = edtResumo.text.toString()
            var paginas = edtPag.text.toString()
            var pg = paginas.toInt()
            var indice = umLivroViewModel.um_livro.index

            var theNoteBook = Note(titulo,descricao,autor,resumo,pg,indice)
            umLivroViewModel.notes(theNoteBook)
            findNavController().navigate(R.id.homeFrag)
        }
    }
}
