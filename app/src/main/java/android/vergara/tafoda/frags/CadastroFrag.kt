package android.vergara.tafoda.frags


import android.os.Bundle
import android.vergara.tafoda.Model.User
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.vergara.tafoda.R
import android.vergara.tafoda.ViewModel.UserViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_cadastro.*

/**
 * A simple [Fragment] subclass.
 */
class CadastroFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cadastro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var userViewModel: UserViewModel? = null
        activity?.let{
            userViewModel = ViewModelProviders.of(it)[UserViewModel::class.java]
        }
        edtNome.setText(userViewModel!!.user!!.nome)
        edtSenha.setText((userViewModel!!.user!!.pass))

        btnCadastrar.setOnClickListener{
            userViewModel?.user = User(edtNome.text.toString(),edtSenha.text.toString())
            findNavController().navigate(R.id.action_cadastroFrag_to_loginFrag)
        }
    }
}
