package com.rashid.saleem.calculatorapp.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rashid.saleem.calculatorapp.ui.theme.CalculatorAppTheme
import com.rashid.saleem.calculatorapp.ui.theme.LightBlue1
import com.rashid.saleem.calculatorapp.ui.theme.LightBlue2

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel<HomeViewModel>(),
    ) {

    val uiState = viewModel.uiState.collectAsState().value

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        TextContainer(
            uiState = uiState,
            modifier = Modifier.weight(1f)
        )
        ButtonsContainer(
            onAction = viewModel::onAction
        )
    }
}

@Composable
private fun TextContainer(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End,
    ) {
        Text(
            text = uiState.value1 + (uiState.operation?.symbol ?: "") + uiState.value2,
            fontSize = 36.sp,
            lineHeight = 36.sp,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = uiState.result,
            fontSize = 26.sp,
            lineHeight = 26.sp,
            color = Color.Gray,
        )
    }
}

@Composable
private fun ButtonsContainer(onAction: (HomeAction) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(text = "C", modifier = Modifier.weight(1f),
            onClick = { onAction(HomeAction.Clear) }
        )
        TextButton(text = "%", modifier = Modifier.weight(1f),
            onClick = { onAction(HomeAction.Operation(OperationEnum.PERCENTAGE)) }
        )
        TextButton(text = "<-", modifier = Modifier.weight(1f),
            onClick = { onAction(HomeAction.Backspace) }
        )
        TextButton(text = "/", modifier = Modifier.weight(1f), textColor = LightBlue2,
            onClick = { onAction(HomeAction.Operation(OperationEnum.DIVIDE)) }
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(text = "7", modifier = Modifier.weight(1f),
            onClick = { onAction(HomeAction.Number(7)) }
        )
        TextButton(text = "8", modifier = Modifier.weight(1f),
            onClick = { onAction(HomeAction.Number(8)) }
        )
        TextButton(text = "9", modifier = Modifier.weight(1f),
            onClick = { onAction(HomeAction.Number(9)) }
        )
        TextButton(text = "x", modifier = Modifier.weight(1f), textColor = LightBlue2,
            onClick = { onAction(HomeAction.Operation(OperationEnum.MULTIPLY)) }
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(text = "4", modifier = Modifier.weight(1f),
            onClick = { onAction(HomeAction.Number(4)) }
        )
        TextButton(text = "5", modifier = Modifier.weight(1f),
            onClick = { onAction(HomeAction.Number(5)) }
        )
        TextButton(text = "6", modifier = Modifier.weight(1f),
            onClick = { onAction(HomeAction.Number(6)) }
        )
        TextButton(text = "-", modifier = Modifier.weight(1f), textColor = LightBlue2,
            onClick = { onAction(HomeAction.Operation(OperationEnum.SUBTRACT)) }
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(text = "1", modifier = Modifier.weight(1f),
            onClick = { onAction(HomeAction.Number(1)) }
        )
        TextButton(text = "2", modifier = Modifier.weight(1f),
            onClick = { onAction(HomeAction.Number(2)) }
        )
        TextButton(text = "3", modifier = Modifier.weight(1f),
            onClick = { onAction(HomeAction.Number(3)) }
        )
        TextButton(text = "+", modifier = Modifier.weight(1f), textColor = LightBlue2,
            onClick = { onAction(HomeAction.Operation(OperationEnum.PLUS)) }
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(text = "00", modifier = Modifier.weight(1f),
            onClick = { onAction(HomeAction.DoubleZero) }
        )
        TextButton(text = "0", modifier = Modifier.weight(1f),
            onClick = { onAction(HomeAction.Number(0)) }
        )
        TextButton(text = ".", modifier = Modifier.weight(1f),
            onClick = { onAction(HomeAction.DecimalPoint) }
        )
        TextButton(text = "=", modifier = Modifier.weight(1f), textColor = LightBlue2,
            onClick = { onAction(HomeAction.Calculate) }
        )
    }
}

@Composable
private fun TextButton(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Unspecified,
    onClick: () -> Unit,
) {
    Text(
        text = text,
        modifier = Modifier.Companion
            .then(modifier)
            .background(LightBlue1)
            .clickable { onClick() }
            .padding(vertical = 36.dp),
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        color = textColor
    )
}

/**
@Preview
@Composable
private fun HomeScreenPreview() {
    CalculatorAppTheme {
        Surface(
            color = Color.White
        ) {
            val uiState = remember {
                HomeUiState(
                    value1 = "2",
                    value2 = "3",
                    operation = OperationEnum.PLUS,
                    result = "5"
                )
            }


            HomeScreen(
                uiState = uiState,
                onAction = { }
            )
        }
    }
}
*/