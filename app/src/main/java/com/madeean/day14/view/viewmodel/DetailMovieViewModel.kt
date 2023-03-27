package com.madeean.day14.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.madeean.day14.data.model.ModelDetailData
import com.madeean.day14.data.model.ModelMovieDetail
import com.madeean.day14.repository.DetailMovieRepository
import com.madeean.day14.repository.MainRepository

class DetailMovieViewModel : ViewModel() {
    private val mMovieDetailRepository: DetailMovieRepository = DetailMovieRepository()

    fun getDetailMovie(id:Int): LiveData<ModelMovieDetail> = mMovieDetailRepository.getDetailMovie(id)
}