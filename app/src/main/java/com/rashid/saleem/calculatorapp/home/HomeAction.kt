package com.rashid.saleem.calculatorapp.home

import org.jetbrains.annotations.Range

sealed interface HomeAction {
    data object Clear: HomeAction
    data object Backspace: HomeAction
    data class Operation(val value: OperationEnum): HomeAction
    data class Number( val value: Int): HomeAction
    data object DecimalPoint: HomeAction
    data object DoubleZero: HomeAction
    data object Calculate: HomeAction

}

enum class OperationEnum(symbol: String) {
    PLUS("+"),
    SUBTRACT("-"),
    MULTIPLY("X"),
    DIVIDE("/"),
    PERCENTAGE("%"),
}