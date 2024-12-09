package com.pulsedonor.app.adapters.introduction

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pulsedonor.app.models.introduction.IntroductionModel
import com.pulsedonor.app.databinding.ItemIntroViewPager2Binding

class IntroductionViewPager2Adapter(var context: Context) :
    ListAdapter<IntroductionModel, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemIntroViewPager2Binding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return IntroViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as IntroViewHolder).bind(
            getItem(position) as IntroductionModel,
            position
        )
    }

    inner class IntroViewHolder(var binding: ItemIntroViewPager2Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: IntroductionModel, position: Int) {
            binding.ivIntro.setImageResource(item.image)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<IntroductionModel>() {
        override fun areItemsTheSame(
            oldItem: IntroductionModel,
            newItem: IntroductionModel
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: IntroductionModel,
            newItem: IntroductionModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}
