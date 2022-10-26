package com.example.androidprofessional.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidprofessional.R
import com.example.androidprofessional.databinding.ItemTranslationLayoutBinding
import com.example.module.data.DataModel
import com.example.utils.DiffUtils

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<DataModel>,
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var isFavourite: Boolean = false

    fun setData(newListData: List<DataModel>) {
        val callback = DiffUtils(data, newListData)
        val result = DiffUtil.calculateDiff(callback)
        result.dispatchUpdatesTo(this)
        this.data = newListData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTranslationLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemTranslationLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerTextViewRecyclerItem.text = data.text
                binding.descriptionTextViewRecyclerItem.text =
                    data.meanings?.joinToString { it.translation?.translation.toString() }
                binding.transcriptionTextView.text = "[${data.meanings?.first()?.transcription}]"
                itemView.setOnClickListener { openInNewWindow(data) }
                binding.imageViewAddToFav.setOnClickListener {
                    if (!isFavourite) {
                        onListItemClickListener.addToFav(data)
                        binding.imageViewAddToFav.setImageResource(R.drawable.ic_fav_active)
                        isFavourite = true
                    } else {
                        onListItemClickListener.deleteFromFav(data)
                        binding.imageViewAddToFav.setImageResource(R.drawable.ic_fav_not_active)
                        isFavourite = false
                    }
                    binding.imageViewAddToFav.startAnimation(
                        AnimationUtils.loadAnimation(
                            binding.root.context,
                            R.anim.shake
                        )
                    )
                }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
        fun addToFav(data: DataModel)
        fun deleteFromFav(data: DataModel)
    }
}