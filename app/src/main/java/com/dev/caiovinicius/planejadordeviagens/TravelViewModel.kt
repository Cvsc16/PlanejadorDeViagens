package com.dev.caiovinicius.planejadordeviagens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TravelViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TravelUiState())
    val uiState: StateFlow<TravelUiState> = _uiState.asStateFlow()

    fun setDistance(distance: Double) {
        _uiState.update { currentState ->
            currentState.copy(distance = distance)
        }
    }

    fun setConsumption(consumption: Double) {
        _uiState.update { currentState ->
            currentState.copy(consumption = consumption)
        }
    }

    fun setPrice(price: Double) {
        _uiState.update { currentState ->
            currentState.copy(price = price)
        }
    }

}