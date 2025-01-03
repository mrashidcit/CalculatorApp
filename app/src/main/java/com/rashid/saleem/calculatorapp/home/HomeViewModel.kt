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
            HomeAction.Backspace -> performBackspace()
            HomeAction.Calculate -> calculate()
            HomeAction.Clear -> performClear()
            HomeAction.DecimalPoint -> enterDecimalPoint()
            HomeAction.DoubleZero -> enterDoubleZero()
            is HomeAction.Number -> enterNumber(action.value.toString())
            is HomeAction.Operation -> enterOperation(action.value)
        }
    }

    private fun calculate() {

        if(uiState.value.result.isEmpty()) return

        updateUiState(
            value1 = uiState.value.result,
            value2 = "",
            result = "",
        )
        updateOperationInUiState(null)
    }

    private fun enterDecimalPoint() {

        val shouldAddInValue2 = uiState.value.let {
            it.operation != null && it.value2.contains(".").not()
        }
        if (shouldAddInValue2) {
            val updatedValue2 = uiState.value.value2.let {
                appendDecimalInString(it)
            }
            updateUiState(
                value2 = updatedValue2
            )
            return
        }

        val shouldAddInValue1 = uiState.value.let {
            it.operation == null && it.value1.contains(".").not()
        }
        if (shouldAddInValue1) {
            val updatedValue1 = uiState.value.value1.let {
                appendDecimalInString(it)
            }
            updateUiState(
                value1 = updatedValue1
            )
        }

    }

    private fun appendDecimalInString(value: String) = if (value.isEmpty())
        "0."
    else
        "$value."

    private fun performClear() {
        updateUiState(
            value1 = "",
            value2 = "",
            result = "",
        )
        updateOperationInUiState(null)
    }

    private fun performBackspace() {

        val shouldApplyOnValue2 = uiState.value.value2.isNotEmpty()
        if (shouldApplyOnValue2) {
            updateUiState(
                value2 = uiState.value.value2.dropLast(1)
            )
            calculateResult()
            return
        }

        val shouldRemoveOperation = uiState.value.operation != null
        if(shouldRemoveOperation) {
            updateOperationInUiState(null)
            return
        }

        val shouldApplyOnValue1 = uiState.value.value1.isNotEmpty()
        if (shouldApplyOnValue1) {
            updateUiState(
                value1 = uiState.value.value1.dropLast(1)
            )
            return
        }
    }

    private fun enterDoubleZero() {
        enterNumber("00")
    }

    private fun enterOperation(value: OperationEnum) {


        val shouldAddOperationSymbol = uiState.value.value1.isNotEmpty()
        if (shouldAddOperationSymbol.not()) return

        val shouldAppendZeroAtTheEndOfValue1 = uiState.value.value1.endsWith(".")
        if(shouldAppendZeroAtTheEndOfValue1)
            updateUiState(
                value1 = uiState.value.value1 + "0"
            )

        updateOperationInUiState(value)
    }

    private fun enterNumber(value: String) {

        val shouldUpdateValue1 = uiState.value.operation == null
        if (shouldUpdateValue1) {
            updateUiState(
                value1 = uiState.value.value1 + value
            )
            return
        }

        updateUiState(
            value2 = uiState.value.value2 + value
        )

        calculateResult()

    }

    private fun calculateResult() {

        val shouldCalculateResult = uiState.value.let {
            it.value1.isNotEmpty() && it.operation != null && it.value2.isNotEmpty()
        }
        if (!shouldCalculateResult) {
            updateUiState(
                result = ""
            )
            return
        }

        val value1 = uiState.value.value1.toDouble()
        val value2 = uiState.value.value2.toDouble()

        val result = when (uiState.value.operation) {
            OperationEnum.PLUS -> value1 + value2
            OperationEnum.SUBTRACT -> value1 - value2
            OperationEnum.MULTIPLY -> value1 * value2
            OperationEnum.DIVIDE -> value1 / value2
            OperationEnum.PERCENTAGE -> (value2 / 100.0) * value1
            null -> 0.0
        }

        val shouldShowDecimalPointInResult = uiState.value.let {
            it.value1.contains(".") || it.value2.contains(".")
        }
        val presentableResult = if (shouldShowDecimalPointInResult)
            result.toString()
        else
            result.toString().split(".").firstOrNull() ?: "0"

        updateUiState(
            result = presentableResult
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
                result = result ?: it.result,
            )
        }
    }
    private fun updateOperationInUiState(
        operation: OperationEnum? = null,
    ) {
        _uiState.update {
            it.copy(
                operation = operation,
            )
        }
    }


}