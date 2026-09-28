package com.example.tipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Сумма заказа:")
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Введите сумму") },
            singleLine = true
        )

        Text("Количество блюд:")
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Введите количество") },
            singleLine = true
        )

        Text("Процент чаевых:")
        Text("10%", fontSize = 18.sp)
        Slider(
            value = 10f,
            onValueChange = {},
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