package ru.avitotech.avitotestapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.avitotech.avitotestapp.R
import ru.avitotech.avitotestapp.databinding.ListItemBinding
import ru.avitotech.avitotestapp.model.DataNumber

class DataListAdapter(private val clickListener: DataNumberListener) :
    ListAdapter<DataNumber, DataListAdapter.ViewHolder>(DataNumberDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener, holder.itemView.context, position)
    }

    class ViewHolder private constructor(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: DataNumber,
            clickListener: DataNumberListener,
            context: Context,
            position: Int
        ) {
            binding.dataNumber = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
            if (position % 2 == 0) {
                binding.cardView.animation =
                    AnimationUtils.loadAnimation(context, R.anim.swing_up_left)
            } else {
                binding.cardView.animation =
                    AnimationUtils.loadAnimation(context, R.anim.swing_up_right)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding =
                    ListItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class DataNumberDiffCallback : DiffUtil.ItemCallback<DataNumber>() {

    override fun areItemsTheSame(oldItem: DataNumber, newItem: DataNumber): Boolean {
        return oldItem.number == newItem.number
    }

    override fun areContentsTheSame(oldItem: DataNumber, newItem: DataNumber): Boolean {
        return oldItem == newItem
    }
}

class DataNumberListener(val clickListener: (number: DataNumber) -> Unit) {

    fun onClick(number: DataNumber) = clickListener(number)
}
