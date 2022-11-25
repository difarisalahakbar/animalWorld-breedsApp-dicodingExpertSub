package com.example.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.core.domain.model.Images
import com.example.core.databinding.SliderItemBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(private val onClick: (ArrayList<Images>) -> Unit): SliderViewAdapter<SliderAdapter.SlideViewHolder>() {

    private val list = ArrayList<Images>()
    private var isDetail = false

    fun setDetail(boolean: Boolean){
        isDetail = boolean
    }

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
        viewHolder?.itemView?.setOnClickListener {
            onClick.invoke(list)
        }
    }

    inner class SlideViewHolder(private val binding: SliderItemBinding): SliderViewAdapter.ViewHolder(binding.root){
        fun bind(item: Images){
            if(isDetail){
                binding.imageView2.visibility = View.VISIBLE
                binding.imageView.visibility = View.GONE
                Glide.with(itemView.context)
                    .load(item.urlImage)
                    .into(binding.imageView2)
            }else{
                binding.imageView2.visibility = View.GONE
                binding.imageView.visibility = View.VISIBLE
                Glide.with(itemView.context)
                    .load(item.urlImage)
                    .into(binding.imageView)
            }
        }
    }
}