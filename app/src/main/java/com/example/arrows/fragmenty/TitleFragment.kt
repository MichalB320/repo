package com.example.arrows.fragmenty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.arrows.R
import com.example.arrows.databinding.FragmentTitleBinding

/**
 * titulný fragmnet
 */
class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,
            R.layout.fragment_title, container, false)
        binding.tlacidlo.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_titleFragment3_to_levelFragment5)
        }

        return binding.root
    }
}