package segalerva.andrea.populartvshows.presentation.view.presenter

import io.reactivex.observers.DisposableObserver
import segalerva.andrea.populartvshows.domain.interactor.usecases.GetPopularTvShows
import segalerva.andrea.populartvshows.domain.model.TvShow
import segalerva.andrea.populartvshows.presentation.mapper.TvShowMapper
import segalerva.andrea.populartvshows.presentation.view.base.BaseView
import segalerva.andrea.populartvshows.presentation.view.features.populartvshows.PopularTvShowsListView

/**
 * Created by andrea on 16/9/18.
 */
class PopularTvShowsListPresenter(private val view: PopularTvShowsListView) : BasePresenter {

    override fun getView(): BaseView = view

    private val getPopularTvShows = GetPopularTvShows()
    private val tvShowsMapper = TvShowMapper()

    fun initializeData() {

        view.showLoading()

        getPopularTvShows.execute(object : DisposableObserver<List<TvShow>>() {

            override fun onComplete() {
                //Do nothing
            }

            override fun onError(e: Throwable) {

                if (isSafeManipulateView()) {

                    view.hideLoading()
                }
            }

            override fun onNext(t: List<TvShow>) {

                if (isSafeManipulateView()) {
                    view.hideLoading()
                    view.populateTvShows(tvShowsMapper.mapList(t))
                }

            }
        }, 1)

    }
}