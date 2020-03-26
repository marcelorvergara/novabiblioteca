package android.vergara.tafoda.frags


import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.vergara.tafoda.Model.User
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.vergara.tafoda.R
import android.vergara.tafoda.ViewModel.UserViewModel
import android.vergara.tafoda.db.UsersDBHelper
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_cadastro.*

/**
 * A simple [Fragment] subclass.
 */
class CadastroFrag : Fragment() {

    lateinit var usersDBHelper: UsersDBHelper
    var i = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        usersDBHelper = UsersDBHelper(this.context!!.applicationContext)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cadastro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var userViewModel: UserViewModel? = null
        activity?.let {
            userViewModel = ViewModelProviders.of(it)[UserViewModel::class.java]
        }
        edtNome.setText(userViewModel!!.user!!.nome)
        edtSenha.setText((userViewModel!!.user!!.pass))

        btnCadastrar.setOnClickListener {
            val apelido = edtId.text.toString()
            val nome = edtNome.text.toString()
            val pass = edtSenha.text.toString()
            val pass2 = edtSenha2.text.toString()
            //testar as duas senhas
            if (pass != pass2) {
                Toast.makeText(
                    this.context!!.applicationContext,
                    "As senhas não são iguais",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                var result =
                    usersDBHelper.insertUser(User(userid = apelido, nome = nome, pass = pass))
                userViewModel?.user = User("", edtNome.text.toString(), edtSenha.text.toString())
                //limpar os editText
                edtId.setText("")
                edtNome.setText("")
                edtSenha.setText("")
                findNavController().navigate(R.id.action_cadastroFrag_to_loginFrag)

            }
        }
        imgEye.setOnClickListener {
            //Toast.makeText(this.context!!.applicationContext,i.toString(),Toast.LENGTH_SHORT).show()
            if (i == 1) {
                i = 2
                edtSenha2.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                edtSenha.setTransformationMethod(HideReturnsTransformationMethod.getInstance())

            } else if (i == 2) {
                edtSenha2.setTransformationMethod(PasswordTransformationMethod.getInstance())
                edtSenha.setTransformationMethod(PasswordTransformationMethod.getInstance())
                i = 1
            }
        }
    }
}