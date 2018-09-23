package segalerva.andrea.populartvshows.domain.interactor.usecases.similartvshows

import io.reactivex.Observable
import segalerva.andrea.populartvshows.data.injector.DataDependencyInjector
import segalerva.andrea.populartvshows.domain.interactor.Interactor
import segalerva.andrea.populartvshows.domain.model.PopularTvShows

/**
 * Created by andrea on 19/9/18.
 * Use case to obtain the list of similar tv shows.
 * Connects with the repository [TvShowsDataRepositoryImpñ]
 */

* Use case to obtain the list of the most popular shows.
* Connects with the repository [TvShowsDataRepositoryImpl] defined in the data layer and
* returns the PopularTvShows model obtained from the remote data source.
*/
class GetSimilarTvShows(private val dataDependencyInjector: DataDependencyInjector) : Interactor<PopularTvShows, GetSimilarTvShowsParams>() {

    override fun buildInteractorObservable(params: GetSimilarTvShowsParams): Observable<PopularTvShows> {

       return dataDependencyInjector.getTvShowsDataRepository().getSimilarTvShows(params.showId, params.page)
    }
}