package segalerva.andrea.populartvshows.data.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by andrea on 15/9/18.
 */
data class TvShowEntity(
        val id: String,
        @SerializedName("original_name")
        val originalName: String,
        @SerializedName("first_air_date")
        val airDate: String,
        val overview: String,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount:Int,
        @SerializedName("original_language")
        val originalLanguage:String,
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("poster_path")
        val posterPath: String?)