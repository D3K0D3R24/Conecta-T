package com.example.conectate_movil.ui.screens.forgot_password.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.conectate_movil.R
import com.example.conectate_movil.utils.globals_data.TokenRepository
import com.example.conectate_movil.ui.screens.forgot_password.view_model.ForgottenPasswordViewModel
import com.example.conectate_movil.ui.theme.poppinsFontFamily


@Composable
fun ForgotPasswordScreen(
    isTablet: Boolean,
    forgotPassWordVM: ForgottenPasswordViewModel,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBFBFF)),
        contentAlignment = Alignment.TopCenter
    ) {
        WaveShapePreview(R.drawable.auth, "VALIDACIÓN DEL CLIENTE")
        ForgotPasswordView(isTablet, forgotPassWordVM, onClick)
    }
}


@Composable
fun ForgotPasswordView(
    isTablet: Boolean,
    forgotPassWordVM: ForgottenPasswordViewModel,
    onClick: () -> Unit
) {
    val scrollSate = rememberScrollState()
    val isLoading: Boolean by forgotPassWordVM.isLoading.observeAsState(false)

    if (isLoading) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEBFBFF)),
            contentAlignment = Alignment.Center // Centra el contenido en el Box
        ) {
            CircularProgressIndicator()
        }

    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
                .verticalScroll(scrollSate),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //Encabezado
            Spacer(modifier = Modifier.padding(140.dp))

            //Contenido, formulario de id contrato
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(350.dp)
            ) {
                CustomContentIdContrat(
                    isTablet,
                    forgotPassWordVM,
                    onClick
                )
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

}

//Efecto ola
@Composable
fun WaveShape(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier
        .fillMaxWidth()) {
        val path = Path()
//        val shadowPath = Path()
        val rectangle = Path()

        // Definimos los puntos iniciales
        val width = size.width
        val height = size.height
        val waveHeight = height * 0.4f
//        val blurRadius = 30f
//        val shadowColor = Color.Black.copy(alpha = 0.45f)
//
//        // Dibujar la sombra (un poco más abajo que la ola principal)
//        shadowPath.moveTo(0f, height*0.35f)
//
//        shadowPath.quadraticTo(
//            width * 0.20f, -waveHeight*0.1f,  // Punto de control para la primera curvaf,-waveHeight,  // Punto de control para la primera curva,      // Punto de control para la primera curva
//            width * 0.5f, height*0.36f,// Punto final de la primera curva                      // Punto final de la primera curva de sombra
//        )
//        shadowPath.quadraticTo(
//            width * 0.75f, height*0.8f,  // Punto de control para la segunda curva,     // Punto de control para la segunda curva
//            width, height*0.35f                         // Punto final de la segunda curva de sombra
//        )
//
//        shadowPath.lineTo(width, height)
//        shadowPath.lineTo(0f, height)
//        shadowPath.close()
//
//        // Usar `drawIntoCanvas` con un `Paint` y `BlurMaskFilter`
//        drawIntoCanvas { canvas ->
//            val paint = android.graphics.Paint().apply {
//                // Sombra básica
//                color = shadowColor.toArgb()
//
//                // Aplicar el BlurMaskFilter para el desenfoque de la sombra
//                maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
//
//                // Agregar una sombra con desplazamiento y desenfoque (opcional)
//                setShadowLayer(20f, 10f, 10f, color)
//            }
//
//            // Dibuja la sombra con el desenfoque aplicado
//            canvas.nativeCanvas.drawPath(shadowPath.asAndroidPath(), paint)
//        }



        // Comienza en la esquina superior izquierda
        path.moveTo(0f, height*0.4f)

        // Dibujamos la ola
        path.quadraticTo(
            width * 0.20f, -waveHeight*0.1f,  // Punto de control para la primera curvaf,-waveHeight,  // Punto de control para la primera curva,      // Punto de control para la primera curva
            width * 0.5f, height*0.4f,// Punto final de la primera curva

        )
        path.quadraticTo(
            width * 0.75f, height*0.8f,  // Punto de control para la segunda curva,     // Punto de control para la segunda curva
             width, height*0.4f                       // Punto final de la segunda curva
        )

        // Cierra el Path en el borde inferior derecho
        path.lineTo(width, height)
        path.lineTo(0f, height)
        path.close()

        // Dibuja el Path con el color especificado
        drawPath(path = path, color = Color(0XFF33ACC1))


        // Dibujar la sombra (un poco más abajo que la ola principal)
        rectangle.moveTo(0f, height*0.35f)

        //Dibujamos un rectángulo rectangle(path=path)

    }
}

