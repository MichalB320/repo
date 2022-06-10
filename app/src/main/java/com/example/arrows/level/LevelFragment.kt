package com.example.arrows.level

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.arrows.R
import com.example.arrows.databinding.FragmentLevelBinding

class LevelFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentLevelBinding>(inflater, R.layout.fragment_level, container, false)
        binding.playButton.setOnClickListener { view: View ->
            //view.findNavController().navigate(R.id.action_levelFragment_to_gameFragment2)
        }
        return binding.root
    }
}