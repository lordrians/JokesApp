package com.example.jokesapp.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.jokesapp.R
import com.example.jokesapp.data.source.local.entity.JokesEntity
import com.example.jokesapp.databinding.ItemJokesBinding
import com.example.jokesapp.ui.MainActivity
import kotlinx.android.synthetic.main.item_jokes.view.*
import net.cachapa.expandablelayout.ExpandableLayout

class JokesAdapter : RecyclerView.Adapter<JokesAdapter.ViewHolder>(){
    private val UNSELECTED = -1
    private var selectedItem = UNSELECTED
    private lateinit var recyclerView: RecyclerView
    private var listData = ArrayList<JokesEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<JokesEntity>){
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemJokesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(listData[position])
    }

    override fun getItemCount(): Int = this.listData.size


    inner class ViewHolder(private val binding: ItemJokesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(joke: JokesEntity){
            binding.apply {
                tvSetup.text = joke.setup
                tvPunchline.text = joke.punchline

                tvSetup.setOnClickListener {
                    tvSetup.isSelected = true

                    if (selectedItem != UNSELECTED){
                        val holder = recyclerView.findViewHolderForAdapterPosition(selectedItem) as ViewHolder

                        if (holder.binding != null){

                            holder.binding.tvSetup.isSelected = false
                            holder.binding.expandableLayout.collapse()
                        }
                    }

                    if (absoluteAdapterPosition == selectedItem){
                        selectedItem = UNSELECTED
                    } else {
                        selectedItem = absoluteAdapterPosition
                        expandableLayout.expand()
                    }
                }
            }



        }
    }
}