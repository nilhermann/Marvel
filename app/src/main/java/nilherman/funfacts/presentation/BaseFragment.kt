package nilherman.funfacts.presentation

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import nilherman.funfacts.R
import nilherman.funfacts.presentation.detail.DetailsFragment

open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity?.let { activity ->
                activity.window.statusBarColor = ContextCompat.getColor(activity, R.color.colorPrimaryDark)
            }
        }
    }

    fun navigateToDetails() {
        val fragment = DetailsFragment()

        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.let {
            transaction.replace(R.id.container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    fun removeExtras() {
        activity?.intent?.removeExtra("SUPERHERO")
        activity?.intent?.removeExtra("COMIC")
    }
}