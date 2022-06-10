package com.example.arrows.gameOver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.arrows.R
import com.example.arrows.databinding.FragmentOverBinding

class GameOverFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentOverBinding>(inflater, R.layout.fragment_over, container, false)
        binding.okButton.setOnClickListener { activity?.finish() }
        return binding.root
    }
}