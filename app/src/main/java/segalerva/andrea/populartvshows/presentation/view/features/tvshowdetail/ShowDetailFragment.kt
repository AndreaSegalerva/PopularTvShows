package segalerva.andrea.populartvshows.presentation.view.features.tvshowdetail

import kotlinx.android.synthetic.main.fragment_tv_show_detail.*
import segalerva.andrea.populartvshows.R
import segalerva.andrea.populartvshows.data.injector.DataDependencyInjector
import segalerva.andrea.populartvshows.domain.injector.DomainDependencyInjector
import segalerva.andrea.populartvshows.extensions.hide
import segalerva.andrea.populartvshows.extensions.loadImage
import segalerva.andrea.populartvshows.extensions.show
import segalerva.andrea.populartvshows.presentation.injector.PresentationDependencyInjector
import segalerva.andrea.populartvshows.presentation.view.base.BaseFragment
import segalerva.andrea.populartvshows.presentation.view.presenter.ShowDetailPresenter

/**
 * Created by andrea on 19/9/18.
 */
class ShowDetailFragment : BaseFragment(), ShowDetailView {

    private var presenter: ShowDetailPresenter
    private var dataDependencyInjector = DataDependencyInjector()
    private var domainDependencyInjector = DomainDependencyInjector()
    private var presentationDependencyInjector: PresentationDependencyInjector = PresentationDependencyInjector(dataDependencyInjector, domainDependencyInjector)

    private var tvShowName = ""
    private var tvShowId: Int? = null

    companion object {

        fun newInstance() = ShowDetailFragment()
    }

    init {

        presenter = ShowDetailPresenter(this, presentationDependencyInjector)
    }


    // ------------------------------------------------------------------------------------
    // BaseFragment overrides
    // ------------------------------------------------------------------------------------

    override fun getFragmentLayout(): Int {

        return R.layout.fragment_tv_show_detail
    }

    override fun prepareView() {

        prepareArguments()
        initializeViewComponents()

        if (tvShowId != null) {

            presenter.getTvShowByIdData(tvShowId!!)
        }
    }

    private fun initializeViewComponents() {

        (getBaseActivity() as TvShowDetailActivity).configureToolbar(toolbar, tvShowName)
    }

    override fun showErrorMessage(message: Int) {

    }

    override fun hideErrorMessage() {

    }

    override fun showLoading() {
        super.showLoading()
        pb_loader.show()
    }

    override fun hideLoading() {
        super.hideLoading()
        pb_loader.hide()
    }

    // ------------------------------------------------------------------------------------
    // ShowDetailView overrides
    // ------------------------------------------------------------------------------------

    override fun showOverView(overview: String) {

        tv_overview_description.text = overview
    }

    override fun showVoteAverage(voteAverage: String) {

        tv_vote_average.text = voteAverage
    }

    override fun showPosterPicture(path: String) {

        iv_tv_show_poster.loadImage(path)
    }

    override fun showAirDate(airDate: String) {

        tv_air_date_value.text = airDate
    }

    override fun showNumberSeasons(numberSeasons: Int) {

        tv_seasons.text = getString(R.string.tv_show_seasons, numberSeasons)
    }

    override fun showNumberEpisodes(numberEpisodes: Int) {

        tv_episodes.text = getString(R.string.tv_show_episodes, numberEpisodes)
    }

    // ------------------------------------------------------------------------------------
    // Private methods
    // ------------------------------------------------------------------------------------

    private fun prepareArguments() {

        if (arguments != null) {

            if (arguments!!.containsKey("tvShowName")) {
                tvShowName = arguments!!.getString("tvShowName")
            }

            if (arguments!!.containsKey("tvShowId")) {
                tvShowId = arguments!!.getInt("tvShowId")
            }
        }
    }
}