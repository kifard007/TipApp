package com.example.tipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TipCalcScreen()
            }
        }
    }
}

@Composable
fun TipCalcScreen() {
    var sumText by remember { mutableStateOf("") }
    var dishesText by remember { mutableStateOf("") }
    var tipPercent by remember { mutableStateOf(10f) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Сумма заказа:")
        OutlinedTextField(
            value = sumText,
            onValueChange = {sumText = it},
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Введите сумму") },
            singleLine = true
        )

        Text("Количество блюд:")
        OutlinedTextField(
            value = dishesText,
            onValueChange = {dishesText = it},
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Введите количество") },
            singleLine = true
        )

        Text("Процент чаевых:")
        Text("${tipPercent.roundToInt()}", fontSize = 18.sp)
        Slider(
            value = tipPercent,
            onValueChange = {tipPercent = it},
            valueRange = 0f..25f,
            steps = 24
        )

        Text("Скидка:")
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = false, onClick = null, enabled = false)
            Text("3%")
            RadioButton(selected = false, onClick = null, enabled = false)
            Text("5%")
            RadioButton(selected = false, onClick = null, enabled = false)
            Text("7%")
            RadioButton(selected = false, onClick = null, enabled = false)
            Text("10%")
        }

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Рассчитать")
        }

        Text("Результат", fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun TipCalcScreenPreview() {
    MaterialTheme {
        TipCalcScreen()
    }
}