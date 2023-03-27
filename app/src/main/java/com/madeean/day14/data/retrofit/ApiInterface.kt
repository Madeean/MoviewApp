package com.madeean.day14.data.retrofit

import com.madeean.day14.data.model.ModelListData
import com.madeean.day14.data.model.ModelMovieDetail
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Observable<ModelListData>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") id:Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
    ):Observable<ModelMovieDetail>
}