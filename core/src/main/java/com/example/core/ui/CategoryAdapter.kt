package com.example.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Category
import com.example.core.R
import com.example.core.databinding.ItemCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val list = ArrayList<Category>()
    private var index = 0

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<Category>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            if (position != 0) {
                Toast.makeText(holder.itemView.context, "Coming Soon", Toast.LENGTH_SHORT).show()
            }
        }

        if (index == position) {
            holder.binding.cardView.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.orange
                )
            )
        }
    }

    override fun getItemCount(): Int = list.size

    class CategoryViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Category) {
            with(binding) {
                tvCategory.text = item.text
                imgCategory.setImageResource(item.image)
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.white
                    )
                )
            }
        }
    }
}