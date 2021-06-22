package com.example.jokesapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jokesapp.data.source.local.entity.JokesEntity
import com.example.jokesapp.databinding.ItemJokesBinding

class JokesAdapter : RecyclerView.Adapter<JokesAdapter.ViewHolder>(){

    private val UNSELECTED = -1
    private var selectedItem = UNSELECTED

    private var listData = ArrayList<JokesEntity>()


    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<JokesEntity>){
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }


    inner class ViewHolder (
        private val binding: ItemJokesBinding
        ): RecyclerView.ViewHolder(binding.root){
            fun bind(joke: JokesEntity){
                val position = absoluteAdapterPosition
                val isSelected = position == selectedItem
                with(binding){
                    tvSetup.text = joke.setup
                    tvPunchline.text = joke.punchline
                    root.setOnClickListener {
                        tvSetup.isSelected = false
                        expandableLayout.collapse()
                    }
                    val position = absoluteAdapterPosition
                    if (position == selectedItem){
                        selectedItem = UNSELECTED
                    } else {
                        expandableLayout.expand()
                        selectedItem = position
                    }
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemJokesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder((binding))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listData[position])

    override fun getItemCount(): Int = this.listData.size
}