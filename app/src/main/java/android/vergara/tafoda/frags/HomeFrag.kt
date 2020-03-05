package android.vergara.tafoda.frags


import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.vergara.tafoda.MainAdapter
import android.vergara.tafoda.Note
import android.vergara.tafoda.R
import android.vergara.tafoda.SharedViewModel
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import java.nio.channels.Selector

/**
 * A simple [Fragment] subclass.
 */
class HomeFrag : Fragment() {

    companion object {
        fun newInstance() = HomeFrag()
    }

    private lateinit var model: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = activity?.run { ViewModelProviders.of(this).get(SharedViewModel::class.java) }
            ?: throw Exception("Invalid Activity")

        var tit: LiveData<Note> = model.getNotes()
        txttitulo.text = tit.toString()

    }

}
