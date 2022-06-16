package com.example.arrows.fragmenty.login

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.arrows.R
import com.example.arrows.activity.ActivityViewModel
import com.example.arrows.database.UserDatabase
import com.example.arrows.databinding.FragmentLoginBinding

private const val SKUSANIE = "kluc"

/**
 * Fragment zapisovania uzivatelov
 */
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val activityModel: ActivityViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.playButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_levelFragment5_to_gameFragment)
        }

        if (savedInstanceState != null) {
            binding.vypisovaciePole.setText(savedInstanceState.getString(SKUSANIE))
        }

        val application = requireNotNull(this.activity).application
        val dataSource = UserDatabase.getInstance(application).userDatabaseDao
        val viewModelFactory = LoginViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        binding.levelViewModel = viewModel

//        val adapter = UserAdapter(UserListener { userId ->
//            viewModel.onUserClicked(userId)
//        })
        val adapter = UserAdapter()
        binding.usersList.adapter = adapter

        viewModel.users.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
        binding.usersList.layoutManager = GridLayoutManager(this.activity, 2)
        binding.lifecycleOwner = this

//        activityModel.score.observe(viewLifecycleOwner) { newScore ->
//            if (newScore != 0) {
//                viewModel.updateScore(newScore)
//            }
//        }

        binding.readyButton.setOnClickListener {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            viewModel.setPlayButtonVisibility()
            viewModel.setReadyButtonVisibility(false)
        }

        binding.submitButton.setOnClickListener {
            val text = binding.vypisovaciePole.text.toString()
            binding.vypisovaciePole.text = null
            val textTrue = getString(R.string.toastTRUE, text)
            val textFalse = getString(R.string.toastFASLE, text)
            viewModel.zapis(text, activity, textTrue, textFalse)
            activityModel.setMeno(text)
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SKUSANIE, binding.vypisovaciePole.text.toString())
    }

    /**
     * metoda ktora sa vykona na kliknute tlacidla v recycleView
     *
     * @param meno, score, button, res
     */
    fun onClick(meno: String, score: Int, button: Button, res: Resources) {
        val model = ViewModelProvider(this).get(LoginViewModel::class.java)
        if (!model.oznacil) {
            activityModel.meno.value = meno
            activityModel.score.value = score
            button.setTextColor(res.getColor(R.color.white))
            button.setBackgroundColor(res.getColor(R.color.black))
            model.onClick()
        }
    }
}