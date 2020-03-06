package android.vergara.tafoda.frags


import android.content.Intent
import android.os.Bundle
import android.vergara.tafoda.HomeActivity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.vergara.tafoda.R
import androidx.navigation.fragment.findNavController
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

        btnCadastro.setOnClickListener {
            findNavController().navigate(R.id.action_loginFrag_to_cadastroFrag)

        }
    }
}
