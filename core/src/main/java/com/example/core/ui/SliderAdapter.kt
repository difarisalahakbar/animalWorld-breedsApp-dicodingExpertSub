package com.example.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.core.domain.model.Images
import com.example.core.databinding.SliderItemBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter: SliderViewAdapter<SliderAdapter.SlideViewHolder>() {

    private val list = ArrayList<Images>()

    fun setList(newList: List<Images>?){
        if(newList == null) return
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup?): SlideViewHolder {
       val binding = SliderItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
       return SlideViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: SlideViewHolder?, position: Int) {
        viewHolder?.bind(list[position])
    }

    class SlideViewHolder(private val binding: SliderItemBinding): SliderViewAdapter.ViewHolder(binding.root){
        fun bind(item: Images){
            Glide.with(itemView.context)
                .load(item.urlImage)
                .into(binding.imageView)
        }
    }
}