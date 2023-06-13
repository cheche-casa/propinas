package com.example.propinas.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.NumberFormat

class PropinasViewModel : ViewModel()  {
    private val _propinasUiState = MutableStateFlow(PropinasUiState())
    val propinasUiState: StateFlow<PropinasUiState> = _propinasUiState.asStateFlow()

    val amount = propinasUiState.value.amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent = propinasUiState.value.tipInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount, tipPercent, propinasUiState.value.roundUp)
}

private fun calculateTip(
    amount: Double,
    tipPercent: Double = 15.0,
    roundUp: Boolean,
): String {
    var tip = tipPercent / 100 * amount
    if (roundUp)
        tip = kotlin.math.ceil(tip)
    return NumberFormat.getCurrencyInstance().format(tip)
}