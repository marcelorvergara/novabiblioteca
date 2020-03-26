package android.vergara.tafoda.frags


import android.graphics.Color
import android.os.Bundle
import android.vergara.tafoda.Model.Note
import android.vergara.tafoda.R
import android.vergara.tafoda.ViewModel.LivroViewModel
import android.vergara.tafoda.db.LivrosDBHelper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_edit.*

/**
 * A simple [Fragment] subclass.
 */
class EditFrag : Fragment(){

    lateinit var umLivroViewModel: LivroViewModel
    lateinit var livrosDBHelper : LivrosDBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        livrosDBHelper = LivrosDBHelper(this.context!!.applicationContext)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let{
            umLivroViewModel = ViewModelProviders.of(it).get(LivroViewModel::class.java)
        }
        edtTitulo.setText(umLivroViewModel.um_livro.title)
        edtDesc.setText(umLivroViewModel.um_livro.description)
        edtAutor.setText(umLivroViewModel.um_livro.autor)
        edtResumo.setText(umLivroViewModel.um_livro.resumo)
        edtPag.setText(umLivroViewModel.um_livro.paginas)


        if(edtTitulo.getText().toString() == "")
        {
            // se vazio
            Toast.makeText(this.context!!.applicationContext,"É necessário selecionar um livro em Detalhes",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.homeFrag)
            edtTitulo.setText("Nenhum livro selecionado")
        }else {
            // não está vazio
            //Toast.makeText(this.context!!.applicationContext,"Ok, livro selecionado com sucesso ${umLivroViewModel.um_livro.ind}",
            //    Toast.LENGTH_SHORT).show()
        }
        btnSalvar.setOnClickListener{
            val titulo = edtTitulo.text.toString()
            val descricao = edtDesc.text.toString()
            val autor = edtAutor.text.toString()
            val resumo = edtResumo.text.toString()
            val paginas = edtPag.text.toString()
            val indice = umLivroViewModel.um_livro.ind

            val edtBook = Note(titulo,descricao,autor,resumo,paginas,indice)
            //update tabela SQLite
            livrosDBHelper.updateLivro(edtBook)
            //update viewmodel
            //umLivroViewModel.notes(theNoteBook)
            findNavController().navigate(R.id.homeFrag)
        }

        btnDelete.setOnClickListener {
            val titulo = edtTitulo.text.toString()
            livrosDBHelper.deleteLivro(umLivroViewModel.um_livro.ind)
            Toast.makeText(this.context!!.applicationContext,"Livro $titulo excluído com sucesso!",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.homeFrag)
        }
    }


}
