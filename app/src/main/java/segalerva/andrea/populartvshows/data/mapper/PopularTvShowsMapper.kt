package segalerva.andrea.populartvshows.data.mapper

import segalerva.andrea.populartvshows.data.model.response.PopularTvShowsResponse
import segalerva.andrea.populartvshows.domain.model.PopularTvShows

/**
 * Created by andrea on 17/9/18.
 *  * Mapper to convert PopularTvShowsResponse to PopularTvShows model used in the domain layer
 */
class PopularTvShowsMapper(private val tvShowEntityMapper: TvShowEntityMapper) {

    fun map(popularTvShowsResponse: PopularTvShowsResponse): PopularTvShows {

        return PopularTvShows(popularTvShowsResponse.page,
                popularTvShowsResponse.totalPages,
                tvShowEntityMapper.mapList(popularTvShowsResponse.results))
    }
}