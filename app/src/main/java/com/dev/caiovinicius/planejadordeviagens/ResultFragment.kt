package com.dev.caiovinicius.planejadordeviagens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dev.caiovinicius.planejadordeviagens.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    private val travelViewModel: TravelViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            val uiState = travelViewModel.uiState.value
            binding.tvConsumption.text = getString(R.string.resultado_consumo, uiState.consumption.toString())
            binding.tvPrice.text = getString(R.string.resultado_preco, uiState.price.toString())
            binding.tvTotalCost.text = getString(R.string.resultado_custo, uiState.totalCost.toString())
        }
    }
}