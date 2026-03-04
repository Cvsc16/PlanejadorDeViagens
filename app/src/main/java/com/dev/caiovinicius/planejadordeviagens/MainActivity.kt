package com.dev.caiovinicius.planejadordeviagens

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.dev.caiovinicius.planejadordeviagens.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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
                            navController.navigate(R.id.action_distanceFragment_to_consumptionFragment)
                            btnNext.apply {
                                text = getString(R.string.proximo)
                            }
                            btnBack.apply {
                                visibility = View.VISIBLE
                            }
                        }
                        btnBack.apply {
                            visibility = View.GONE
                        }
                    }

                    R.id.consumptionFragment -> {
                        btnNext.setOnClickListener {
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

                    R.id.priceFragment -> {
                        btnBack.setOnClickListener {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}