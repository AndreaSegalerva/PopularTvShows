package segalerva.andrea.populartvshows.presentation.view.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by andrea on 16/9/18.
 * Base Fragment defined with all the basic fragment configuration and view methods used commonly by all the fragments
 * All the defined Fragments will extend from this abstract class, that way the same initialization and configuration is abstracted and applied to all of them
 */
abstract class BaseFragment : Fragment(), BaseView {

    private var activity: BaseActivity? = null

    fun getBaseActivity() = activity
    fun getFragmentTag() = this.javaClass.simpleName.toString()

    abstract fun getFragmentLayout(): Int

    /**
     * Method called when the view is created and it's components ready to use.
     */
    abstract fun prepareView()

    // ------------------------------------------------------------------------------------
    // BaseView overrides
    // ------------------------------------------------------------------------------------

    override fun context(): Context? = activity

    override fun showLoading() {

        Log.d(getFragmentTag(), "Showing loading")
    }

    override fun hideLoading() {

        Log.d(getFragmentTag(), "Hide loading")
    }

    /**
     * Method to know if the view is prepared to be manipulated when asynchronous are executed
     */
    override fun isSafeManipulateView(): Boolean {

        return isAdded && getBaseActivity() != null && !getBaseActivity()!!.isFinishing
    }

    // ------------------------------------------------------------------------------------
    // Fragment lifecycle default overrides
    // ------------------------------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(getFragmentLayout(), null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        prepareView()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is BaseActivity) {
            this.activity = context
        }
    }
}