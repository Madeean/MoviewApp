package com.madeean.day14.view.adapter

import android.content.Context
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.madeean.day14.data.model.ModelDetailData
import com.madeean.day14.data.model.ModelListData
import com.madeean.day14.databinding.ItemDataProductBinding
import java.util.Collections.addAll

class MainAdapter :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    val listData: ArrayList<ModelDetailData> = arrayListOf()
    var context: Context? = null
    var goToMoviewDetailClickListener: ((modelDetailData: ModelDetailData) -> Unit)? = null

    inner class MainViewHolder(private val binding: ItemDataProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(modelDetailData: ModelDetailData) {
            with(binding) {
                tvNamaProduct.text = modelDetailData.overview
                tvHargaDiskonProduct.text = modelDetailData.originalTitle
                tvHargaProduct.text = modelDetailData.title
                tvInfoStok.text = modelDetailData.releaseDate
                tvDiskon.visibility = View.GONE
                root.setOnClickListener {
                    goToMoviewDetailClickListener?.invoke(modelDetailData)
                }
            }
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${modelDetailData.posterPath}")
                .into(binding.ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        context = parent.context
        val binding =
            ItemDataProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    fun updateData(modelDetailData: List<ModelDetailData>) {
        listData.run {
            clear()
            addAll(modelDetailData)
        }
        notifyItemRangeChanged(0, listData.size)
    }
}