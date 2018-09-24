package segalerva.andrea.populartvshows.presentation.view.features.populartvshows

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cell_load_more.view.*
import kotlinx.android.synthetic.main.cell_tv_show.view.*
import segalerva.andrea.populartvshows.R
import segalerva.andrea.populartvshows.extensions.hide
import segalerva.andrea.populartvshows.extensions.loadImage
import segalerva.andrea.populartvshows.extensions.show
import segalerva.andrea.populartvshows.presentation.model.TvShowView

/**
 * Created by andrea on 16/9/18.
 * Adapter for TVShows Recycler View
 */
class PopularTvShowsListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val typeTvShow = 0
    private val typeLoadMore = 1
    private var tvShows: ArrayList<TvShowView> = ArrayList()
    private var loadMoreEnabled = true
    private var tvShowClickListener: TvShowClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val tvShowCell = layoutInflater.inflate(R.layout.cell_tv_show, parent, false)
        val loadMoreCell = layoutInflater.inflate(R.layout.cell_load_more, parent, false)

        return when (viewType) {
            typeTvShow -> TvShowViewHolder(tvShowCell)
            typeLoadMore -> LoadMoreViewHolder(loadMoreCell)
            else -> {
                throw IllegalArgumentException("The view type $viewType is a invalid argument invalid")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {

            typeTvShow -> {

                val tvShowView = getItem(position)

                if (tvShowView != null) {
                    (holder as TvShowViewHolder).bindTvShow(tvShowView)

                    holder.itemView.setOnClickListener {
                        onItemClickListener(tvShowView)
                    }
                }
            }

            typeLoadMore -> {

                if (loadMoreEnabled) {
                    (holder as LoadMoreViewHolder).showLoadMore()
                } else {
                    (holder as LoadMoreViewHolder).hideLoadMore()
                }
            }
        }
    }

    override fun getItemCount(): Int {

        return tvShows.size + 1 // +1 because of the load more cell item
    }

    override fun getItemViewType(position: Int): Int {

        return if (position != 0 && position == itemCount - 1) {
            typeLoadMore
        } else {
            typeTvShow
        }
    }

// ------------------------------------------------------------------------------------
// Public methods
// ------------------------------------------------------------------------------------

    fun addTvShows(tvShowViewList: List<TvShowView>) {

        tvShows.addAll(tvShowViewList)
        this.notifyDataSetChanged()
    }

    fun cleanTvShows() {
        tvShows.clear()
    }

    fun setLoadMoreEnabled(enable: Boolean) {

        loadMoreEnabled = enable
    }

    fun setOnTvShowClickListener(clickListener: TvShowClickListener) {

        this.tvShowClickListener = clickListener
    }


// ------------------------------------------------------------------------------------
// Private methods
// ------------------------------------------------------------------------------------

    private fun getItem(position: Int): TvShowView? {

        return if (tvShows.size > position) {
            tvShows[position]
        } else {
            null
        }
    }

    private fun onItemClickListener(tvShowView: TvShowView) {

        tvShowClickListener?.onTvShowClicked(tvShowView)
    }

// ------------------------------------------------------------------------------------
// TvShow View Holder class
// ------------------------------------------------------------------------------------

    /**
     * View Holder of tv show cell view
     * @param itemView
     */
    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var view: View = itemView

        fun bindTvShow(tvShowView: TvShowView) {

            view.tv_show_name.text = tvShowView.name
            view.tv_votes_count_value.text = tvShowView.voteCount.toString()
            view.tv_vote_average.text = tvShowView.voteAverage.toString()
            view.iv_show_poster.loadImage(tvShowView.posterPath)
        }
    }

    /**
     * View Holder of tv load more cell view
     * @param itemView
     */
    class LoadMoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var view: View = itemView

        fun showLoadMore() {
            view.pb_bottom_loader.show()
        }

        fun hideLoadMore() {
            view.pb_bottom_loader.hide()
        }
    }
}