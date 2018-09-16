package segalerva.andrea.populartvshows.presentation.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import segalerva.andrea.populartvshows.R

/**
 * Created by andrea on 16/9/18.
 * Base Activity defined with all the basic activity configuration used commonly by all the activities
 * All the defined Activities will extend from this abstract class, that way the same initialization and configuration is abstracted and applied to all of them
 */
abstract class BaseActivity : AppCompatActivity() {

    protected open fun getLayoutActivity(): Int = R.layout.activity_principal_frame

    /**
     * Method implemented by all the Activities to define the BaseFragment in it
     */
    protected abstract fun getInitialFragment(): BaseFragment?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutActivity())
        prepareView()
    }

    /**
     * Once the activity view is created this is called
     * This method replaces the principal frameLayout of the activity by the one defined in getInitialFragment method
     */
    fun prepareView() {

        val fragment = getInitialFragment()

        fragment?.let {

            val extras = intent.extras

            extras?.let {

                fragment.arguments = extras
                replaceFragment(R.id.fm_container, fragment, fragment.getFragmentTag(), false)
            }
        }

    }

    /**
     * Replace the actual frame layout container by the fragment defined in getInitialFragment
     */
    fun replaceFragment(container: Int, fragment: BaseFragment, tag: String, addToBackStack: Boolean) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(container, fragment, tag)

        if (addToBackStack) {

            fragmentTransaction.addToBackStack(tag)
        }

        fragmentTransaction.commitAllowingStateLoss()
    }
}