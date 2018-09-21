package segalerva.andrea.populartvshows.presentation.view.features.tvshowdetail

import android.support.annotation.StringRes
import segalerva.andrea.populartvshows.presentation.model.TvShowView
import segalerva.andrea.populartvshows.presentation.view.base.BaseView

/**
 * Created by andrea on 20/9/18.
 */
interface ShowDetailView : BaseView {

    fun showOverView(overview: String)
    fun showVoteAverage(voteAverage: String)
    fun showPosterPicture(path: String)
    fun showAirDate(airDate: String)
    fun showNumberSeasons(numberSeasons: Int)
    fun showNumberEpisodes(numberEpisodes: Int)
    fun showSimilarTvShows(similarTvShows: List<TvShowView>)
    fun hideSimilarTvShows()
    fun disableLoadMoreSimilarTvShows()
    fun navigateToTvShowDetail(showId: Int,showName:String)
    fun showToastMessage(@StringRes message: Int)
    fun hideLoadMore()
}