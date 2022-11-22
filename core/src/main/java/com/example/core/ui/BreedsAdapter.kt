package com.example.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.model.Breeds
import com.example.core.databinding.ItemListBinding

class BreedsAdapter(private val onClick: (Breeds) -> Unit) :
    RecyclerView.Adapter<BreedsAdapter.FavoriteViewHolder>() {

    private val list = ArrayList<Breeds>()

    fun setList(newList: List<Breeds>?) {
        if (newList == null) return
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class FavoriteViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Breeds) {
            with(binding) {
                tvBreed.text = item.name
                tvScience.text = item.origin
                if (item.urlImage != null) {
                    Glide.with(itemView.context)
                        .load(item.urlImage)
                        .into(imgItem)
                }
                itemView.setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }
    }
}