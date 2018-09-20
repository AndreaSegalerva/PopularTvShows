package segalerva.andrea.populartvshows.data.injector

import segalerva.andrea.populartvshows.data.mapper.PopularTvShowsMapper
import segalerva.andrea.populartvshows.data.mapper.TvShowEntityMapper
import segalerva.andrea.populartvshows.data.network.RestClient
import segalerva.andrea.populartvshows.data.repository.TvShowsDataRepositoryImpl
import segalerva.andrea.populartvshows.data.repository.datasource.RemoteTvShowsDataSource

/**
 * Created by andrea on 17/9/18.
 * Injector of all the dependencies needed in the data layer
 */
class DataDependencyInjector {

    fun getRemoteTvShowsDataSource() = RemoteTvShowsDataSource(getRestClient())

    /**
     * Returns an instance of [TvShowEntityMapper] needed in [PopularTvShowsMapper]
     */
    fun getTvShowEntityMapper(): TvShowEntityMapper = TvShowEntityMapper()

    /**
     * Returns an instance of [PopularTvShowsMapper] needed in [TvShowsDataRepositoryImpl]
     */
    fun getPopularTvShowsMapper(): PopularTvShowsMapper {

        return PopularTvShowsMapper(getTvShowEntityMapper())
    }

    /**
     * Returns an instance of [TvShowsDataRepositoryImpl] needed in every use case defined in the domain layer
     */
    fun getTvShowsDataRepository(): TvShowsDataRepositoryImpl {

        return TvShowsDataRepositoryImpl(getRemoteTvShowsDataSource(), getPopularTvShowsMapper(), getTvShowEntityMapper())
    }

    /**
     * Returns an instance of [RestClient] used in [RemoteTvShowsDataSource]
     */
    private fun getRestClient(): RestClient {
        return RestClient()
    }
}