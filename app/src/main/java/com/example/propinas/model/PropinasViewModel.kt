package com.example.propinas.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class PropinasViewModel : ViewModel()  {
    private val _propinasUiState = MutableStateFlow(PropinasUiState())
    val propinasUiState: StateFlow<PropinasUiState> = _propinasUiState.asStateFlow()

//    val amount = propinasUiState.value.amountInput.toDoubleOrNull() ?: 0.0
    private var amount: Double = 0.0
    private var tipPercent = 0.0
    var tip = ""

    fun updateAmountInput(updateAmountInput: String) {
        _propinasUiState.update { currentState ->
            currentState.copy(
                amountInput = updateAmountInput
            )
        }
        amount = propinasUiState.value.amountInput.toDoubleOrNull() ?: 0.0
        tip = calculateTip(amount, tipPercent, propinasUiState.value.roundUp)
    }

    fun updateTipInput(updateTipInput: String) {
        _propinasUiState.update { currentState ->
            currentState.copy(
                tipInput = updateTipInput
            )
        }
        tipPercent = propinasUiState.value.tipInput.toDoubleOrNull() ?: 0.0
        tip = calculateTip(amount, tipPercent, propinasUiState.value.roundUp)
    }
    fun updateRoundUp(updateRoundUp: Boolean) {
        _propinasUiState.update { currentState ->
            currentState.copy(
                roundUp = updateRoundUp
            )
        }
        tip = calculateTip(amount, tipPercent, propinasUiState.value.roundUp)
    }
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
