package segalerva.andrea.populartvshows.domain.mapper

import segalerva.andrea.populartvshows.domain.model.TvShow
import segalerva.andrea.populartvshows.domain.model.TvShowDetail

/**
 * Created by andrea on 19/9/18.
 * Mapper to convert [TvShow] to [TvShowDetail] model used in the domain layer
 */
class TvShowDetailMapper {

    fun map(tvShow: TvShow): TvShowDetail {

        return TvShowDetail(tvShow.id,
                tvShow.originalName,
                tvShow.airDate,
                tvShow.overView,
                tvShow.voteCount,
                tvShow.voteAverage,
                tvShow.originalLanguage,
                tvShow.backdropPath,
                tvShow.posterPath,
                tvShow.numberEpisodes,
                tvShow.numberSeasons,
                ArrayList())
    }
}