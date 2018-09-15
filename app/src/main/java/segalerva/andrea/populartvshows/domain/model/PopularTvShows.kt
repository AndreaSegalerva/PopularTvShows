package segalerva.andrea.populartvshows.domain.model

/**
 * Created by andrea on 15/9/18.
 */
data class PopularTvShows(val page: Int,
                          val totalResults: Int,
                          val totalPages: Int,
                          val shows: List<TvShow>)
