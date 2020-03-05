package android.vergara.tafoda.frags



import android.content.Intent
import android.os.Bundle
import android.vergara.tafoda.Note
import android.vergara.tafoda.R
import android.vergara.tafoda.SharedViewModel
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_lista.*

/**
 * A simple [Fragment] subclass.
 */
class ListaFrag : Fragment() {

    private lateinit var model: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        model.selected.observe(viewLifecycleOwner, Observer {

            txtTitulo.text = it?.toString()
        })
    }
}
