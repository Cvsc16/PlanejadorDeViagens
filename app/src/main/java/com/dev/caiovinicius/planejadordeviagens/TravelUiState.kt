package com.dev.caiovinicius.planejadordeviagens

data class TravelUiState (
    val distance: Double? = null,
    val consumption: Double? = null,
    val price: Double? = null,
) {
    val isDistanceValid: Boolean
        get() = distance != null && distance > 0.0

    val isConsumptionValid: Boolean
        get() = consumption != null && consumption > 0.0


    val isPriceValid: Boolean
        get() = price != null && price > 0.0

    val totalCost: Double?
        get() = if (distance != null && consumption != null && price != null) {
            (distance / consumption) * price
        } else null
}