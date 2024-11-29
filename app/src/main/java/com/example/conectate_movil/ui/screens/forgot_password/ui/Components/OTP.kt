package com.example.conectate_movil.ui.screens.forgot_password.ui.Components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun OTPInput(
    otpLength: Int = 6,
    onOtpComplete: (String) -> Unit
) {
    // Estado para cada dígito del OTP
    val otpValues = remember { mutableStateListOf(*Array(otpLength) { "" }) }

    // Lista de focus requesters para cada campo
    val focusRequesters = remember { List(otpLength) { FocusRequester() } }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .fillMaxWidth(),
//            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Crea un TextField para cada dígito
        for (index in 0 until otpLength) {
            OutlinedTextField(
                value = otpValues[index],
                onValueChange = { value ->
                    // Limita el valor a un solo carácter
                    if (value.length <= 1) {
                        otpValues[index] = value

                        // Si el campo no está vacío, avanza al siguiente
                        if (value.isNotEmpty()) {
                            if (index < otpLength - 1) {
                                focusRequesters[index + 1].requestFocus()
                            } else {
                                // OTP completo, oculta el teclado y llama al callback
                                keyboardController?.hide()
                                onOtpComplete(otpValues.joinToString(""))
                            }
                        } else if (index > 0) {
                            // Si está vacío y el usuario borra, regresa al anterior
                            focusRequesters[index - 1].requestFocus()
                        }
                    }
                },
                modifier = Modifier
                    .width(45.dp)
                    .focusRequester(focusRequesters[index]).background(Color(0xFFEBFBFF)),
                singleLine = true,
                maxLines = 1,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(
                    onNext = {
                        if (index < otpLength - 1) {
                            focusManager.moveFocus(FocusDirection.Next)
                        } else {
                            keyboardController?.hide()
                            onOtpComplete(otpValues.joinToString(""))
                        }
                    }
                )
            )
        }
    }

    // Enfoca automáticamente el primer campo cuando el Composable se carga
    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
    }
}