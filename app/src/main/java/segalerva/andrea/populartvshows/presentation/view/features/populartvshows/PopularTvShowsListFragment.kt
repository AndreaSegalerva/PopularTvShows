package segalerva.andrea.populartvshows.presentation.view.features.populartvshows

import android.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.error_message_layout.*
import kotlinx.android.synthetic.main.popular_tv_shows_list_fragment.*
import segalerva.andrea.populartvshows.R
import segalerva.andrea.populartvshows.data.injector.DataDependencyInjector
import segalerva.andrea.populartvshows.extensions.hide
import segalerva.andrea.populartvshows.extensions.show
import segalerva.andrea.populartvshows.presentation.model.TvShowView
import segalerva.andrea.populartvshows.presentation.view.base.BaseFragment
import segalerva.andrea.populartvshows.presentation.injector.PresentationDependencyInjector
import segalerva.andrea.populartvshows.presentation.view.presenter.PopularTvShowsListPresenter

/**
 * Created by andrea on 16/9/18.
 */
class PopularTvShowsListFragment : BaseFragment(), PopularTvShowsListView {

    private lateinit var adapter: PopularTvShowsListAdapter
    private var presenter: PopularTvShowsListPresenter
    private var dataDependencyInjector = DataDependencyInjector()
    private var presentationDependencyInjector: PresentationDependencyInjector = PresentationDependencyInjector(dataDependencyInjector)

    companion object {
        fun newInstance() = PopularTvShowsListFragment()
    }

    init {
        presenter = PopularTvShowsListPresenter(this, presentationDependencyInjector)
    }

    // ------------------------------------------------------------------------------------
    // BaseFragment overrides
    // ------------------------------------------------------------------------------------

    override fun getFragmentLayout(): Int = R.layout.popular_tv_shows_list_fragment

    override fun prepareView() {

        prepareRecyclerView()
        setOnClickListeners()
        presenter.initializeData()
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

        this.adapter.addTvShows(tvShowViews)
    }

// ------------------------------------------------------------------------------------
// Private methods
// ------------------------------------------------------------------------------------

    private fun prepareRecyclerView() {

        adapter = PopularTvShowsListAdapter(context!!)
        rv_popular_tv_shows.layoutManager = LinearLayoutManager(context())
        rv_popular_tv_shows.adapter = adapter
    }

    private fun setOnClickListeners() {

        btn_try_again.setOnClickListener {

            presenter.onTryAgainClicked()
        }
    }
}