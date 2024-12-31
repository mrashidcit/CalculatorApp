package com.rashid.saleem.calculatorapp.feature.home.presentation

data class HomeUiState(
    val value1: String = "",
    val value2: String = "",
    val operation: OperationEnum? = null,
    val result: String = "",
)

fun previewHomeUiState() = HomeUiState(
    value1 = "2",
    value2 = "5",
    operation = OperationEnum.ADD,
    result = "7"
)
