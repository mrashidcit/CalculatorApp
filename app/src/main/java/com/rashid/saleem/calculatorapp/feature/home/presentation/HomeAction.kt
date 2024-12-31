package com.rashid.saleem.calculatorapp.feature.home.presentation

sealed interface HomeAction {
    data class Number(val value: Int): HomeAction
    data object DoubleZero: HomeAction
    data object Clear: HomeAction
    data object Backspace: HomeAction
    data object DecimalPoint: HomeAction
    data object Calculate: HomeAction
    data class Operation(val value: OperationEnum): HomeAction
}

enum class OperationEnum(val symbol: String) {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("x"),
    DIVIDE("/"),
    PERCENTAGE("%"),
}