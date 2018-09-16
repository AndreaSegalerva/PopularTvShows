package segalerva.andrea.populartvshows.presentation.view.features.populartvshows

import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.popular_tv_shows_list_fragment.*
import segalerva.andrea.populartvshows.R
import segalerva.andrea.populartvshows.extensions.hide
import segalerva.andrea.populartvshows.extensions.show
import segalerva.andrea.populartvshows.presentation.model.TvShowView
import segalerva.andrea.populartvshows.presentation.view.base.BaseFragment
import segalerva.andrea.populartvshows.presentation.view.presenter.injector.PresenterDependencyInjector
import segalerva.andrea.populartvshows.presentation.view.presenter.PopularTvShowsListPresenter

/**
 * Created by andrea on 16/9/18.
 */
class PopularTvShowsListFragment : BaseFragment(), PopularTvShowsListView {

    private lateinit var adapter: PopularTvShowsListAdapter
    private var presenter: PopularTvShowsListPresenter
    private var presenterDependencyInjector: PresenterDependencyInjector = PresenterDependencyInjector()

    companion object {
        fun newInstance() = PopularTvShowsListFragment()
    }

    init {
        presenter = PopularTvShowsListPresenter(this, presenterDependencyInjector)
    }

    // ------------------------------------------------------------------------------------
    // BaseFragment overrides
    // ------------------------------------------------------------------------------------

    override fun getFragmentLayout(): Int = R.layout.popular_tv_shows_list_fragment

    override fun prepareView() {

        prepareRecyclerView()
        presenter.initializeData()
    }

    override fun showLoading() {
        super.showLoading()
        pb_loader.show()
    }

    override fun hideLoading() {
        super.hideLoading()
        pb_loader.hide()
    }

    // ------------------------------------------------------------------------------------
    // PopularTvShowsListView overrides
    // ------------------------------------------------------------------------------------

    override fun populateTvShows(tvShowViews: List<TvShowView>) {

        this.adapter.addTvShows(tvShowViews)
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