package android.vergara.tafoda


import android.os.Bundle
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

        //rotação
        if (savedInstanceState == null) {
            //inicializando o banco de dados
            //livrosDBHelper = LivrosDBHelper(this)
            // Inicializando ViewModel
            livroViewModel = ViewModelProviders.of(this)[LivroViewModel::class.java]
            //adicionando o total de livros no live data
            //livroViewModel.total = livrosDBHelper.countLivros()


            //Garantindo pelo menos um livro para não dar erro se o usuário não selecionar um livro no RV
            livroViewModel.um_livro = Note("", "", "", "", "", "")
        }

        botnNav.setupWithNavController(findNavController(R.id.navFrag))
    }


}
