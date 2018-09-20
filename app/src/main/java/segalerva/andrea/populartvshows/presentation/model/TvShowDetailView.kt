package segalerva.andrea.populartvshows.presentation.model

/**
 * Created by andrea on 20/9/18.
 */
data class TvShowDetailView(
        val id: Int,
        val originalName: String,
        val airDate: String,
        val overView: String,
        val voteCount: Int,
        val voteAverage: Double,
        val originalLanguage: String,
        val backdropPath: String?,
        val posterPath: String?,
        val numberEpisodes: Int?,
        val numberSeasons: Int?,
        var similarShows: List<TvShowView>)
