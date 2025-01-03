package com.rashid.saleem.calculatorapp.home

data class HomeUiState(
    val value1: String = "",
    val value2: String = "",
    val operation: OperationEnum? = null,
    val result: String = "",
)
