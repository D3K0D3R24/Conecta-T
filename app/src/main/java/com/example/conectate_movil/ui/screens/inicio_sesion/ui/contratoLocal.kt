package com.example.conectate_movil.ui.screens.inicio_sesion.ui

import android.util.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.conectate_movil.R
import com.example.conectate_movil.ui.screens.inicio_sesion.view_model.InicioSesionViewModel

//@Preview(showBackground = true)

//NOTA: isForgotPassword es una variable que se utiliza para determinar si se esta llaman

@Composable
fun MyApp(istab: Boolean = false, idClientOrEmail: InicioSesionViewModel, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBFBFF)),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp) // Ajusta la altura según necesites
                .align(Alignment.TopStart)
                .clip(RoundedCornerShape(bottomStart = 80.dp, bottomEnd = 80.dp))
                .background(Color(0xFF00ACC1)) // Color similar al de tu imagen
        )

        MyHomePage(istab, idClientOrEmail, onClick)
    }
}

@Composable
fun MyHomePage(istab: Boolean, idClientOrEmail: InicioSesionViewModel, onClick: () -> Unit) {
    val scrollSate = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(if (istab) 90.dp else 20.dp)
            .verticalScroll(scrollSate),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        val isLoading: Boolean by idClientOrEmail.isLoading.observeAsState(false)

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            //Encabezado o logo
            Image(
                modifier = Modifier
                    .padding(10.dp)
                    .size(150.dp),
                painter = painterResource(id = R.drawable.conectate_logo),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )

            Spacer(modifier = Modifier.padding(10.dp))

            //Contenido, formulario de id contrato
            MyContentIdConrat(istab, idClientOrEmail, onClick)


            //Soporte tecnico
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = "Si crees que se trata de un error, comunícate con el soporte técnico",
                color = Color(0xFF00414B),
                fontSize = if (istab) 30.sp else 16.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(10.dp))
            ElevatedButton(
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color(0xFFEADDFF), // Color de fondo
                    contentColor = Color.White,         // Color del contenido (texto, íconos)
                    disabledContainerColor = Color.Gray, // Color de fondo cuando está deshabilitado
                    disabledContentColor = Color.LightGray // Color del contenido cuando está deshabilitado
                ),
                onClick = { }) {
                MyImageLogo(
                    painter = painterResource(id = R.drawable.baseline_support_agent_24),
                    contentScale = ContentScale.Fit
                )
            }

            //Pie de la vista
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = "CONECT@T® 2024\ny todos sus afiliados",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF95D9D1)
            )
        }
    }
}

@Composable
fun MyContentIdConrat(istab: Boolean, idClientOrEmail: InicioSesionViewModel, onClick: () -> Unit) {

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
                "Para comenzar, ingrese el correo electrónico con el que contrató nuestros servicios",
                color = Color(0xFF405084),
                fontSize = if (istab) 30.sp else 16.sp,
                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(20.dp))

            MyValClientTextField(istab, idClientOrEmail)

            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = "Ingrese su correo",
                color = Color(0xFF53878F),
                fontSize = if (istab) 24.sp else 14.sp,
                )
            Spacer(modifier = Modifier.padding(20.dp))
            MyButtonSend(istab, idClientOrEmail, onClick)
        }
        //}
    }
}

@Composable
fun MyAlertDialog(idClientOrEmail: InicioSesionViewModel) {
    AlertDialog(
        onDismissRequest = {
            idClientOrEmail.isDataValid(false)
        },
        title = {
            Text(
                text = "Error",
                color = Color(0xFF00414B)
            )
        },
        text = {
            Text(
                text = "El contrato no es valido",
                color = Color(0xFF53878F)
            )
        },
        confirmButton = {
            TextButton(onClick = {
                idClientOrEmail.isDataValid(false)

            }) {
                Text(
                    text = "OK",
                    color = Color(0xFF00414B)
                )
            }
        }
    )
}


@Composable
fun MyButtonSend(istab: Boolean, idClientOrEmail: InicioSesionViewModel, onClick: () -> Unit) {
    val isButtonEnable: Boolean by idClientOrEmail.isButtonEnable.observeAsState(false)
    //usando MutableStateFlow
    val error by idClientOrEmail.error.collectAsState()
    val isGetDataCorrect: Boolean by idClientOrEmail.isGetDataCorrect.collectAsState()


    LaunchedEffect(isGetDataCorrect) {
        if (isGetDataCorrect) {
            onClick()
        }
    }

    Button(
        enabled = isButtonEnable,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF05A7E), //si esta habilitado esta de este color
            disabledContainerColor = Color(0xFF58182D),//si esta inhabilitado esta de este color
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        onClick = {
            //prueba de que el contrato el falso o no es valido aun
            //reemplazar esta linea de codigo po uno donde corrobore que el contrato es valido y que exista en BD
            idClientOrEmail.onClickButton()
        }) {
        Text(
            text = "Identificarme", color = Color.White, fontSize = if (istab) 30.sp else 18.sp
        )
    }
    error?.let {
        Text(text = it, color = Color.Red, fontSize = if (istab) 20.sp else 14.sp)
    }
}

@Composable
fun MyValClientTextField(istab: Boolean, idClientOrEmail: InicioSesionViewModel) {
    val valor: String by idClientOrEmail.idClienteOrEmail.observeAsState("")

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
            //aqui iria la logica para observar los cambios al ingresar datos y llama a la funcion
            idClientOrEmail.onIdContractChange(it)
        })
}

@Composable
fun MyImageLogo(painter: Painter, contentScale: ContentScale) {
    //agregamos imagen
    Image(
        modifier = Modifier.padding(10.dp),
        painter = painter,
        contentDescription = null,
        contentScale = contentScale,
    )
}
