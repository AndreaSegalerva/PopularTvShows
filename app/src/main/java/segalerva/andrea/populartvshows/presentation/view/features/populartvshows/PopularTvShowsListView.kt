package segalerva.andrea.populartvshows.presentation.view.features.populartvshows

import segalerva.andrea.populartvshows.presentation.model.TvShowView
import segalerva.andrea.populartvshows.presentation.view.base.BaseView

/**
 * Created by andrea on 16/9/18.
 */
interface PopularTvShowsListView : BaseView {

    fun showTvShows(tvShowViews: List<TvShowView>)
    fun cleanListTvShows()
    fun disableLoadMore()
    fun hideTvShowsList()
    fun hideMoreLoading()
    fun enableSwipeRefreshLayout()
    fun navigateToTvShowDetail(tvShowView: TvShowView)
}