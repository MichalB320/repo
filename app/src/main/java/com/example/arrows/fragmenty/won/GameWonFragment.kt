package com.example.arrows.fragmenty.won

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.arrows.activity.ActivityViewModel
import com.example.arrows.R
import com.example.arrows.database.UserDatabase
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
        val application = requireNotNull(this.activity).application
        val dataSource = UserDatabase.getInstance(application).userDatabaseDao
        val viewModelFactory = GameWonViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(GameWonViewModel::class.java)
        binding.gameWonViewModel = viewModel
        binding.lifecycleOwner = this
        activityModel.score.observe(viewLifecycleOwner) { newScore ->
            if (newScore != 0) {
                val meno = activityModel.meno.value.toString()
                viewModel.updateniscore(newScore, meno)
            }
        }
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_USER
        binding.vyhralScore.text = getString(R.string.ziskal_si, activityModel.score.value)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(Intent.ACTION_SEND)
        val score = activityModel.score.value
        val meno = activityModel.meno.value

        intent.type = getString(R.string.intent_type)
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.sprava, meno, score))
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}