package android.vergara.tafoda.frags


import android.app.Activity
import android.os.Bundle
import android.vergara.tafoda.MainAdapter
import android.vergara.tafoda.Model.Note
import android.vergara.tafoda.R
import android.vergara.tafoda.ViewModel.LivroViewModel
import android.vergara.tafoda.ViewModel.UserViewModel
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * A simple [Fragment] subclass.
 */
class HomeFrag : Fragment() {

    companion object {
        fun newInstance() = HomeFrag()
    }

      override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            var rootView = inflater.inflate(R.layout.fragment_home, container, false)

            var livro_recyclerview = rootView.findViewById(R.id.livro_recyclerview) as RecyclerView // Add this
            //livro_recyclerview.adapter = MainAdapter(notes()) { Note ->

            //usando viewmodel
            var livroViewModel: LivroViewModel? = null
            activity?.let{
                livroViewModel = ViewModelProviders.of(it)[LivroViewModel::class.java]
            }
            livro_recyclerview.adapter = MainAdapter(livroViewModel!!.livro) { Note ->
                val tit = Note.title
                val des = Note.description
                val aut = Note.autor
                val res = Note.resumo
                val pag = Note.paginas

            var um_livroViewModel: LivroViewModel? = null
            activity?.let{
                um_livroViewModel = ViewModelProviders.of(this)[LivroViewModel::class.java]
            }
                um_livroViewModel!!.um_livro = Note(tit,des,aut,res,pag)
                var autor = um_livroViewModel!!.um_livro.autor
                //var intt = Intent(context,ListaFrag::class.java)
                //var livro = Note(tit,des,aut,res,pag)
                //intt.putExtra("livro",livro)
                //startActivity(intt)

                Toast.makeText(this.context!!.applicationContext,"${autor} clicked",Toast.LENGTH_LONG).show()
                //Log.i(ContentValues.TAG, "${tit} clicked")

        }

        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        livro_recyclerview.layoutManager = layoutManager

        return rootView
    }

}
