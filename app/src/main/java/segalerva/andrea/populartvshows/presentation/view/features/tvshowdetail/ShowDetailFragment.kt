package segalerva.andrea.populartvshows.presentation.view.features.tvshowdetail

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.fragment_tv_show_detail.*
import segalerva.andrea.populartvshows.R
import segalerva.andrea.populartvshows.data.injector.DataDependencyInjector
import segalerva.andrea.populartvshows.domain.injector.DomainDependencyInjector
import segalerva.andrea.populartvshows.extensions.hide
import segalerva.andrea.populartvshows.extensions.loadImage
import segalerva.andrea.populartvshows.extensions.show
import segalerva.andrea.populartvshows.presentation.injector.PresentationDependencyInjector
import segalerva.andrea.populartvshows.presentation.model.TvShowView
import segalerva.andrea.populartvshows.presentation.view.base.BaseFragment
import segalerva.andrea.populartvshows.presentation.view.features.populartvshows.TvShowClickListener
import segalerva.andrea.populartvshows.presentation.view.features.tvshowdetail.TvShowDetailActivity.Companion.createIntent
import segalerva.andrea.populartvshows.presentation.view.presenter.ShowDetailPresenter

/**
 * Created by andrea on 19/9/18.
 */
class ShowDetailFragment : BaseFragment(), ShowDetailView {

    private var presenter: ShowDetailPresenter
    private var dataDependencyInjector = DataDependencyInjector()
    private var domainDependencyInjector = DomainDependencyInjector()
    private var presentationDependencyInjector: PresentationDependencyInjector = PresentationDependencyInjector(dataDependencyInjector, domainDependencyInjector)

    private lateinit var adapter: SimilarShowListAdapter
    private var tvShowName = ""
    private var tvShowId: Int? = null
    private val numberElementsPerPage = 20
    private var isAlreadyLoading = false

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
        prepareRecyclerView()

        if (tvShowId != null) {

            presenter.getTvShowByIdData(tvShowId!!)
        }
    }

    private fun initializeViewComponents() {

        (getBaseActivity() as TvShowDetailActivity).configureToolbar(toolbar, tvShowName)
    }

    override fun showConnectionDialog() {
        super.showConnectionDialog()

        if (context != null) {

            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle(context!!.getString(R.string.dialog_no_connection_title))
            alertDialog.setMessage(context!!.getString(R.string.dialog_need_internet_connection_message))
            alertDialog.setPositiveButton(context!!.getString(R.string.dialog_no_connection_ok_button)) { _, _ ->
                Log.d(getFragmentTag(), "Okey clicked")
            }.show()
        }
    }

    override fun showErrorMessage(message: Int) {

        tv_missing_internet_connection.show()
        tv_missing_internet_connection.text = getString(message)
    }

    override fun hideErrorMessage() {

        tv_missing_internet_connection.hide()
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

        tv_overview_title.show()
        tv_overview_description.text = overview
    }

    override fun showVoteAverage(voteAverage: String) {

        iv_star.show()
        tv_vote_average.text = voteAverage
    }

    override fun showPosterPicture(path: String) {

        iv_tv_show_poster.loadImage(path)
    }

    override fun showAirDate(airDate: String) {

        tv_air_date_title.show()
        tv_air_date_value.text = airDate
    }

    override fun showNumberSeasons(numberSeasons: Int) {

        tv_episodes_title.show()
        tv_seasons.text = getString(R.string.tv_show_seasons, numberSeasons)
    }

    override fun showNumberEpisodes(numberEpisodes: Int) {

        tv_episodes_title.show()
        tv_episodes.text = getString(R.string.tv_show_episodes, numberEpisodes)
    }

    override fun showSimilarTvShows(similarTvShows: List<TvShowView>) {

        tv_similar_shows_title.show()
        rv_similar_tv_shows.show()
        isAlreadyLoading = false
        adapter.addTvShows(similarTvShows)
    }

    override fun hideSimilarTvShows() {

        rv_similar_tv_shows.hide()
    }

    override fun disableLoadMoreSimilarTvShows() {

        adapter.setLoadMoreEnabled(false)
        adapter.notifyDataSetChanged()
    }

    override fun navigateToTvShowDetail(showId: Int, showName: String) {

        startActivity(createIntent(getBaseActivity()!!, showName, showId))
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

    private fun prepareRecyclerView() {

        adapter = SimilarShowListAdapter(context!!)
        rv_similar_tv_shows.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_similar_tv_shows.adapter = adapter
        addOnScrollListener()

        adapter.setOnTvShowClickListener(object : TvShowClickListener {

            override fun onTvShowClicked(tvShowView: TvShowView) {

                presenter.onTvShowClicked(tvShowView)
            }
        })
    }

    private fun addOnScrollListener() {

        rv_similar_tv_shows.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

                if (scrollPositionToLoadMore()) {

                    isAlreadyLoading = true
                    presenter.onLoadMore()
                }
            }
        })
    }

    /**
     * Returns if the scroll position is the one to start the load more lateral pagination
     */
    private fun scrollPositionToLoadMore(): Boolean {

        val layoutManager = rv_similar_tv_shows.layoutManager as LinearLayoutManager
        val visibleItems = layoutManager.childCount
        val totalItems = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        return !isAlreadyLoading && (firstVisibleItemPosition + visibleItems) >= totalItems && totalItems >= numberElementsPerPage
    }
}