package segalerva.andrea.populartvshows.presentation.mapper

import segalerva.andrea.populartvshows.domain.model.TvShow
import segalerva.andrea.populartvshows.presentation.model.TvShowView

/**
 * Created by andrea on 16/9/18.
 * Mapper to convert TvShow to TvShowView model which will be used when showing the movies in the view
 */
class TvShowMapper {

    fun map(tvShow: TvShow): TvShowView {

        return TvShowView(tvShow.originalName, tvShow.posterPath, tvShow.voteCount, tvShow.voteAverage, tvShow.originalLanguage)
    }

    fun mapList(tvShows: List<TvShow>): List<TvShowView> {

        val tvShowViews = ArrayList<TvShowView>()

        if (tvShows.isNotEmpty()) {

            tvShows.mapTo(tvShowViews) {
                map(it)
            }
        }

        return tvShowViews;
    }
}


