package segalerva.andrea.populartvshows.data.network.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created by andrea on 15/9/18.
 */
data class PopularTVShowsResponse(
        val page: Int,
        @SerializedName("total_results") val totalResults: Int,
        @SerializedName("total_pages") val totalPages: Int,
        val results: List<TvShow>)



