package android.vergara.tafoda.frags


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.vergara.tafoda.HomeActivity
import android.vergara.tafoda.Model.Note
import android.vergara.tafoda.Model.User
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.vergara.tafoda.R
import android.vergara.tafoda.ViewModel.LivroViewModel
import android.vergara.tafoda.ViewModel.UserViewModel
import android.vergara.tafoda.db.LivrosDBHelper
import android.vergara.tafoda.db.UsersDBHelper
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_cadastro.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlin.random.Random


class LoginFrag : Fragment() {

    lateinit var usersDBHelper : UsersDBHelper
    lateinit var livrosDBHelper : LivrosDBHelper
    lateinit var livroViewModel: LivroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        usersDBHelper = UsersDBHelper(this.context!!.applicationContext)
        livrosDBHelper = LivrosDBHelper(this.context!!.applicationContext)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var userViewModel: UserViewModel? = null
        activity?.let{
            userViewModel = ViewModelProviders.of(it)[UserViewModel::class.java]
        }
        activity?.let{
            livroViewModel = ViewModelProviders.of(it)[LivroViewModel::class.java]
        }

        //usuário teste
        val rnds = (0..10000).random()
        usersDBHelper.insertUser(User(userid = "Vergara$rnds", nome = "1", pass = "1"))

        val userModel = userViewModel!!.user?.nome
        val passModel = userViewModel!!.user?.pass
        if ( userModel != null && !userModel.isNullOrBlank()) {
        //Toast.makeText(this.context!!.applicationContext, "$user", Toast.LENGTH_SHORT ).show()
        //if ( user != null) {
            txtNome.setText("$userModel")
            txtPass.setText("$passModel")
        }

        btnCadastro.setOnClickListener {
            val nome = txtNome.text.toString()
            val pass = txtPass.text.toString()
            userViewModel!!.user = User("",nome,pass)
            findNavController().navigate(R.id.action_loginFrag_to_cadastroFrag)
        }
        btnNext.setOnClickListener {
            //Toast.makeText(this.context!!.applicationContext, "$userModel and $passModel - ${txtNome.text} and ${txtPass.text}", Toast.LENGTH_LONG ).show()

            val pass = usersDBHelper.readPass(txtNome.text.toString())
            if (pass != txtPass.text.toString()) {
                Toast.makeText(
                    this.context!!.applicationContext,
                    "Password Inválido ou Login não Cadastrado",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val note : List<Note> = livroViewModel.notes()
                for (note in note) {
                    livrosDBHelper.insertLivro(Note(title = note.title,description = note.description,autor = note.autor,resumo = note.resumo,paginas = note.paginas,ind = note.ind))
                }
                val totLivros = livrosDBHelper.countLivros()
                livroViewModel.total = totLivros
                Log.i("teste3","DataB ${livrosDBHelper.countLivros().toString()}")
                Log.i("teste5","ViewM ${livroViewModel.total.toString()}")
                val intt = Intent(this.context!!.applicationContext, HomeActivity::class.java)
                startActivity(intt)
            }
        }
    }
}

