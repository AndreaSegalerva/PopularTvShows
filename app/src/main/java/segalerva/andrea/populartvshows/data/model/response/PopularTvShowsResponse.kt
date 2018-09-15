package segalerva.andrea.populartvshows.data.model.response

import com.google.gson.annotations.SerializedName
import segalerva.andrea.populartvshows.data.model.entity.TvShowEntity

/**
 * Created by andrea on 15/9/18.
 */
data class PopularTvShowsResponse(
        val page: Int,
        @SerializedName("total_results") val totalResults: Int,
        @SerializedName("total_pages") val totalPages: Int,
        val results: List<TvShowEntity>)



