package com.example.favouritescreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.favouritescreen.databinding.ItemFavouriteRecyclerBinding
import com.example.module.data.DataModel
import com.example.utils.DiffUtils

class FavouriteAdapter(var data: List<DataModel>, private var onListItemClickListener: IOnListItemClickListener) :
        RecyclerView.Adapter<FavouriteAdapter.ViewHolder>(), ItemTouchHelperAdapter {
    
    private var changeData = data.toMutableList()

    fun setFavoriteData(newListData: List<DataModel>) {
        val callback = DiffUtils(data.sortedWith(compareBy { it.text }), newListData)
        val result = DiffUtil.calculateDiff(callback)
        result.dispatchUpdatesTo(this)
        changeData = newListData.toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavouriteRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(changeData[position])
    }

    override fun getItemCount(): Int = changeData.size

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        changeData.removeAt(fromPosition).apply {
            changeData.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        changeData.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ViewHolder(val binding: ItemFavouriteRecyclerBinding) :
            RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.wordTextView.text = data.text
                binding.descriptionWordTextView.text =
                        data.meanings?.joinToString { it.translation?.translation.toString() }
                binding.transcriptionTextView.text = "[${data.meanings?.first()?.transcription}]"
                itemView.setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
                itemView.setOnLongClickListener { removeItem()
                true}
            }

        }


        private fun removeItem(){
            changeData.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }
    }



}