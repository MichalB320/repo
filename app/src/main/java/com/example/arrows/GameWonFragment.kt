package com.example.arrows

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.arrows.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {
    private val activityModel: ActivityViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<FragmentGameWonBinding>(inflater, R.layout.fragment_game_won, container, false)
        binding.nieButton.setOnClickListener { activity?.finish() }
        binding.anoButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment)
        }
        binding.pokracovatBotton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_gameWonFragment_to_levelFragment5)
        }
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_USER

        binding.vyhralScore!!.text = getString(R.string.ziskal_si, activityModel.score.value)
        
        return binding.root
    }
}