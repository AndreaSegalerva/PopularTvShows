package segalerva.andrea.populartvshows.presentation.view.features.populartvshows

import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.popular_tv_shows_list_fragment.*
import segalerva.andrea.populartvshows.R
import segalerva.andrea.populartvshows.presentation.view.base.BaseFragment

/**
 * Created by andrea on 16/9/18.
 */
class PopularTvShowsListFragment : BaseFragment() {

    private lateinit var adapter: PopularTvShowsListAdapter

    companion object {
        fun newInstance() = PopularTvShowsListFragment()
    }

    // ------------------------------------------------------------------------------------
    // BaseFragment overrides
    // ------------------------------------------------------------------------------------

    override fun getFragmentLayout(): Int = R.layout.popular_tv_shows_list_fragment

    override fun prepareView() {

        prepareRecyclerView()
    }

    // ------------------------------------------------------------------------------------
    // Private methods
    // ------------------------------------------------------------------------------------

    private fun prepareRecyclerView() {

        adapter = PopularTvShowsListAdapter(context!!)
        rv_popular_tv_shows.layoutManager = LinearLayoutManager(context())
        rv_popular_tv_shows.adapter = adapter
    }
}