package com.dev.caiovinicius.planejadordeviagens

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.dev.caiovinicius.planejadordeviagens.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val travelViewModel: TravelViewModel by viewModels()

    private val navController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fcvContent) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {

            navController.addOnDestinationChangedListener { _, destination, _ ->
                Log.d("MAINACTIVITYLOG", "onCreate: ${navController.currentDestination}")
                when (destination.id) {

                    R.id.distanceFragment -> {
                        btnNext.setOnClickListener {
                            val currentFragment = supportFragmentManager
                                .findFragmentById(R.id.fcvContent)
                                ?.childFragmentManager
                                ?.fragments
                                ?.firstOrNull() as? DistanceFragment

                            if (currentFragment != null) {
                                val distanceStr = currentFragment.getDistanceValue()
                                val distanceDouble = distanceStr.toDoubleOrNull() ?: 0.0

                                travelViewModel.setDistance(distanceDouble)

                                if (!travelViewModel.uiState.value.isDistanceValid) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Insira uma distância válida",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    return@setOnClickListener
                                }

                                navController.navigate(R.id.action_distanceFragment_to_consumptionFragment)
                                btnNext.apply {
                                    text = getString(R.string.proximo)
                                }
                                btnBack.apply {
                                    visibility = View.VISIBLE
                                }
                            }
                        }
                    }

                    R.id.consumptionFragment -> {
                        btnNext.setOnClickListener {
                            val currentFragment = supportFragmentManager
                                .findFragmentById(R.id.fcvContent)
                                ?.childFragmentManager
                                ?.fragments
                                ?.firstOrNull() as? ConsumptionFragment

                            if (currentFragment != null) {
                                val consumptionStr = currentFragment.getConsumptionValue()
                                val consumptionDouble = consumptionStr.toDoubleOrNull() ?: 0.0

                                travelViewModel.setConsumption(consumptionDouble)

                                if (!travelViewModel.uiState.value.isConsumptionValid) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Insira um consumo válido",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    return@setOnClickListener
                                }

                                navController.navigate(R.id.action_consumptionFragment_to_priceFragment)
                            }
                            btnBack.setOnClickListener {
                                navController.popBackStack()
                                btnNext.apply {
                                    text = getString(R.string.comecar)
                                }
                                btnBack.apply {
                                    visibility = View.GONE
                                }
                            }
                        }
                    }


                    R.id.priceFragment -> {
                        btnNext.setOnClickListener {

                            val currentFragment = supportFragmentManager
                                .findFragmentById(R.id.fcvContent)
                                ?.childFragmentManager
                                ?.fragments
                                ?.firstOrNull() as? PriceFragment

                            if (currentFragment != null) {
                                val priceStr = currentFragment.getPriceValue()
                                val priceDouble = priceStr.toDoubleOrNull() ?: 0.0

                                travelViewModel.setPrice(priceDouble)

                                if (!travelViewModel.uiState.value.isPriceValid) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Insira um preço válido",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    return@setOnClickListener
                                }

                                navController.navigate(R.id.action_priceFragment_to_resultFragment)
                                btnNext.apply {
                                    text = getString(R.string.planejar_outra_viagem)
                                }
                                btnBack.apply {
                                    visibility = View.GONE
                                }
                            }

                            btnBack.setOnClickListener {
                                navController.popBackStack()
                            }
                        }
                    }

                    R.id.resultFragment -> {
                        btnNext.setOnClickListener {
                            travelViewModel.resetState()

                            navController.navigate(R.id.action_resultFragment_to_distanceFragment)
                            btnNext.apply {
                                text = getString(R.string.comecar)
                            }
                            btnBack.apply {
                                visibility = View.GONE
                            }
                        }
                    }
                }
            }
        }
    }
}