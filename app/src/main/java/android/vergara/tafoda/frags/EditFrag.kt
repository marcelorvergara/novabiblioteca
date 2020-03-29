package android.vergara.tafoda.frags


import android.content.DialogInterface
import android.graphics.Color
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.vergara.tafoda.Model.Note
import android.vergara.tafoda.R
import android.vergara.tafoda.ViewModel.LivroViewModel
import android.vergara.tafoda.db.LivrosDBHelper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_edit.txt2
import kotlinx.android.synthetic.main.fragment_home.*

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

    @RequiresApi(Build.VERSION_CODES.O)
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

        edtTitulo.setText(umLivroViewModel.um_livro.title)
        edtDesc.setText(umLivroViewModel.um_livro.description)
        edtAutor.setText(umLivroViewModel.um_livro.autor)
        edtResumo.setText(umLivroViewModel.um_livro.resumo)
        edtPag.setText(umLivroViewModel.um_livro.paginas)


        if(edtTitulo.getText().toString() == "")
        {
            // se vazio
            Toast.makeText(this.context!!.applicationContext,"É necessário selecionar um livro para Editar ou Deletar",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.homeFrag)
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
            val contFrag = activity!!.applicationContext
            val titulo = edtTitulo.text.toString()
            val dialogBuilder = AlertDialog.Builder(activity!!)
            dialogBuilder.setMessage("Tem certeza que gostaria de deletar $titulo ?")
                .setCancelable(false)
                //seguir em frente
                .setPositiveButton("Sim"){ _, which ->
                    // segue o baile
                    livrosDBHelper.deleteLivro(umLivroViewModel.um_livro.ind)
                    //update viewmodel para Livedata
                    umLivroViewModel.total = livrosDBHelper.countLivros()
                    findNavController().navigate(R.id.homeFrag)
                    Toast.makeText(contFrag,"Livro $titulo excluído com sucesso!",Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Não"){ _, which ->
                Toast.makeText(contFrag,"Livro não deletado",Toast.LENGTH_SHORT).show()
            }
            // Display a neutral button on alert dialog
            .setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(contFrag,"Operação cancelada",Toast.LENGTH_SHORT).show()
            }

            val alert = dialogBuilder.create()
            alert.setTitle("Deletar Livro")
            alert.show()
        }

        edtDesc.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
        edtResumo.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
    }

    override fun onDestroy() {
        super.onDestroy()
        livrosDBHelper.close()
    }


}
