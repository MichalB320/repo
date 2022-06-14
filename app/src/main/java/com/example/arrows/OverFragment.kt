package com.example.arrows

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.arrows.databinding.FragmentOverBinding

class GameOverFragment : Fragment() {
    private val activityModel: ActivityViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<FragmentOverBinding>(inflater, R.layout.fragment_over, container, false)
        binding.okButton.setOnClickListener { activity?.finish() }
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_USER

        binding.zobrazovacScore.text = getString(R.string.nahral_si, activityModel.score.value)
        return binding.root
    }
}