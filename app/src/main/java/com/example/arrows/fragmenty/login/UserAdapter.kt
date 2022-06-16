package com.example.arrows.fragmenty.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.arrows.R
import com.example.arrows.database.User
import com.example.arrows.databinding.ItemBinding

/**
 * UserAdapter - praca s recycleView
 */

//              (private val clickListener: UserListener)
class UserAdapter: ListAdapter<User, UserAdapter.ViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!) //, clickListener

    }

    class ViewHolder private constructor(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) { //, clickListener: UserListener
            val res = itemView.context.resources
            val meno = item.meno
            val score = item.score

            binding.user = item
            binding.klikatko.text = res.getString(R.string.vypis, meno, score)
            binding.klikatko.setOnClickListener {
                val frag = itemView.findFragment<LoginFragment>()
                frag.onClick(meno, score, binding.klikatko, res)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class UserDiffCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}

class UserListener(val clickListener: (userId: Int) -> Unit) {
    fun onClick(user: User) {
        clickListener(user.userId)
    }
}