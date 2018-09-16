package segalerva.andrea.populartvshows.data.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by andrea on 15/9/18.
 */
class RestClient {

    private val baseUrl = "https://api.themoviedb.org/3/"
    var retrofit: Retrofit? = null
    var apiService: ApiService? = null

    init {
        createRetrofit()
    }

    private fun createRetrofit() {

        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        this.apiService = retrofit?.create(ApiService::class.java)
    }
}