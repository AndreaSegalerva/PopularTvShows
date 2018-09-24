package segalerva.andrea.populartvshows.presentation.view.features.populartvshows

import segalerva.andrea.populartvshows.presentation.view.base.BaseActivity
import segalerva.andrea.populartvshows.presentation.view.base.BaseFragment

/**
 * Created by andrea on 16/9/18.
 */
class PopularTvShowsListActivity : BaseActivity() {

    override fun getInitialFragment(): BaseFragment? = PopularTvShowsListFragment.newInstance()
}