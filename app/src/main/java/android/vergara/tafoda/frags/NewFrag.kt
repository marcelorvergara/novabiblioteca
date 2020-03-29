package android.vergara.tafoda.frags


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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_new.edtAutor
import kotlinx.android.synthetic.main.fragment_new.edtDesc
import kotlinx.android.synthetic.main.fragment_new.edtPag
import kotlinx.android.synthetic.main.fragment_new.edtResumo
import kotlinx.android.synthetic.main.fragment_new.edtTitulo
import kotlinx.android.synthetic.main.fragment_new.*
import kotlinx.android.synthetic.main.fragment_new.txt2
import kotlinx.android.synthetic.main.fragment_new.txtPagina

/**
 * A simple [Fragment] subclass.
 */
class NewFrag : Fragment(){

    lateinit var umLivroViewModel: LivroViewModel
    lateinit var livrosDBHelper : LivrosDBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        livrosDBHelper = LivrosDBHelper(this.context!!.applicationContext)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let{
            umLivroViewModel = ViewModelProviders.of(it).get(LivroViewModel::class.java)
        }
        umLivroViewModel.total_livros.observe(viewLifecycleOwner, Observer {
            //marretando observer somente na primeira execução
            if(it == -1){
                txt2.setText(livrosDBHelper.countLivros().toString())
            }else {
                txt2.setText(it.toString())
                //Toast.makeText(this.context!!.applicationContext, "Com LiveData: ${it.toString()}", Toast.LENGTH_SHORT).show()
            }
        })
        btnNovoLivro.setOnClickListener{
            //valores do novo livro meno o indice
            val titulo = edtTitulo.text.toString()
            val descricao = edtDesc.text.toString()
            val autor = edtAutor.text.toString()
            val resumo = edtResumo.text.toString()
            val paginas = txtPagina.text.toString()

            //tratando o indice
            val ind = livrosDBHelper.readIndices()?.plus(1)
            var indice = ind.toString()
            val edtBook = Note(titulo,descricao,autor,resumo,paginas,indice)

            //update tabela SQLite
            livrosDBHelper.insertLivro(edtBook)
            //update viewmodel para Livedata
            umLivroViewModel.total = ind
            findNavController().navigate(R.id.homeFrag)
        }
    }


}
