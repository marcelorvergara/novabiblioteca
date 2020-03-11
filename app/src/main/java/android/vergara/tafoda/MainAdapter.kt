package android.vergara.tafoda

import android.vergara.tafoda.Model.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.livro_row.view.*

class MainAdapter(private val notes: List<Note>, val listener: (Note) -> Unit) : RecyclerView.Adapter<MainAdapter.CustomViewHolder>() {

    //number of item
    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        //create view
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.livro_row,parent, false)
        //val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val note = notes[position]
        holder.hdr_title.text = note.title
        holder.hdr_desc.text = note.description

        holder.hdr_title.setOnClickListener{
            listener(notes[position])
        }

    }

    class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

        val hdr_title= view.note_title
        val hdr_desc = view.note_desc
    }
}