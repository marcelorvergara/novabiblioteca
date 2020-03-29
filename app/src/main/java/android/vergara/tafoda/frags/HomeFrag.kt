package android.vergara.tafoda.frags


import android.os.Bundle
import android.util.Log
import android.vergara.tafoda.adapter.MainAdapter
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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.livro_row.view.*


class HomeFrag : Fragment() {

    lateinit var livroViewModel: LivroViewModel
    lateinit var livrosDBHelper : LivrosDBHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //inicializando o DB
        livrosDBHelper = LivrosDBHelper(this.context!!.applicationContext)
        //instanciando o view model de livros
        activity?.let{
            livroViewModel = ViewModelProviders.of(it)[LivroViewModel::class.java]
            //tabelas SQLite
            livroViewModel.livro = livrosDBHelper.readAllLivros()
        }
        //inicializando o RV
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        val livro_recyclerview = rootView.findViewById(R.id.livro_recyclerview) as RecyclerView // Add this

        livro_recyclerview.adapter = MainAdapter(
            livroViewModel.livro,
            this::act
        )
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        livro_recyclerview.layoutManager = layoutManager

        //inciniando o LiveData com o total de livros contidos no banco
        livroViewModel.total = livrosDBHelper.countLivros()

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("teste4", livroViewModel.total.toString())
        livroViewModel.total_livros.observe(viewLifecycleOwner, Observer {
            //marretando observer somente na primeira execução
            if(it == -1){
                txt2.setText(livrosDBHelper.countLivros().toString())
            }else {
                txt2.setText(it.toString())
                //Toast.makeText(this.context!!.applicationContext, "Com LiveData: ${it.toString()}", Toast.LENGTH_SHORT).show()
            }
        })
        Log.i("teste6", livroViewModel.total.toString())

    }


    fun act (note : Note) : Unit {
        livroViewModel.um_livro = note
        findNavController().navigate(R.id.action_homeFrag_to_listaFrag)
    }

    override fun onDestroy() {
        super.onDestroy()
        livrosDBHelper.close()
    }
}
