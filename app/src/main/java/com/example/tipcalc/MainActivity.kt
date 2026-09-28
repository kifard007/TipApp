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
    var resultText by remember { mutableStateOf("") }

    val dishes = dishesText.toIntOrNull() ?: 0
    val discount = when {
        dishes in 1..2 -> 3
        dishes in 3..5 -> 5
        dishes in 6..10 -> 7
        dishes > 10 -> 10
        else -> 0
    }

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

            DiscountRadio("3%", discount == 3)
            DiscountRadio("5%", discount == 5)
            DiscountRadio("7%", discount == 7)
            DiscountRadio("10%", discount == 10)
        }

        Button(
            onClick = {
                val sum = sumText.replace(',','.').toDoubleOrNull()
                val dishesCount = dishesText.toIntOrNull()

                if (sum == null || dishesCount == null || sum<0 || dishesCount <= 0) {
                    resultText = "Введите корректные сумму и количество блюд"
                    return@Button

                }
                val discountPercent = when {
                    dishesCount in 1..2 -> 3
                    dishesCount in 3..5 -> 5
                    dishesCount in 6..10 -> 7
                    else -> 10
                }

                val tip = tipPercent.roundToInt()
                val discountAmount = sum * discountPercent / 100.0
                val sumWithDiscount = sum - discountAmount
                val tipAmount = sumWithDiscount * tip / 100.0
                val total = sumWithDiscount + tipAmount

                resultText = buildString {
                    append("Скидка: $discountPercent%\n")
                    append("Сумма скидки: %.2f\n".format(discountAmount))
                    append("Сумма со скидкой: %.2f\n".format(sumWithDiscount))
                    append("Чаевые: $tip%% = %.2f\n".format(tipAmount))
                    append("Итого: %.2f".format(total))
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Рассчитать")
        }

        if (resultText.isNotEmpty()) {
            Text(resultText, fontSize = 18.sp)
        }
    }
}

@Composable
fun DiscountRadio(label: String, selected: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = selected,onClick = null, enabled = false)
        Text(label)
    }
}

@Preview(showBackground = true)
@Composable
fun TipCalcScreenPreview() {
    MaterialTheme {
        TipCalcScreen()
    }
}