package segalerva.andrea.populartvshows.presentation.view.features.populartvshows

import android.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.cell_load_more.*
import kotlinx.android.synthetic.main.error_message_layout.*
import kotlinx.android.synthetic.main.fragment_popular_tv_shows_list.*
import segalerva.andrea.populartvshows.R
import segalerva.andrea.populartvshows.data.injector.DataDependencyInjector
import segalerva.andrea.populartvshows.domain.injector.DomainDependencyInjector
import segalerva.andrea.populartvshows.extensions.hide
import segalerva.andrea.populartvshows.extensions.show
import segalerva.andrea.populartvshows.presentation.model.TvShowView
import segalerva.andrea.populartvshows.presentation.view.base.BaseFragment
import segalerva.andrea.populartvshows.presentation.injector.PresentationDependencyInjector
import segalerva.andrea.populartvshows.presentation.view.features.tvshowdetail.TvShowDetailActivity
import segalerva.andrea.populartvshows.presentation.view.presenter.PopularTvShowsListPresenter

/**
 * Created by andrea on 16/9/18.
 */
class PopularTvShowsListFragment : BaseFragment(), PopularTvShowsListView {

    private lateinit var adapter: PopularTvShowsListAdapter
    private var presenter: PopularTvShowsListPresenter
    private var dataDependencyInjector = DataDependencyInjector()
    private var domainDependencyInjector = DomainDependencyInjector()
    private var presentationDependencyInjector: PresentationDependencyInjector = PresentationDependencyInjector(dataDependencyInjector, domainDependencyInjector)

    private var isAlreadyLoading = false
    private var numberElementsPerPage = 20

    companion object {
        fun newInstance() = PopularTvShowsListFragment()
    }

    init {
        presenter = PopularTvShowsListPresenter(this, presentationDependencyInjector)
    }

    // ------------------------------------------------------------------------------------
    // BaseFragment overrides
    // ------------------------------------------------------------------------------------

    override fun getFragmentLayout(): Int = R.layout.fragment_popular_tv_shows_list

    override fun prepareView() {

        prepareRecyclerView()
        setOnClickListeners()
        presenter.getPopularTvShowsData(false)
    }

    override fun showLoading() {
        super.showLoading()
        pb_loader.show()
    }

    override fun hideLoading() {
        super.hideLoading()
        pb_loader.hide()
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

        incl_error_message.show()
        tv_error_message.text = context!!.getText(message)
    }

    override fun hideErrorMessage() {

        incl_error_message.hide()
    }

    // ------------------------------------------------------------------------------------
    // PopularTvShowsListView overrides
    // ------------------------------------------------------------------------------------

    override fun populateTvShows(tvShowViews: List<TvShowView>) {

        rv_popular_tv_shows.show()
        isAlreadyLoading = false
        this.adapter.addTvShows(tvShowViews)
    }

    override fun hideTvShowsList() {

        rv_popular_tv_shows.hide()
    }

    override fun hideMoreLoading() {
        pb_bottom_loader.hide()
    }

    override fun disableLoadMore() {

        adapter.setLoadMoreEnabled(false)
        adapter.notifyDataSetChanged()
    }

    // ------------------------------------------------------------------------------------
    // PopularTvShowsListView overrides
    // ------------------------------------------------------------------------------------

    override fun navigateToTvShowDetail(tvShowView: TvShowView) {

        if (getBaseActivity() != null) {
            startActivity(TvShowDetailActivity.
                    createIntent(getBaseActivity()!!, tvShowView.name, tvShowView.id))
        }
    }

    // ------------------------------------------------------------------------------------
    // Private methods
    // ------------------------------------------------------------------------------------

    private fun prepareRecyclerView() {

        adapter = PopularTvShowsListAdapter(context!!)

        rv_popular_tv_shows.layoutManager = LinearLayoutManager(context())
        rv_popular_tv_shows.adapter = adapter
        addOnScrollListener()

        this.adapter.setOnTvShowClickListener(object : TvShowClickListener {
            override fun onTvShowClicked(tvShowView: TvShowView) {
                presenter.onTvShowClicked(tvShowView)
            }
        })
    }

    private fun addOnScrollListener() {

        rv_popular_tv_shows.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

                if (scrollPositionToLoadMore()) {

                    isAlreadyLoading = true
                    presenter.onLoadMore()
                }
            }
        })
    }

    /**
     * Returns if the scroll position is the one to start the load more pagination
     */
    private fun scrollPositionToLoadMore(): Boolean {

        val layoutManager = rv_popular_tv_shows.layoutManager as LinearLayoutManager
        val visibleItems = layoutManager.childCount
        val totalItems = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        return !isAlreadyLoading && (firstVisibleItemPosition + visibleItems) >= totalItems && totalItems >= numberElementsPerPage
    }

    private fun setOnClickListeners() {

        btn_try_again.setOnClickListener {

            presenter.onTryAgainClicked()
        }
    }
}