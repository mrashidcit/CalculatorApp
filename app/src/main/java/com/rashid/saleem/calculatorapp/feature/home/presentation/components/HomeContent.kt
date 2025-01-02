package com.rashid.saleem.calculatorapp.feature.home.presentation.components

import android.content.OperationApplicationException
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rashid.saleem.calculatorapp.core.presentation.theme.CalculatorAppTheme
import com.rashid.saleem.calculatorapp.core.presentation.theme.LightBlue1
import com.rashid.saleem.calculatorapp.core.presentation.theme.LightBlue2
import com.rashid.saleem.calculatorapp.feature.home.presentation.HomeAction
import com.rashid.saleem.calculatorapp.feature.home.presentation.HomeUiState
import com.rashid.saleem.calculatorapp.feature.home.presentation.OperationEnum
import com.rashid.saleem.calculatorapp.feature.home.presentation.previewHomeUiState

@Composable
fun HomeContent(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
    action: (HomeAction) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = uiState.value1 + (uiState.operation?.symbol ?: "") + uiState.value2,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right,
                fontSize = 46.sp,
                lineHeight = 46.sp,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = uiState.result,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Gray,
                textAlign = TextAlign.Right,
                fontSize = 26.sp,
            )
        }

        // Buttons
        ButtonsContainer(
            action = action
        )
    }
}

@Composable
private fun ButtonsContainer(
    modifier: Modifier = Modifier,
    action: (HomeAction) -> Unit,
) {
    Column(
        modifier = modifier
            .background(
                color = LightBlue1
            )
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(modifier = Modifier.weight(1f), text = "C",
                onClick = { action(HomeAction.Clear) }
            )
            TextButton(modifier = Modifier.weight(1f), text = "%",
                onClick = { action(HomeAction.Operation(OperationEnum.PERCENTAGE)) }
            )
            TextButton(modifier = Modifier.weight(1f), text = "<-",
                onClick = { action(HomeAction.Backspace) }
            )
            TextButton(modifier = Modifier.weight(1f), text = "/", color = LightBlue2,
                onClick = { action(HomeAction.Operation(OperationEnum.DIVIDE)) }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(modifier = Modifier.weight(1f), text = "7",
                onClick = {
                    action(HomeAction.Number(7))
                }
            )
            TextButton(modifier = Modifier.weight(1f), text = "8",
                onClick = {
                    action(HomeAction.Number(8))
                }
            )
            TextButton(modifier = Modifier.weight(1f), text = "9",
                onClick = {
                    action(HomeAction.Number(9))
                }
            )
            TextButton(modifier = Modifier.weight(1f), text = "x", color = LightBlue2,
                onClick = {
                    action(HomeAction.Operation(OperationEnum.MULTIPLY))
                }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(modifier = Modifier.weight(1f), text = "4",
                onClick = {
                    action(HomeAction.Number(4))
                }
            )
            TextButton(modifier = Modifier.weight(1f), text = "5",
                onClick = {
                    action(HomeAction.Number(5))
                }
            )
            TextButton(modifier = Modifier.weight(1f), text = "6",
                onClick = {
                    action(HomeAction.Number(6))
                }
            )
            TextButton(modifier = Modifier.weight(1f), text = "-", color = LightBlue2,
                onClick = {
                    action(HomeAction.Operation(OperationEnum.SUBTRACT))
                }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(modifier = Modifier.weight(1f), text = "1",
                onClick = {
                    action(HomeAction.Number(1))
                }
            )
            TextButton(modifier = Modifier.weight(1f), text = "2",
                onClick = {
                    action(HomeAction.Number(2))
                }
            )
            TextButton(modifier = Modifier.weight(1f), text = "3",
                onClick = {
                    action(HomeAction.Number(3))
                }
            )
            TextButton(modifier = Modifier.weight(1f), text = "+", color = LightBlue2,
                onClick = {
                    action(HomeAction.Operation(OperationEnum.ADD))
                }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(modifier = Modifier.weight(1f), text = "00",
                onClick = {
                    action(HomeAction.DoubleZero)
                }
            )
            TextButton(modifier = Modifier.weight(1f), text = "0",
                onClick = {
                    action(HomeAction.Number(0))
                }
            )
            TextButton(modifier = Modifier.weight(1f), text = ".",
                onClick = {
                    action(HomeAction.DecimalPoint)
                }
            )
            TextButton(modifier = Modifier.weight(1f), text = "=", color = LightBlue2,
                onClick = {
                    action(HomeAction.Calculate)
                }
            )
        }

    }
}

@Composable
private fun TextButton(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 24.sp,
    color: Color = Color.Unspecified,
    onClick: () -> Unit,
) {
    Text(
        modifier = modifier
            .clickable { onClick() }
            .padding(
                vertical = 36.dp
            ),
        text = text,
        textAlign = TextAlign.Center,
        fontSize = fontSize,
        color = color
    )
}

@Preview
@Composable
private fun HomeContentPreview() {
    CalculatorAppTheme {
        Surface(
            color = Color.White
        ) {

//            val uiState = previewHomeUiState()
            val uiState = HomeUiState(
                value1 = "123",
                operation = OperationEnum.ADD
            )

            HomeContent(
                uiState = uiState,
                action = { }
            )
        }
    }
}