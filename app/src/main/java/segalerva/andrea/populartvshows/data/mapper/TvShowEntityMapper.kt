package segalerva.andrea.populartvshows.data.mapper

import segalerva.andrea.populartvshows.data.model.entity.TvShowEntity
import segalerva.andrea.populartvshows.domain.model.TvShow

/**
 * Created by andrea on 16/9/18.
 * Mapper to convert [TvShowEntity] to [TvShow] model used in the domain layer
 */
class TvShowEntityMapper {

    fun map(tvShowEntity: TvShowEntity): TvShow {

        return TvShow(tvShowEntity.id,
                tvShowEntity.originalName,
                tvShowEntity.airDate,
                tvShowEntity.overview,
                tvShowEntity.voteCount,
                tvShowEntity.voteAverage,
                tvShowEntity.originalLanguage,
                tvShowEntity.backdropPath,
                tvShowEntity.posterPath,
                tvShowEntity.numberEpisodes,
                tvShowEntity.numberSeasons)
    }

    fun mapList(tvShowsEntity: List<TvShowEntity>): List<TvShow> {

        val tvShowsList = ArrayList<TvShow>()
        tvShowsEntity.mapTo(tvShowsList) {
            map(it)
        }
        return tvShowsList
    }
}