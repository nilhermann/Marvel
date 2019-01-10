package nilherman.funfacts.presentation.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import nilherman.funfacts.R
import nilherman.funfacts.presentation.home.characters.CharactersFragment
import nilherman.funfacts.presentation.home.comics.ComicsFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val toolbar: Toolbar = (Toolbar) findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        navigateToFirstFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar, menu)
        return true
    }

    private fun navigateToFirstFragment() {
        val charactersFragment = CharactersFragment()
        openFragment(charactersFragment)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_search -> {
            true
        }else -> {
            super.onOptionsItemSelected(item)
        }
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




