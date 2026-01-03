package com.app.myapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.BigDecimal
import java.math.MathContext

class CalculatorViewModel : ViewModel() {

    private val _display = MutableStateFlow("0")
    val display: StateFlow<String> = _display.asStateFlow()

    private var currentNumber: String = "0"
    private var operator: String? = null
    private var firstOperand: BigDecimal? = null
    private var waitingForOperand: Boolean = false

    fun onDigitClick(digit: String) {
        if (waitingForOperand) {
            currentNumber = digit
            waitingForOperand = false
        } else {
            if (currentNumber == "0" && digit != ".") {
                currentNumber = digit
            } else if (digit == "." && currentNumber.contains(".")) {
                // Do nothing if decimal already exists
            } else {
                currentNumber += digit
            }
        }
        _display.value = currentNumber
    }

    fun onOperationClick(op: String) {
        if (firstOperand == null) {
            firstOperand = BigDecimal(currentNumber)
        } else if (!waitingForOperand) {
            performOperation()
        }
        operator = op
        waitingForOperand = true
    }

    fun onEqualsClick() {
        if (firstOperand != null && operator != null && !waitingForOperand) {
            performOperation()
            operator = null
            waitingForOperand = true
        }
    }

    fun onClearClick() {
        currentNumber = "0"
        operator = null
        firstOperand = null
        waitingForOperand = false
        _display.value = "0"
    }

    private fun performOperation() {
        val secondOperand = BigDecimal(currentNumber)
        val result = when (operator) {
            "+" -> firstOperand?.add(secondOperand)
            "-" -> firstOperand?.subtract(secondOperand)
            "*" -> firstOperand?.multiply(secondOperand)
            "/" -> {
                if (secondOperand.compareTo(BigDecimal.ZERO) == 0) {
                    _display.value = "Error"
                    onClearClick()
                    return
                } else {
                    firstOperand?.divide(secondOperand, MathContext.DECIMAL128) // Handle division by zero
                }
            }
            else -> null
        }

        _display.value = result?.stripTrailingZeros()?.toPlainString() ?: "Error"
        currentNumber = _display.value
        firstOperand = result
    }
}