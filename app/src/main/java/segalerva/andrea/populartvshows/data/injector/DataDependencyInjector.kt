package segalerva.andrea.populartvshows.data.injector

import segalerva.andrea.populartvshows.data.mapper.PopularTvShowsMapper
import segalerva.andrea.populartvshows.data.mapper.TvShowEntityMapper
import segalerva.andrea.populartvshows.data.network.RestClient
import segalerva.andrea.populartvshows.data.repository.TvShowsDataRepositoryImpl
import segalerva.andrea.populartvshows.data.repository.TvShowsRepository
import segalerva.andrea.populartvshows.data.repository.datasource.RemoteTvShowsDataSource
import segalerva.andrea.populartvshows.data.repository.datasource.TvShowsDataSource

/**
 * Created by andrea on 17/9/18.
 * Injector of all the dependencies needed in the data layer
 */
class DataDependencyInjector {


    /**
     * Returns an instance of [TvShowsRepository] needed in every use case defined in the domain layer
     */
    fun getTvShowsRepository(): TvShowsRepository {


        return TvShowsDataRepositoryImpl(getTvShowsDataSource(), getPopularTvShowsMapper(), getTvShowEntityMapper())
    }

    /**
     * Returns an instance of [RestClient] used in [RemoteTvShowsDataSource]
     */
    private fun getRestClient(): RestClient {
        return RestClient()
    }

    /**
     * Returns an instance of [TvShowsDataSource]
     */
    private fun getTvShowsDataSource(): TvShowsDataSource {

        return RemoteTvShowsDataSource(getRestClient())
    }

    /**
     * Returns an instance of [TvShowEntityMapper] needed in [PopularTvShowsMapper]
     */
    private fun getTvShowEntityMapper(): TvShowEntityMapper = TvShowEntityMapper()

    /**
     * Returns an instance of [PopularTvShowsMapper] needed in [TvShowsDataRepositoryImpl]
     */
    private fun getPopularTvShowsMapper(): PopularTvShowsMapper {

        return PopularTvShowsMapper(getTvShowEntityMapper())
    }
}