package android.vergara.tafoda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.vergara.tafoda.Model.User
import android.vergara.tafoda.ViewModel.UserViewModel
import android.vergara.tafoda.db.LivrosDBHelper
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*

class MainActivity : AppCompatActivity() {

    lateinit var livrosDBHelper : LivrosDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var userViewModel = ViewModelProviders.of(this)[UserViewModel::class.java]
        //userViewModel.user = User("Marcelo","marcelo@globo.com")

    }

    override fun onResume() {
        super.onResume()
        //limpar a base para não carregar novamente quando o usuário voltar para a tela de login
        livrosDBHelper = LivrosDBHelper(this)
        livrosDBHelper.deleteAll()

    }

    override fun onDestroy() {
        super.onDestroy()
        livrosDBHelper.close()
    }
}
