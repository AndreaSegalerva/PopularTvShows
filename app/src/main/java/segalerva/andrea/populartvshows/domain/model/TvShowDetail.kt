package segalerva.andrea.populartvshows.domain.model

/**
 * Created by andrea on 19/9/18.
 * Model that defines the tv show detail model in the domain layer
 */
class TvShowDetail(val id: Int,
                   val originalName: String,
                   val airDate: String,
                   val overView: String,
                   val voteCount: Int,
                   val voteAverage: Double,
                   val originalLanguage: String,
                   val backdropPath: String?,
                   val posterPath: String?,
                   var similarShows: ArrayList<TvShow>) {

    fun addSimilarShows(shows: List<TvShow>) {

        similarShows.addAll(shows)
    }
}