package segalerva.andrea.populartvshows.presentation.mapper

import segalerva.andrea.populartvshows.domain.model.TvShowDetail
import segalerva.andrea.populartvshows.presentation.model.TvShowDetailView

/**
 * Created by andrea on 20/9/18.
 **Mapper to convert [TvShowDetail] to [TvShowDetailView] model used in the presentation layer
 * @param tvShowMapper
 */

class TvShowDetailMapper(private val tvShowMapper: TvShowMapper) {

    fun map(tvShowDetail: TvShowDetail): TvShowDetailView {

        return TvShowDetailView(tvShowDetail.id,
                tvShowDetail.originalName,
                tvShowDetail.airDate,
                tvShowDetail.overView,
                tvShowDetail.voteCount,
                tvShowDetail.voteAverage,
                tvShowDetail.originalLanguage,
                tvShowDetail.backdropPath,
                tvShowDetail.posterPath,
                tvShowDetail.numberEpisodes,
                tvShowDetail.numberSeasons,
                tvShowDetail.similarTvShows)
    }
}