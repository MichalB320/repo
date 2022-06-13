package com.example.arrows.game

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.arrows.R
import com.example.arrows.databinding.FragmentGameBinding
import kotlin.math.roundToInt

class GameFragment : Fragment() {
    private val viewModel: GameViewModel by viewModels()
    private val kruh: KruhViewModel by viewModels()
    private val arrow: ArrowViewModel by viewModels()
    private var dlzkaHry = 9223372036854775805 //10min = 100000
    private lateinit var timer: CountDownTimer
    private lateinit var binding: FragmentGameBinding
    private lateinit var sipky: Array<ImageView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        binding.palButton.setOnClickListener { viewModel.onPal() }
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sipky = arrayOf(binding.arrow1, binding.arrow2, binding.arrow3, binding.arrow4, binding.arrow5)
        odpocitavaj()
        viewModel.score.observe(viewLifecycleOwner) { newScore ->
            binding.score.text = getString(R.string.score, newScore)
        }
        viewModel.currentArrowCount.observe(viewLifecycleOwner) { newArrowCount ->
            binding.wordCount.text = getString(R.string.arrow_count, newArrowCount, 5)
        }
        kruh.rotKruhu.observe(viewLifecycleOwner) { newRotKruhu ->
            binding.kruh.rotation = newRotKruhu
        }
        arrow.rotacia.observe(viewLifecycleOwner) { newRotSip: Array<Float> ->
            for ((index, element) in sipky.withIndex()) {
                element.rotation = newRotSip[index]
            }
        }
        viewModel.score.observe(viewLifecycleOwner) { newScore ->
            if (newScore == 5 * 15) {
                this.findNavController().navigate(R.id.action_gameFragment_to_gameWonFragment)
            }
        }
        viewModel.koliduje.observe(viewLifecycleOwner) { newKolission ->
            if (newKolission) {
                this.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
            }
        }
    }

    private fun odpocitavaj() {
        timer = object : CountDownTimer(dlzkaHry, 10) { //countDownInterval = 1
            override fun onTick(p0: Long) { //p0: Long
                dlzkaHry = p0
                tik()
            }

            override fun onFinish() {

            }
        }.start()
    }

    private fun tik() {
        kruh.rotuj()
        if (viewModel.stlacil) {
            val spicY = sipky[viewModel.index].y - 150 - 5 //binding.arrow1.y - 150 - 5//getSpicY(viewModel.index)
            val spicX = sipky[viewModel.index].x + 50 + 2 //binding.arrow1.x + 50 + 2//getSpicX(viewModel.index)
            val stredKruhuX = binding.kruh.x + kruh.getPolomerKruhu()
            val stredKruhuY = binding.kruh.y + 155f // 155 = polomerKruhu + bielaPlochaZaNim
            if (!arrow.jeZapichnuta(stredKruhuY, kruh.getPolomerKruhu(), spicY, spicX, stredKruhuX)) { //!jeZapichnuta(viewModel.index)
                sipky[viewModel.index].y = sipky[viewModel.index].y - arrow.getPohyb()
                arrow.setRect(viewModel.index, stredKruhuX.roundToInt() - 5, sipky[viewModel.index].y.toInt() - arrow.getPolkaSipky(), stredKruhuX.roundToInt() + 5, sipky[viewModel.index].y.toInt() + arrow.getPolkaSipky())
                //viewModel.pocitajSurSipky(viewModel.index)
            } else {
                viewModel.nestlacil()
                viewModel.pripocitajScore()
                viewModel.pocitadlo()
                if(!viewModel.stlacil) {
                    viewModel.zvysIndex()
                    if (viewModel.index < 5) {
                        sipky[viewModel.index].isVisible = true
                    }
                }
            }
        }
        rotujOkoloKruhu()
        for (i in 0..4) {
            if (arrow.koliduje(i)) {
                timer.cancel() // zastavy odpocet
                viewModel.prehral()
            }
        }
    }

    private fun rotujOkoloKruhu() {
        val stredKruhuY = binding.kruh.y + 155f // 155 = polomerKruhu + bielaPlochaZaNim
        val stredKruhuX = binding.kruh.x + kruh.getPolomerKruhu()
        for (i in 0..4) {
            val spicY = sipky[i].y - 150 - 5 //binding.arrow1.y - 150 - 5//getSpicY(i)
            val spicX = sipky[i].x + 50 + 2 //binding.arrow1.x + 50 + 2// getSpicX(i)
            //val stredKruhuX = binding.kruh.x + kruh.getPolomerKruhu()
            if (arrow.jeZapichnuta(stredKruhuY, kruh.getPolomerKruhu(), spicY, spicX, stredKruhuX)) { //jeZapichnuta(i)
                arrow.pocitajRotaciuSipky(i)

                sipky[i].y = stredKruhuY + arrow.getSurYSip(i) //binding.arrow1.y = getStredKruhuY() + viewModel.getSurYSip()
                sipky[i].x = stredKruhuX + arrow.getSurXSip(i) //binding.arrow1.x = getStredKruhuX() + viewModel.getSurXSip()

                if (arrow.jeZapichnuta(stredKruhuY, kruh.getPolomerKruhu(), spicY, spicX, stredKruhuX)) {
                    arrow.nastavKoliziu(i, stredKruhuX, stredKruhuY, kruh.getPolomerKruhu())
                }
            }
        }
    }
}