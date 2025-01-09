package com.rashid.saleem.calculatorapp.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            HomeAction.Backspace -> backSpace()
            HomeAction.Calculate -> TODO()
            HomeAction.Clear -> TODO()
            HomeAction.DecimalPoint -> TODO()
            HomeAction.DoubleZero -> TODO()
            is HomeAction.Number -> enterNumber(action.value)
            is HomeAction.Operation -> TODO()
        }
    }

    private fun backSpace() {
        val newUiState = uiState.value.let {
            it.copy(
                value1 = it.value1.dropLast(1)
            )
        }

        _uiState.update { newUiState }
    }

    private fun enterNumber(value: Int) {
        val newUiState = uiState.value.let {
            it.copy(
                value1 = it.value1 + value.toString()
            )
        }

        _uiState.update { newUiState }
    }

}