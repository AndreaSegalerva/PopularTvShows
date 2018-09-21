package segalerva.andrea.populartvshows.domain.model

/**
 * Created by andrea on 15/9/18.
 */
data class TvShow(val id: Int,
                  val originalName: String,
                  val airDate: String?,
                  val overView:String?,
                  val voteCount: Int,
                  val voteAverage: Double,
                  val originalLanguage: String,
                  val backdropPath: String?,
                  val posterPath: String?,
                  val numberEpisodes:Int?,
                  val numberSeasons:Int?)