@Composable
fun RectangleBackground(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = Color(0XFF33ACC1)
    ) {

    }
}


@Composable
fun WaveShapePreview(drawableResId: Int, tittle: String) {
    Column {
        Spacer(Modifier.padding(top = 60.dp))
        Text(
            tittle,
            fontFamily = poppinsFontFamily,
            fontSize = 20.sp,
            color = Color(0XFF405084),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.padding(vertical = 10.dp))
        Image(
            painter = painterResource(id = drawableResId), // Reemplaza "ic_icon" por el nombre de tu archivo XML
            contentDescription = "Descripción del icono",
            modifier = Modifier
                .size(150.dp)
                .align(alignment = Alignment.CenterHorizontally) // Ajusta el tamaño según tus necesidades
        )
//        Spacer(Modifier.padding(40.dp))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            color = Color(0xFFEBFBFF)
        ) {
            WaveShape(
                modifier = Modifier.fillMaxWidth()
            )
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = Color(0xFFEBFBFF)
        ) {
            RectangleBackground(modifier = Modifier)
        }
    }

}

@Composable
fun CustomContentIdContrat(
    istab: Boolean,
    forgotPassWordVM: ForgottenPasswordViewModel,
    onClick: () -> Unit
) {

    //para mostrar el AlertDialog
    //val isLoading:Boolean by idClientOrEmail.isLoading.observeAsState(false)

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
                "Para continuar, ingrese el correo electrónico con el que contrató nuestros servicios",
                color = Color(0xFF405084),
                fontSize = if (istab) 30.sp else 16.sp,
                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(15.dp))

            CustomClientTextField(istab, forgotPassWordVM)

            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = "Ingrese su correo",
                color = Color(0xFF53878F),
                fontSize = if (istab) 24.sp else 14.sp,
            )
            Spacer(modifier = Modifier.padding(10.dp))
            CustomIdClientButtonSend(istab, forgotPassWordVM, onClick)
        }
        //}
    }
}

@Composable
fun CustomClientTextField(istab: Boolean, forgotPassWordVM: ForgottenPasswordViewModel) {
    val valor: String by forgotPassWordVM.idClienteOrEmail.observeAsState("")

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (istab) 80.dp else 55.dp),
        value = valor, // aqui iria texto de ejemplo que estaria ingresando el usuario
        placeholder = {
            Text(
                text = "example@gmail.com",
                color = Color(0xFF49454F),
                fontSize = if (istab) 30.sp else 16.sp
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(
            color = Color(0xFF49454F),
            fontSize = if (istab) 30.sp else 16.sp
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFEBFBFF),
            unfocusedContainerColor = Color(0xFFC2EBF7),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),

        onValueChange = {
            forgotPassWordVM.onIdContractChange(it)
        })
}

@Composable
fun CustomIdClientButtonSend(
    istab: Boolean,
    forgotPassWordVM: ForgottenPasswordViewModel,
    onClick: () -> Unit
) {
    val isButtonEnable: Boolean by forgotPassWordVM.isButtonEnable.observeAsState(false)
    val error by forgotPassWordVM.error.collectAsState()
    val isGetDataCorrect: Boolean by forgotPassWordVM.isGetDataCorrect.collectAsState()
    val idClientOrEmail by forgotPassWordVM.idClienteOrEmail.observeAsState("")

    LaunchedEffect(isGetDataCorrect) {
        if (isGetDataCorrect) {
            TokenRepository.idclient = idClientOrEmail
            onClick()
        }
    }

    Button(
        enabled = isButtonEnable,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF05A7E),
            disabledContainerColor = Color(0xFF58182D),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        onClick = {
            forgotPassWordVM.onClickButton()
        }) {
        Text(
            text = "Identificarme", color = Color.White, fontSize = if (istab) 30.sp else 18.sp
        )
    }
    error?.let {
        Text(text = it, color = Color.Red, fontSize = if (istab) 20.sp else 14.sp)
    }
}


