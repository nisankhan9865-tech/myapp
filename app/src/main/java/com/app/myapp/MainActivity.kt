package com.app.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.myapp.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    CalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel = viewModel()) {
    val display by viewModel.display.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = display,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 8.dp),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(16.dp))
        CalculatorKeypad(
            onDigitClick = viewModel::onDigitClick,
            onOperationClick = viewModel::onOperationClick,
            onEqualsClick = viewModel::onEqualsClick,
            onClearClick = viewModel::onClearClick
        )
    }
}

@Composable
fun CalculatorKeypad(
    onDigitClick: (String) -> Unit,
    onOperationClick: (String) -> Unit,
    onEqualsClick: () -> Unit,
    onClearClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            CalculatorButton("7") { onDigitClick("7") }
            CalculatorButton("8") { onDigitClick("8") }
            CalculatorButton("9") { onDigitClick("9") }
            CalculatorButton("/") { onOperationClick("/") }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            CalculatorButton("4") { onDigitClick("4") }
            CalculatorButton("5") { onDigitClick("5") }
            CalculatorButton("6") { onDigitClick("6") }
            CalculatorButton("*") { onOperationClick("*") }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            CalculatorButton("1") { onDigitClick("1") }
            CalculatorButton("2") { onDigitClick("2") }
            CalculatorButton("3") { onDigitClick("3") }
            CalculatorButton("-") { onOperationClick("-") }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            CalculatorButton("C") { onClearClick() }
            CalculatorButton("0") { onDigitClick("0") }
            CalculatorButton("=") { onEqualsClick() }
            CalculatorButton("+") { onOperationClick("+") }
        }
    }
}

@Composable
fun CalculatorButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(80.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Text(text = text, style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onPrimaryContainer)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyAppTheme {
        CalculatorScreen()
    }
}