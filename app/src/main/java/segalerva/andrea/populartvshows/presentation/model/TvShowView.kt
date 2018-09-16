package segalerva.andrea.populartvshows.presentation.model

/**
 * Created by andrea on 16/9/18.
 */
data class TvShowView(val name: String,
                      val posterPath: String?,
                      val voteCount:Int,
                      val voteAverage: Double,
                      val originalLanguage: String)