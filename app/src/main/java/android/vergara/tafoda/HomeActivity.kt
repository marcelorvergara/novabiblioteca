package android.vergara.tafoda


import android.os.Bundle
import android.util.Log
import android.vergara.tafoda.Model.Note
import android.vergara.tafoda.ViewModel.LivroViewModel
import android.vergara.tafoda.db.LivrosDBHelper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    lateinit var livroViewModel : LivroViewModel
    lateinit var livrosDBHelper : LivrosDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //inicializando o banco de dados
        livrosDBHelper = LivrosDBHelper(this)

        //rotação
        if (savedInstanceState == null) {
            // Inicializando ViewModel
            livroViewModel = ViewModelProviders.of(this)[LivroViewModel::class.java]
            //adicionando o total de livros no live data
            livroViewModel.total = livrosDBHelper.countLivros()
            Log.i("Teste7",livroViewModel.total.toString())

            //livroViewModel.livro = notes()
            livroViewModel.um_livro = Note("", "", "", "", "", "")
        }

        botnNav.setupWithNavController(findNavController(R.id.navFrag))
    }


}
