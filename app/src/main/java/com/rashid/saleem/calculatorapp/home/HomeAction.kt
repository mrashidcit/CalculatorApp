package com.rashid.saleem.calculatorapp.home

sealed interface HomeAction {
    data object Clear: HomeAction
    data object Backspace: HomeAction
    data object DoubleZero: HomeAction
    data object DecimalPoint: HomeAction
    data object Calculate: HomeAction
    data class Number(val value: Int): HomeAction
    data class Operation(val value: OperationEnum): HomeAction
}

enum class OperationEnum(val symbol: String) {
    PLUS("+"),
    SUBTRACT("-"),
    MULTIPLY("x"),
    DIVIDE("/"),
    PERCENTAGE("%")
}