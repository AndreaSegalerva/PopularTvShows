package segalerva.andrea.populartvshows.presentation.view.features.populartvshows

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cell_tv_show.view.*
import segalerva.andrea.populartvshows.R
import segalerva.andrea.populartvshows.extensions.loadImage
import segalerva.andrea.populartvshows.presentation.model.TvShowView

/**
 * Created by andrea on 16/9/18.
 * Adapter for TVShows Recycler View
 */
class PopularTvShowsListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var tvShows: ArrayList<TvShowView> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.cell_tv_show, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val tvShowView = getItem(position)

        (holder as TvShowViewHolder).bindTvShow(tvShowView)
    }

    override fun getItemCount(): Int {

        return tvShows.count()
    }

    fun getItem(position: Int): TvShowView {

        return tvShows[position]
    }

    fun addTvShows(tvSHowViewList: List<TvShowView>) {

        tvShows.addAll(tvSHowViewList)
        this.notifyDataSetChanged()
    }

    // ------------------------------------------------------------------------------------
    // TvShow View Holder class
    // ------------------------------------------------------------------------------------

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var view: View = itemView

        fun bindTvShow(tvShowView: TvShowView) {

            view.tv_show_name.text = tvShowView.name
            view.tv_votes_count_value.text = tvShowView.voteCount.toString()
            view.tv_vote_average.text = tvShowView.voteAverage.toString()
            view.iv_show_poster.loadImage(tvShowView.posterPath)
        }
    }
}