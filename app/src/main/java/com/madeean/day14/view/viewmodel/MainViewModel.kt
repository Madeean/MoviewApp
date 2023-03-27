package com.madeean.day14.view.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.madeean.day14.data.model.ModelDetailData
import com.madeean.day14.data.model.ModelListData
import com.madeean.day14.data.retrofit.RetrofitClient
import com.madeean.day14.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class MainViewModel : ViewModel() {
    private val mAlamatRepository: MainRepository = MainRepository()

    fun getAllAlamat(): LiveData<List<ModelDetailData>> = mAlamatRepository.getListProduct()

}