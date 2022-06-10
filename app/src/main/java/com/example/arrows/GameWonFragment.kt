package com.example.arrows.win

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.arrows.R
import com.example.arrows.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentGameWonBinding>(inflater, R.layout.fragment_game_won, container, false)
        binding.nieButton.setOnClickListener { activity?.finish() }
        binding.anoButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment)
        }
        binding.pokracovatBotton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_gameWonFragment_to_levelFragment5)
        }
        return binding.root
    }
}