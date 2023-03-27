package com.madeean.day14.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.madeean.day14.R
import com.madeean.day14.data.model.ModelMovieDetail
import com.madeean.day14.databinding.ActivityDetailMovieBinding
import com.madeean.day14.view.viewmodel.DetailMovieViewModel
import com.madeean.day14.view.viewmodel.MainViewModel

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var viewModel: DetailMovieViewModel
    private var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailMovieViewModel::class.java]
        getData()
        observeData()


    }

    private fun getData() {
        id = intent.getIntExtra("id", 0)
        viewModel.getDetailMovie(id)
    }

    private fun observeData() {
        viewModel.getDetailMovie(id).observe(this) {
            print(it)
            if (it == null) return@observe
            setData(it)
        }
    }

    private fun setData(it: ModelMovieDetail) {
        binding.run {
            tvJudul.text = it.originalTitle
            tvOverview.text = it.overview
            tvReleaseDate.text = it.releaseDate
            tvBudget.text = it.budget.toString()
            tvOriginalTitle.text = it.originalTitle
            tvStatus.text = it.status
        }
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${it.posterPath}")
            .into(binding.ivPoster)

        var string = ""
        for (i in it.genres) {
            string += i.name + ", "
        }
        binding.tvGenre.text = string
    }
}