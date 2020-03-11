package android.vergara.tafoda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.vergara.tafoda.Model.User
import android.vergara.tafoda.ViewModel.UserViewModel
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var userViewModel = ViewModelProviders.of(this)[UserViewModel::class.java]
        //userViewModel.user = User("Marcelo","marcelo@globo.com")

    }


}
