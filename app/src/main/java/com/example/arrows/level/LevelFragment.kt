package com.example.arrows.level

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.arrows.R
import com.example.arrows.databinding.FragmentLevelBinding

const val SKUSANIE = "kluc"

class LevelFragment : Fragment() {
    private lateinit var binding: FragmentLevelBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_level, container, false)
        binding.playButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_levelFragment5_to_gameFragment)
        }

        if (savedInstanceState != null) {
            binding.vypisovaciePole.setText(savedInstanceState.getString(SKUSANIE))
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SKUSANIE, binding.vypisovaciePole.text.toString()) //TODO zakazat fragmentom sa vracat spet
    }
}