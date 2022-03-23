package com.rchang0501.rejuvenate.reminderlistfragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rchang0501.rejuvenate.data.Reminder
import com.rchang0501.rejuvenate.databinding.ReminderListItemBinding
import com.rchang0501.rejuvenate.viewmodels.RejuvenateViewModel

class ReminderListAdapter(val viewModel: RejuvenateViewModel, private val onReminderClicked: (Reminder) -> Unit): ListAdapter<Reminder, ReminderListAdapter.ReminderViewHolder>(DiffCallback) {

    class ReminderViewHolder(var binding: ReminderListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (reminder: Reminder){
            binding.apply{
                reminderTitle.text = reminder.title
                reminderDueDate.text = reminder.dueDate
                completedButton.setOnClickListener { Log.d("apdater", "${reminder.title} completed: ${reminder.isComplete}") }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderListAdapter.ReminderViewHolder {
        return ReminderViewHolder(
            ReminderListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReminderListAdapter.ReminderViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener{
            onReminderClicked(current)
        }

        holder.binding.completedButton.setOnClickListener { viewModel.changeCompleted(current) }

        holder.bind(current)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Reminder>(){
            override fun areItemsTheSame(oldReminder: Reminder, newReminder: Reminder): Boolean {
                return oldReminder == newReminder
            }

            override fun areContentsTheSame(oldReminder: Reminder, newReminder: Reminder): Boolean {
                return oldReminder.title == newReminder.title
            }

        }

    }

}