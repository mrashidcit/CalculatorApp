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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rashid.saleem.calculatorapp.ui.theme.CalculatorAppTheme
import com.rashid.saleem.calculatorapp.ui.theme.LightBlue1
import com.rashid.saleem.calculatorapp.ui.theme.LightBlue2

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp)
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = "2+2",
                fontSize = 36.sp,
                lineHeight = 36.sp,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "4",
                fontSize = 26.sp,
                lineHeight = 26.sp,
                color = Color.Gray,
            )
        }



        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(text = "C", modifier = Modifier.weight(1f), onClick = { })
            TextButton(text = "%", modifier = Modifier.weight(1f), onClick = { })
            TextButton(text = "<-", modifier = Modifier.weight(1f), onClick = { })
            TextButton(text = "/", modifier = Modifier.weight(1f), textColor = LightBlue2, onClick = { })
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(text = "7", modifier = Modifier.weight(1f), onClick = { })
            TextButton(text = "8", modifier = Modifier.weight(1f), onClick = { })
            TextButton(text = "9", modifier = Modifier.weight(1f), onClick = { })
            TextButton(text = "x", modifier = Modifier.weight(1f), textColor = LightBlue2, onClick = { })
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(text = "4", modifier = Modifier.weight(1f), onClick = { })
            TextButton(text = "5", modifier = Modifier.weight(1f), onClick = { })
            TextButton(text = "6", modifier = Modifier.weight(1f), onClick = { })
            TextButton(text = "-", modifier = Modifier.weight(1f), textColor = LightBlue2, onClick = { })
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(text = "1", modifier = Modifier.weight(1f), onClick = { })
            TextButton(text = "2", modifier = Modifier.weight(1f), onClick = { })
            TextButton(text = "3", modifier = Modifier.weight(1f), onClick = { })
            TextButton(text = "+", modifier = Modifier.weight(1f), textColor = LightBlue2, onClick = { })
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(text = "00", modifier = Modifier.weight(1f), onClick = { })
            TextButton(text = "0", modifier = Modifier.weight(1f), onClick = { })
            TextButton(text = ".", modifier = Modifier.weight(1f), onClick = { })
            TextButton(text = "=", modifier = Modifier.weight(1f), textColor = LightBlue2, onClick = { })
        }
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

@Preview
@Composable
private fun HomeScreenPreview() {
    CalculatorAppTheme {
        Surface(
            color = Color.White
        ) {
            HomeScreen()
        }
    }
}