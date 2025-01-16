package com.rashid.saleem.calculatorapp.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            HomeAction.Backspace -> backSpace()
            HomeAction.Calculate -> performCalculate()
            HomeAction.Clear -> performClear()
            HomeAction.DecimalPoint -> enterDecimalPoint()
            HomeAction.DoubleZero -> enterDoubleZero()
            is HomeAction.Number -> enterNumber(action.value)
            is HomeAction.Operation -> enterOperation(action.value)
        }
    }

    private fun performCalculate() {
        val updatedValue1 = uiState.value.result
        updateUiState(
            value1 = updatedValue1,
            value2 = "",
            result = ""
        )
        updateOperationUiState(null)
    }

    private fun enterDoubleZero() {
        val shouldUpdateValue1 = uiState.value.operation == null

        if (shouldUpdateValue1) {
            val updatedValue1 = uiState.value.value1 + "00"
            updateUiState(value1 = updatedValue1)

            return
        }

        val updatedValue2 = uiState.value.value2 + "00"
        updateUiState(value2 = updatedValue2)

        calculateResult()
    }

    private fun enterDecimalPoint() {

        val shouldApplyFirstDecimalOnValue2 = uiState.value.let {
            it.operation != null && it.value2.isEmpty()
        }
        if (shouldApplyFirstDecimalOnValue2) {
            val updateValue2 = "0."
            updateUiState(value2 = updateValue2)
            return
        }

        if (uiState.value.value2.isNotEmpty()) {
            val isValue2AlreadyContainsDecimalPoint = uiState.value.value2.contains(".")
            if (!isValue2AlreadyContainsDecimalPoint) {
                val updateValue2 = uiState.value.value2 + "."
                updateUiState(value2 = updateValue2)
            }

            return
        }

        if (uiState.value.operation != null) return

        if (uiState.value.value1.isEmpty()) {
            val updatedValue1 = uiState.value.value1 + "0."
            updateUiState(value1 = updatedValue1)
            return
        }

        val isValue1AlreadyContainsDecimalPoint = uiState.value.value1.contains(".")
        if (!isValue1AlreadyContainsDecimalPoint) {
            val updatedValue1 = uiState.value.value1 + "."
            updateUiState(value1 = updatedValue1)
        }
    }

    private fun performClear() {
        updateUiState(
            value1 = "",
            value2 = "",
            result = ""
        )

        updateOperationUiState(null)
    }

    private fun calculateResult() {

        if (uiState.value.value2.isEmpty()) {
            updateUiState(result = "")
            return
        }

        val value1 = uiState.value.value1.toDouble()
        val value2 = uiState.value.value2.toDouble()
        val operation = uiState.value.operation
        val result = when (operation) {
            OperationEnum.PLUS -> value1 + value2
            OperationEnum.SUBTRACT -> value1 - value2
            OperationEnum.MULTIPLY -> value1 * value2
            OperationEnum.DIVIDE -> value1 / value2
            OperationEnum.PERCENTAGE -> (value2 / 100.0) * value1
            null -> return
        }

        val isAllInputValuesAreInteger = uiState.value.let {
            !it.value1.contains(".") && !it.value2.contains(".")
        }

        val newUiState = uiState.value.copy(
            result = if (isAllInputValuesAreInteger)
                result.toInt().toString()
            else
                result.toString()
        )
        _uiState.update { newUiState }
    }

    private fun enterOperation(value: OperationEnum) {
        updateOperationUiState(value)

    }

    private fun backSpace() {

        val shouldApplyOnValue2 = uiState.value.value2.isNotEmpty()
        if (shouldApplyOnValue2) {
            val updatedValue2 = uiState.value.value2.dropLast(1)
            updateUiState(value2 = updatedValue2)
            calculateResult()
            return
        }

        if (uiState.value.operation != null) {
            updateOperationUiState(null)
            return
        }

        val shouldApplyOnValue1 = uiState.value.value1.isNotEmpty()
        if (shouldApplyOnValue1) {
            val updateValue1 = uiState.value.value1.dropLast(1)
            updateUiState(value1 = updateValue1)
        }
    }

    private fun enterNumber(value: Int) {

        val shouldUpdateValue1 = uiState.value.operation == null

        if (shouldUpdateValue1) {
            val updatedValue1 = uiState.value.value1 + value.toString()
            updateUiState(value1 = updatedValue1)

            return
        }

        val updatedValue2 = uiState.value.value2 + value.toString()
        updateUiState(value2 = updatedValue2)

        calculateResult()

    }

    private fun updateUiState(
        value1: String? = null,
        value2: String? = null,
        result: String? = null,
    ) {
        val newUiState = uiState.value.let {
            it.copy(
                value1 = value1 ?: it.value1,
                value2 = value2 ?: it.value2,
                result = result ?: it.result
            )
        }
        _uiState.update { newUiState }
    }

    private fun updateOperationUiState(operation: OperationEnum?) {
        val newUiState = uiState.value.copy(
            operation = operation
        )
        _uiState.update { newUiState }
    }


}