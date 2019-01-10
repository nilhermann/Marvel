package nilherman.funfacts.presentation

import android.support.v4.app.Fragment
import nilherman.funfacts.R
import nilherman.funfacts.presentation.detail.DetailsFragment

open class BaseFragment : Fragment() {

    fun navigateToDetails() {
        val fragment = DetailsFragment()

        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun removeExtras() {
        activity!!.intent.removeExtra("SUPERHERO")
        activity!!.intent.removeExtra("COMIC")
    }
}