package com.example.arrows.level

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.arrows.ActivityViewModel
import com.example.arrows.R
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


        if (savedInstanceState != null) {
            binding.vypisovaciePole.setText(savedInstanceState.getString(SKUSANIE))
        }

        val application = requireNotNull(this.activity).application
        val dataSource = UserDatabase.getInstance(application).userDatabaseDao
        val viewModelFactory = LevelViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(LevelViewModel::class.java)
        binding.levelViewModel = viewModel
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
}