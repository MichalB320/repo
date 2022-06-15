package com.example.arrows.fragmenty.level

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.arrows.R
import com.example.arrows.database.User
import com.example.arrows.databinding.ItemBinding

//RecyclerView.Adapter<UserAdapter.ViewHolder>()
//                                                  ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback())
class UserAdapter(private val clickListener: UserListener): ListAdapter<User, UserAdapter.ViewHolder>(UserDiffCallback()) {
//    var data =  listOf<User>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
                                          //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = data[position]
//        holder.bind(item)
        holder.bind(getItem(position)!!, clickListener)

    }

//    override fun getItemCount() = data.size

    class ViewHolder private constructor(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        //private val tlacidlo: Button = itemView.findViewById(R.id.klikatko)

        fun bind(item: User, clickListener: UserListener) {
            val res = itemView.context.resources
            val meno = item.meno
            val score = item.score
//            tlacidlo.text = res.getString(R.string.vypis, meno, score)

            //binding.sleep = item
            binding.user = item
            binding.klikatko.text = res.getString(R.string.vypis, meno, score)
            //binding.clickListener = clickListener
            binding.klikatko.setOnClickListener {
//                val act = itemView.findFragment<LevelFragment>()
//                val application = requireNotNull(act.activity).application
//                val dataSource = UserDatabase.getInstance(application).userDatabaseDao
//                val viewModelFactory = LevelViewModelFactory(dataSource, application)
//                val model = ViewModelProvider(act, viewModelFactory).get(LevelViewModel:class.java)
//                val viewModel = ViewModelProvider(this, viewModelFactory).get(LevelViewModel::class.java)
                val frag = itemView.findFragment<LevelFragment>()
                frag.onClick(meno, score)
                binding.klikatko.setTextColor(res.getColor(R.color.white))
                binding.klikatko.setBackgroundColor(res.getColor(R.color.black))
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                //val view = layoutInflater.inflate(R.layout.item, parent, false)
                //return ViewHolder(view)

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