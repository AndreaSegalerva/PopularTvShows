package segalerva.andrea.populartvshows.presentation.view.presenter

import io.reactivex.observers.DisposableObserver
import segalerva.andrea.populartvshows.domain.model.PopularTvShows
import segalerva.andrea.populartvshows.presentation.view.base.BaseView
import segalerva.andrea.populartvshows.presentation.view.features.populartvshows.PopularTvShowsListView
import segalerva.andrea.populartvshows.presentation.injector.PresentationDependencyInjector

/**
 * Created by andrea on 16/9/18.
 * Presenter of tv shows list view,containing all the business logic of the view
 * @param view
 * @param presentationDependencyInjector
 */

class PopularTvShowsListPresenter(private val view: PopularTvShowsListView, private val presentationDependencyInjector: PresentationDependencyInjector) : BasePresenter {

    override fun getView(): BaseView = view
    private var totalPages = 0

    fun initializeData() {

        view.showLoading()

        presentationDependencyInjector.getPopularTvShows().execute(object : DisposableObserver<PopularTvShows>() {

            override fun onComplete() {
                //Do nothing for the moment
            }

            override fun onError(e: Throwable) {

                if (isSafeManipulateView()) {

                    view.hideLoading()
                }
            }

            override fun onNext(populatTvShows: PopularTvShows) {

                if (isSafeManipulateView()) {
                    view.hideLoading()
                    totalPages = populatTvShows.totalPages
                    view.populateTvShows(presentationDependencyInjector.getTvShowMapper().mapList(populatTvShows.shows))
                }
            }
        }, 1)

    }
}