package android.vergara.tafoda


import android.os.Bundle
import android.vergara.tafoda.Model.Note
import android.vergara.tafoda.ViewModel.LivroViewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //rotação
        if (savedInstanceState == null) {
            //ViewModel
            var livroViewModel = ViewModelProviders.of(this)[LivroViewModel::class.java]
            //livroViewModel.livro = notes()
            livroViewModel.um_livro = Note("", "", "", "", "", "")
        }

        botnNav.setupWithNavController(findNavController(R.id.navFrag))
    }


}
