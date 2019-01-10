package nilherman.funfacts.presentation.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import nilherman.funfacts.R


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        navigateToFirstFragment()
    }

    private fun navigateToFirstFragment() {
        val charactersFragment = CharactersFragment()
        openFragment(charactersFragment)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        item -> when (item.itemId) {
            R.id.navigation_characters -> {
                val charactersFragment = CharactersFragment()
                openFragment(charactersFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_comics -> {
                val comicsFragment = ComicsFragment()
                openFragment(comicsFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}




