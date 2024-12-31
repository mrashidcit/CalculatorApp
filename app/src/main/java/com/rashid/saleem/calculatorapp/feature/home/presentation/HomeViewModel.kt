package com.rashid.saleem.calculatorapp.feature.home.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel: ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            HomeAction.Backspace -> performBackspace()
            HomeAction.Calculate -> Unit
            HomeAction.Clear -> performClear()
            HomeAction.DecimalPoint -> enterDecimalPoint()
            HomeAction.DoubleZero -> enterNumber("00")
            is HomeAction.Number -> enterNumber(action.value.toString())
            is HomeAction.Operation -> updateOperationInUiState(
                operation = action.value
            )
        }
    }

    private fun enterDecimalPoint() {
        val isBothValuesAreEmpty = uiState.value.value1.isEmpty() && uiState.value.value1.isEmpty()
        if (isBothValuesAreEmpty) {
            updateUiState(
                value1 = "0."
            )
        }

        val shouldAddDecimalPointInValue2 = uiState.value.value1.isNotEmpty()
                && uiState.value.operation != null
        val isValue2Empty = uiState.value.value2.isEmpty()
        if (shouldAddDecimalPointInValue2 && isValue2Empty) {
            updateUiState(
                value2 = "0."
            )
            return
        }
        val isValue2AlreadyHaveDecimalPoint = uiState.value.value2.contains(".")
        if (shouldAddDecimalPointInValue2 && isValue2AlreadyHaveDecimalPoint.not()) {
            updateUiState(
                value2 = uiState.value.value2 + "."
            )
            return
        }

        val isValue1Empty = uiState.value.value1.isEmpty()
        if (isValue1Empty) {
            updateUiState(
                value1 = "0."
            )
            return
        }

        val isValue1AlreadyHaveDecimalPoint = uiState.value.value1.contains(".")
        if (isValue1AlreadyHaveDecimalPoint.not()) {
            updateUiState(
                value1 = uiState.value.value1 + "."
            )
        }


    }


    private fun performBackspace() {
        if (uiState.value.value2.isNotEmpty()) {
            updateUiState(
                value2 = uiState.value.value2.dropLast(1)
            )
            return
        }

        if (uiState.value.operation != null) {
            updateOperationInUiState(null)
            return
        }

        if (uiState.value.value1.isNotEmpty()) {
            updateUiState(
                value1 = uiState.value.value1.dropLast(1)
            )
        }
    }

    private fun performClear() {
        _uiState.update {
            it.copy(
                value1 = "",
                value2 = "",
                operation = null,
                result = ""
            )
        }
    }

    private fun enterNumber(value: String) {

        val shouldUpdateValue1 = uiState.value.operation == null
        if (shouldUpdateValue1) {
            val updatedValue1 = uiState.value.value1 + value
            updateUiState(
                value1 = updatedValue1
            )
            return
        }

        val updatedValue2 = uiState.value.value2 + value
        updateUiState(
            value2 = updatedValue2
        )

    }

    private fun updateUiState(
        value1: String? = null,
        value2: String? = null,
        result: String? = null,
    ) {
        _uiState.update {
            it.copy(
                value1 = value1 ?: it.value1,
                value2 = value2 ?: it.value2,
                result = result ?: it.result
            )
        }
    }

    private fun updateOperationInUiState(
        operation: OperationEnum? = null,
    ) {
        _uiState.update {
            it.copy(
                operation = operation
            )
        }
    }






}