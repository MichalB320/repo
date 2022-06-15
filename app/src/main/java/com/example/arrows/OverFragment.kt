package com.example.arrows

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.arrows.database.UserDatabase
import com.example.arrows.databinding.FragmentOverBinding
import com.example.arrows.over.GameOverViewModel
import com.example.arrows.over.GameOverViewModelFactory

class GameOverFragment : Fragment() {
    private val activityModel: ActivityViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<FragmentOverBinding>(inflater, R.layout.fragment_over, container, false)
        binding.okButton.setOnClickListener { activity?.finish() }
        val application = requireNotNull(this.activity).application
        val dataSource = UserDatabase.getInstance(application).userDatabaseDao
        val viewModelFactory = GameOverViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(GameOverViewModel::class.java)
        binding.gameOverViewModel = viewModel
        binding.lifecycleOwner = this
        activityModel.score.observe(viewLifecycleOwner) { newScore ->
            if (newScore != 0) {
                viewModel.updateniscore(newScore)
            }
        }
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_USER

        binding.zobrazovacScore.text = getString(R.string.nahral_si, activityModel.score.value)
        return binding.root
    }
}