package com.madeean.day14.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.madeean.day14.Utils.API_KEY
import com.madeean.day14.data.model.ModelDetailData
import com.madeean.day14.data.model.ModelListData
import com.madeean.day14.data.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class MainRepository {
    val model = MutableLiveData<List<ModelDetailData>>()

    fun getListProduct(): MutableLiveData<List<ModelDetailData>> {

        RetrofitClient.apiInterface.getPopularMovies(API_KEY,"en-US",1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : io.reactivex.Observer<ModelListData> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: ModelListData) {
                    println(t.results)
                    model.value = t.results
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