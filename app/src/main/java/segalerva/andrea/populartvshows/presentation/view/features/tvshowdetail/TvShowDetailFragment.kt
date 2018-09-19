package segalerva.andrea.populartvshows.presentation.view.features.tvshowdetail

import kotlinx.android.synthetic.main.fragment_tv_show_detail.*
import segalerva.andrea.populartvshows.R
import segalerva.andrea.populartvshows.presentation.view.base.BaseFragment

/**
 * Created by andrea on 19/9/18.
 */
class TvShowDetailFragment : BaseFragment() {

    private var tvShowName = ""

    companion object {

        fun newInstance() = TvShowDetailFragment()
    }

    // ------------------------------------------------------------------------------------
    // BaseFragment overrides
    // ------------------------------------------------------------------------------------

    override fun getFragmentLayout(): Int {

        return R.layout.fragment_tv_show_detail
    }

    override fun prepareView() {

        prepareArguments()
        initializeViewComponents()
    }

    private fun initializeViewComponents() {

        (getBaseActivity() as TvShowDetailActivity).configureToolbar(toolbar, tvShowName)
    }

    override fun showErrorMessage(message: Int) {

    }

    override fun hideErrorMessage() {

    }

    // ------------------------------------------------------------------------------------
    // Private methods
    // ------------------------------------------------------------------------------------

    private fun prepareArguments() {

        if (arguments != null) {

            tvShowName = arguments!!.getString("tvShowName")
        }
    }
}