package segalerva.andrea.populartvshows.presentation.view.features.tvshowdetail

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
    //TODO similar tvShows
}