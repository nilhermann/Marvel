package nilherman.funfacts.presentation.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import nilherman.funfacts.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

}




