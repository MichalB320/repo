package com.example.arrows.fragmenty.level

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.arrows.R
import com.example.arrows.activity.ActivityViewModel
import com.example.arrows.database.UserDatabase
import com.example.arrows.databinding.FragmentLevelBinding

const val SKUSANIE = "kluc"

class LevelFragment : Fragment() {
    private lateinit var binding: FragmentLevelBinding
    //private val viewModel: LevelViewModel by viewModels()
    private val activityModel: ActivityViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_level, container, false)
        binding.playButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_levelFragment5_to_gameFragment)
        }

        binding.readyButton.setOnClickListener {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }


        if (savedInstanceState != null) {
            binding.vypisovaciePole.setText(savedInstanceState.getString(SKUSANIE))
        }

        val application = requireNotNull(this.activity).application
        val dataSource = UserDatabase.getInstance(application).userDatabaseDao
        val viewModelFactory = LevelViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(LevelViewModel::class.java)
        binding.levelViewModel = viewModel

        val adapter = UserAdapter(UserListener { userId ->
            viewModel.onUserClicked(userId)
            Log.i("LevelFragment", "klikol")
            //Toast.makeText(context, "hello", Toast.LENGTH_LONG).show()
        })

//        val adapter = UserAdapter(UserListener { nightId ->
//            Toast.makeText(context, "${nightId}", Toast.LENGTH_LONG).show()
//        })
        //binding.sleepList.adapter = adapter
        binding.usersList.adapter = adapter

        viewModel.users.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

//        viewModel.users.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                //adapter.data = it
//                adapter.submitList(it)
//            }
//        })

        binding.lifecycleOwner = this

        activityModel.score.observe(viewLifecycleOwner) { newScore ->
            if (newScore != 0) {
                viewModel.updateScore(newScore)
            }
        }

        binding.submitButton.setOnClickListener {
            val text = binding.vypisovaciePole.text.toString()
            val textTrue = getString(R.string.toastTRUE, text)
            val textFalse = getString(R.string.toastFASLE, text)
            viewModel.zapis(text, activity, textTrue, textFalse)
            activityModel.setMeno(text)
        }

        //binding.submitButton.isEnabled = true
        return binding.root
    }

//    private fun onClick() {
//        val text = binding.vypisovaciePole.text.toString()
//        val textTrue = getString(R.string.toastTRUE, text)
//        val textFalse = getString(R.string.toastFASLE, text)
//        viewModel.zapis(text, activity, textTrue, textFalse)
//        //activityModel.setMeno(text)
//    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SKUSANIE, binding.vypisovaciePole.text.toString()) //TODO zakazat fragmentom sa vracat spet
    }

    fun onClick(meno: String, score: Int) {
        val model = ViewModelProvider(this).get(LevelViewModel::class.java)
        model.onClick()
        activityModel.meno.value = meno
        activityModel.score.value = score
    }
}