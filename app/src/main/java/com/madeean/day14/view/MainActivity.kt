package com.madeean.day14.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.madeean.day14.R
import com.madeean.day14.Utils
import com.madeean.day14.data.model.ModelDetailData
import com.madeean.day14.databinding.ActivityMainBinding
import com.madeean.day14.view.adapter.MainAdapter
import com.madeean.day14.view.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
        setAdapter()
        observeData()
        binding.swlListProduct.setOnRefreshListener {
            checkAndGetData()
            binding.swlListProduct.isRefreshing = false
        }


    }

    private fun checkAndGetData() {
        if (Utils.isNetworkAvailable(this)) {
            viewModel.getAllAlamat()
        } else {
            Toast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun observeData() {
        viewModel.getAllAlamat().observe(this) {
            print(it)
            if (it == null) return@observe
            adapter.updateData(it)
        }
    }

    private fun setAdapter() {
        adapter = MainAdapter()
        adapter.run {
            goToMoviewDetailClickListener = {
                val intent = Intent(this@MainActivity, DetailMovieActivity::class.java)
                intent.putExtra("id", it.id)
                startActivity(intent)
            }
        }
        binding.rvListProducts.layoutManager = LinearLayoutManager(this)
        binding.rvListProducts.adapter = adapter
    }
}