package android.vergara.tafoda.frags



import android.os.Bundle
import android.util.Log
import android.vergara.tafoda.Model.Note
import android.vergara.tafoda.R
import android.vergara.tafoda.ViewModel.LivroViewModel
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_lista.*

/**
 * A simple [Fragment] subclass.
 */
class ListaFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var umLivroViewModel: LivroViewModel? = null
        activity?.let{
            umLivroViewModel = ViewModelProviders.of(it).get(LivroViewModel::class.java)
        }
        val titulo = umLivroViewModel!!.um_livro.title
        if (umLivroViewModel!!.um_livro != null)
            Log.i("LivroViewModel", "Livro não é nulo: ${umLivroViewModel!!.um_livro}")
        else
            Log.i("LivroViewModel", "Livro é nulo")
        //txtTitulo.setText(titulo)
//        txtDesc.setText(umLivroViewModel!!.um_livro.description)
//        txtAutor.setText(umLivroViewModel!!.um_livro.autor)
//        txtResumo.setText(umLivroViewModel!!.um_livro.resumo)
        //txtPagina.setText(umLivroViewModel!!.um_livro.paginas)

        Toast.makeText(this.context!!.applicationContext, "${titulo}",Toast.LENGTH_LONG).show()

    }
}
