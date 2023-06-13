package com.example.propinas.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class PropinasUiState (
    var amountInput: String = "",
    var tipInput: String = "",
    var roundUp: Boolean = false

)