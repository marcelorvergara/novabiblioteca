package android.vergara.tafoda.frags


import android.content.Intent
import android.os.Bundle
import android.vergara.tafoda.HomeActivity
import android.vergara.tafoda.Model.User
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.vergara.tafoda.R
import android.vergara.tafoda.ViewModel.UserViewModel
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_cadastro.*
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFrag : Fragment() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        btnNext.setOnClickListener{
//            var intt = Intent(this,HomeActivity::class.java)
//            startActivity(intt)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener{
            var intt = Intent(this.context!!.applicationContext,HomeActivity::class.java)
            startActivity(intt)
        }


        //var email = txtEmail.text.toString()

        var userViewModel: UserViewModel? = null
        activity?.let{
            userViewModel = ViewModelProviders.of(it)[UserViewModel::class.java]
        }

        var user = userViewModel!!.user?.nome
        if ( user != null && !user!!.isNullOrBlank()) {
        //Toast.makeText(this.context!!.applicationContext, "$user", Toast.LENGTH_SHORT ).show()
        //if ( user != null) {
            txtNome.setText("$user")
        }

        btnCadastro.setOnClickListener {
            var nome = txtNome.text.toString()
            var pass = txtPass.text.toString()
            userViewModel!!.user = User(nome,pass)
            findNavController().navigate(R.id.action_loginFrag_to_cadastroFrag)
        }
    }
}
