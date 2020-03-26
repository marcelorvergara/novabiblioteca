package android.vergara.tafoda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.vergara.tafoda.Model.Note
import android.vergara.tafoda.ViewModel.LivroViewModel
import android.vergara.tafoda.frags.HomeFrag
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_login.*

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (savedInstanceState == null) {
            //rotação


            //ViewModel
            var livroViewModel = ViewModelProviders.of(this)[LivroViewModel::class.java]
            //livroViewModel.livro = notes()
            livroViewModel.um_livro = Note("", "", "", "", "", "")
        }

        botnNav.setupWithNavController(findNavController(R.id.navFrag))
    }




}
