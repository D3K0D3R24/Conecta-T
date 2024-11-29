package com.example.conectate_movil.ui.screens.forgot_password.ui



import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.conectate_movil.R
import com.example.conectate_movil.ui.screens.forgot_password.ui.Components.OTPInput
import com.example.conectate_movil.ui.screens.forgot_password.view_model.ForgottenPasswordViewModel
import com.example.conectate_movil.utils.globals_data.TokenRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun TwoVerificationScreen(
    isTablet: Boolean,
    forgotPasswordVM: ForgottenPasswordViewModel,
    onClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        forgotPasswordVM.start(TokenRepository.idclient.toString())
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBFBFF)),
        contentAlignment = Alignment.TopCenter
    ) {
        WaveShapePreview(R.drawable.token, "REVISA TU CORREO")
        TwoVerificationView(isTablet, forgotPasswordVM,onClick)
    }
}


@Composable
fun TwoVerificationView(
    isTablet: Boolean,
    forgotPasswordVM: ForgottenPasswordViewModel,
    onClick: () -> Unit
) {
    val scrollSate = rememberScrollState()

//    val isLoading: Boolean by forgotPasswordVM.isLoading.observeAsState(false)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
            .verticalScroll(scrollSate),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //Encabezado
        Spacer(modifier = Modifier.padding(140.dp))

        //Contenido, formulario de id contrato
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            CustomContentToken(isTablet, forgotPasswordVM,onClick)
        }

        // Pie de la vista
        Text(
            text = "CONECT@T® 2024\ny todos sus afiliados",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF95D9D1)
        )
    }
}


@SuppressLint("DefaultLocale")
@Composable
fun CustomContentToken(
    istab: Boolean,
    forgotPasswordVM: ForgottenPasswordViewModel,
    onClick: () -> Unit
) {

    // Observar el tiempo restante y el estado del temporizador
    val timeLeft by remember { derivedStateOf { forgotPasswordVM.timeLeft } }
    //Lo formateamos a un valor de minutos y segundos
    val formattedTime by remember {
        derivedStateOf {
            val minutes = timeLeft / 60
            val seconds = timeLeft % 60
            String.format("%02d:%02d", minutes, seconds)
        }
    }
    val isGetDataCorrect: Boolean by forgotPasswordVM.isGetDataCorrect.collectAsState()

    LaunchedEffect(isGetDataCorrect) {
        if (isGetDataCorrect) {
            onClick()
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(14.dp),
                clip = true
            )
            .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(14.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 20.dp, start = 30.dp, end = 30.dp),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "A continuación, ingresa el código que hemos enviado a tu correo",
                color = Color(0xFF405084),
                fontSize = if (istab) 30.sp else 16.sp,
                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(15.dp))

//            CustomTokenTextField(istab, forgotPasswordVM)
            OTPInput(
                otpLength = 5,
                onOtpComplete = { otp ->
                    // Aquí puedes validar el OTP
                    println("OTP ingresado: $otp")

                    //LLamar para comprobar si9 el token es igual
                    forgotPasswordVM.verifyToken(otp)
                }
            )

            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = "Tiempo restante: $formattedTime",
                color = Color(0xFF53878F),
                fontSize = if (istab) 24.sp else 14.sp,
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "¿No recibiste el código?")

            Spacer(modifier = Modifier.padding(10.dp))
            CustomVerifyTokenButtonSend(istab, forgotPasswordVM)
        }
    }
}

@Composable
fun CustomVerifyTokenButtonSend(
    istab: Boolean,
    forgotPasswordVM: ForgottenPasswordViewModel,
) {
    val isButtonEnable: Boolean by forgotPasswordVM.isButtonEnable.observeAsState(false)
    val error by forgotPasswordVM.error.collectAsState()
    val corroutineScope = rememberCoroutineScope()
//    val isGetDataCorrect: Boolean by forgotPasswordVM.isGetDataCorrect.collectAsState()




    Button(
        enabled = isButtonEnable,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF05A7E), //si esta habilitado esta de este color
            disabledContainerColor = Color(0xFF58182D),//si esta inhabilitado esta de este color
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        onClick = {
            //volvemos a lanzar el mensaje
            corroutineScope.launch { forgotPasswordVM.start(TokenRepository.idclient.toString()) }
        }) {
        Text(
            text = "Reeenviar", color = Color.White, fontSize = if (istab) 30.sp else 18.sp
        )
    }
    error?.let {
        Text(text = it, color = Color.Red, fontSize = if (istab) 20.sp else 14.sp)
    }
}

//@Composable
//fun CustomTokenTextField(istab: Boolean, forgotPasswordVM: ForgottenPasswordViewModel) {
//    val valor: String by forgotPasswordVM.tokenInput.observeAsState("")
////    val input: String by forgotPasswordVM.filter(valor).observeAsState("")
//
//    TextField(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(if (istab) 80.dp else 55.dp),
//        value = valor, // aqui iria texto de ejemplo que estaria ingresando el usuario
//        placeholder = {
//            Text(
//                text = "00 00 00",
//                color = Color(0xFF49454F),
//                fontSize = if (istab) 30.sp else 16.sp,
//                textAlign = TextAlign.Center
//            )
//        },
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//        maxLines = 1,
//        singleLine = true,
//        textStyle = TextStyle(
//            color = Color(0xFFFAC183),
//            fontSize = if (istab) 30.sp else 20.sp,
//            textAlign = TextAlign.Center,
//            fontFamily = poppinsFontFamily,
//            fontWeight = FontWeight.Bold
//        ),
//        colors = TextFieldDefaults.colors(
//            focusedContainerColor = Color(0xFFEBFBFF),
//            unfocusedContainerColor = Color(0xFFC2EBF7),
//            focusedIndicatorColor = Color.Transparent,
//            unfocusedIndicatorColor = Color.Transparent,
//        ),
//        onValueChange = {
//            //aqui iria la logica para observar los cambios al ingresar datos y llama a la funcion
////            forgotPasswordVM.onTokenInputChange(it)
//                newValue ->
//            forgotPasswordVM.onTokenInputChange(newValue)
//        }
//    )
//}

// Función que formatea el texto con espacios cada dos caracteres
fun formatWithSpaces(input: String): String {
    return input.chunked(2).joinToString(" ")
}

// Función que ajusta la posición del cursor al añadir espacios
fun calculateCursorPosition(text: String, cursorPosition: Int): Int {
    // Contar el número de espacios que hay que añadir antes del cursor
    val spacesBeforeCursor = cursorPosition / 2
    return cursorPosition + spacesBeforeCursor
}











