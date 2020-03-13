package android.vergara.tafoda.ViewModel

import android.vergara.tafoda.Model.Note
import androidx.lifecycle.ViewModel

class LivroViewModel: ViewModel() {
    lateinit var livro: List<Note>
    lateinit var um_livro : Note
}