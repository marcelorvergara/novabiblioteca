package android.vergara.tafoda.frags


import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.vergara.tafoda.MainAdapter
import android.vergara.tafoda.Model.Note
import android.vergara.tafoda.R
import android.vergara.tafoda.ViewModel.LivroViewModel
import android.vergara.tafoda.ViewModel.UserViewModel
import android.vergara.tafoda.db.LivrosDBHelper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFrag : Fragment() {

    lateinit var livroViewModel: LivroViewModel
    lateinit var livrosDBHelper : LivrosDBHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        livrosDBHelper = LivrosDBHelper(this.context!!.applicationContext)
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        val livro_recyclerview = rootView.findViewById(R.id.livro_recyclerview) as RecyclerView // Add this

        activity?.let{
            livroViewModel = ViewModelProviders.of(it)[LivroViewModel::class.java]
            //tabelas SQLite
            livroViewModel.livro = livrosDBHelper.readAllLivros()
        }
        livro_recyclerview.adapter = MainAdapter(livroViewModel.livro, this::act)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        livro_recyclerview.layoutManager = layoutManager

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            livroViewModel = ViewModelProviders.of(this)[LivroViewModel::class.java]
        }
        Log.i("teste4", livroViewModel.total_livros.value.toString())
        livroViewModel.total_livros.observe(viewLifecycleOwner, Observer {
            //contador de livros
            txt2.text = it.toString()
            //txtRvTot.text = livrosDBHelper.countLivros().toString()
        })
        Log.i("teste5", livroViewModel.total.toString())
        txt2.text = livrosDBHelper.countLivros().toString()
    }

    fun act (note : Note) : Unit {
        activity?.let{
            livroViewModel = ViewModelProviders.of(it)[LivroViewModel::class.java]
            livroViewModel.um_livro = note
        }
        findNavController().navigate(R.id.action_homeFrag_to_listaFrag)
    }

}



