package com.madeean.day14.repository

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.madeean.day14.R
import com.madeean.day14.Utils.API_KEY
import com.madeean.day14.data.model.ModelDetailData
import com.madeean.day14.data.model.ModelMovieDetail
import com.madeean.day14.data.retrofit.RetrofitClient
import io.reactivex.disposables.Disposable
import retrofit2.HttpException

class DetailMovieRepository{
    val model = MutableLiveData<ModelMovieDetail>()

    fun getDetailMovie(id:Int): MutableLiveData<ModelMovieDetail>{

        RetrofitClient.apiInterface.getMovieDetail(id,API_KEY,"en-US")
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe(object : io.reactivex.Observer<ModelMovieDetail>{
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: ModelMovieDetail) {
                    println(t)
                    model.value = t
                }

                override fun onError(e: Throwable) {
                    if (e is HttpException) {
                        val error: HttpException = e
                        val errorBody: String = error.response()?.errorBody()?.string() ?: ""
                        println(errorBody)
                        // Then parse the errorBody and extract the values you need
                    }
                }
            })

        return model
    }
}