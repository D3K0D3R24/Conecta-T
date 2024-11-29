package com.example.conectate_movil.ui.screens.inicio_sesion.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.conectate_movil.R
import com.example.conectate_movil.ui.screens.inicio_sesion.view_model.LoginViewModel

@Composable
fun MyLoginApp(
    isTablet: Boolean,
    loginViewModel: LoginViewModel,
    onclick: () -> Unit,
    onclick1: () -> Unit,
    onclickForgotPassword: () -> Unit
) {
    val isLoading: Boolean by loginViewModel.isLoading.observeAsState(false)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBFBFF)),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp) // Ajusta la altura según necesites
                    .align(Alignment.BottomStart)
                    .clip(RoundedCornerShape(topStart = 80.dp, topEnd = 80.dp))
                    .background(Color(0xFF00ACC1)) // Color similar al de tu imagen
            )
            MyHomePageLogin(isTablet, loginViewModel, onclick, onclick1, onclickForgotPassword)

        }
    }
}


//@Preview(showBackground = true)
@Composable
fun MyHomePageLogin(
    isTablet: Boolean,
    loginViewModel: LoginViewModel,
    onclick: () -> Unit,
    onclick1: () -> Unit,
    onclickForgotPassword: () -> Unit
) {

    val scrollSate = rememberScrollState()
    Box(

        modifier = Modifier
            .fillMaxSize()
            .padding(if (isTablet) 120.dp else 20.dp)
            .verticalScroll(scrollSate), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {
            Image(
                modifier = Modifier
                    .size(if (isTablet) 120.dp else 70.dp)
                    .align(Alignment.Start),
                painter = painterResource(id = R.drawable.conectate_logo),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
            Image(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.login_image_01),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            //Contenido -- FORM DE LOGIN --
            MyContentLogin(isTablet, loginViewModel, onclick, onclickForgotPassword)

            if (isTablet)Spacer(Modifier.padding(20.dp)) else Spacer(Modifier.padding(10.dp))
            // Crear cuentas
            Text(
                "¿No tienes una cuenta?",
                fontSize = if (isTablet) 25.sp else 16.sp,
                fontWeight = FontWeight.Bold
            )
            if (isTablet)Spacer(Modifier.padding(5.dp))
            OutlinedButton(
                border = BorderStroke(1.dp, Color(0xFFFFFFFF)),
                onClick = { onclick1() }) {
                Text(
                    "Crear cuenta",
                    color = Color(0xFFFFFFFF),
                    fontSize = if (isTablet) 25.sp else 16.sp
                )
            }
            // Pie de la vista
            if (isTablet)Spacer(Modifier.padding(10.dp)) else Spacer(Modifier.padding(5.dp))
            Text(
                text = "CONECT@T® 2024\ny todos sus afiliados",
                fontSize = if (isTablet) 22.sp else 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D6360)
            )
        }

    }

}


@Composable
fun MyContentLogin(
    isTablet: Boolean,
    loginViewModel: LoginViewModel,
    onclick: () -> Unit,
    onclickForgotPassword: () -> Unit
) {
    val email: String by loginViewModel.loginEmail.observeAsState("")
    val password: String by loginViewModel.loginPassword.observeAsState("")
    val error by loginViewModel.error.collectAsState()
    val isGetDataCorrect: Boolean by loginViewModel.isGetDataCorrect.collectAsState()


    LaunchedEffect(isGetDataCorrect) {
        if (isGetDataCorrect) {
            onclick()
        }
    }

    Card(
        modifier = Modifier
            .border(
                width = 0.dp,
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(14.dp),
                clip = true
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(if (isTablet) 40.dp else 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "LOGIN",
                textAlign = TextAlign.Start,
                fontSize = if (isTablet) 30.sp else 18.sp,
                color = Color(0xFF33acc1),
                fontWeight = FontWeight.Bold,

                )
            Spacer(modifier = Modifier.padding(13.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Correo Electronico",
                textAlign = TextAlign.Start,
                fontSize = if (isTablet) 25.sp else 18.sp,
                color = Color(0xFF124747),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(5.dp))

            MyOutlinedTextFieldEmail(isTablet, email) {
                loginViewModel.onLoginTextFieldChange(
                    it, password
                )
            }
            Spacer(modifier = Modifier.padding(13.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Contraseña",
                textAlign = TextAlign.Start,
                fontSize = if (isTablet) 25.sp else 18.sp,
                color = Color(0xFF124747),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(5.dp))
            MyOutlinedTextFieldPaasword(isTablet, password) {
                loginViewModel.onLoginTextFieldChange(
                    email, it
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))

            TextButton(
                onClick = { onclickForgotPassword() },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "Olvidé mi contraseña",
                    color = Color(0xFF0D05B1),
                    fontSize = if (isTablet) 20.sp else 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            MyButtonLogin(isTablet, loginViewModel, onclick)

            error?.let {
                Text(text = it, color = Color.Red, fontSize = if (isTablet) 18.sp else 14.sp)
            }
        }
    }
}

@Composable
fun MyButtonLogin(isTablet: Boolean, loginViewModel: LoginViewModel, onclick1: () -> Unit) {
    val isButtonEnable: Boolean by loginViewModel.isButtonEnable.observeAsState(false)
    Button(colors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFFF05A7E), //si esta habilitado esta de este color
        disabledContainerColor = Color(0xFFA30101),//si esta inhabilitado esta de este color
        contentColor = Color.White, disabledContentColor = Color.White
    ), enabled = isButtonEnable, onClick = {


        loginViewModel.onClickLogin()

    }) {
        Text("Iniciar Sesión", fontSize = if (isTablet) 25.sp else 16.sp)
    }

}

@Composable
fun MyOutlinedTextFieldPaasword(
    isTablet: Boolean, password: String, onLoginTextFieldChange: (String) -> Unit
) {
    var isShowPass by remember { mutableStateOf(false) } //controla si se muestra contraseña o no
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = password,
        onValueChange = { onLoginTextFieldChange(it) },
        label = { Text("Contraseña", fontSize = if (isTablet) 24.sp else 16.sp) },
        textStyle = TextStyle(
            fontSize = if (isTablet) 25.sp else 16.sp
        ),
        visualTransformation = if (isShowPass) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image =
                if (isShowPass) painterResource(id = R.drawable.login_not_show_pass) else painterResource(
                    id = R.drawable.login_show_pass
                )
            IconButton(onClick = {
                isShowPass = !isShowPass
            }) {
                //Icon(imageVector = image, contentDescription = if (isShowPass) "" else "")
                Image(
                    painter = image,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                //Text("t", fontSize = 25.sp, textAlign = TextAlign.Center)
            }
        },
        //keyboardActions = KeyboardActions(onSearch = {}),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        //isError = true
        singleLine = true, //de solo una linea,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black, unfocusedTextColor = Color.Black,

            focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,

            cursorColor = Color.Black

        ),
        shape = RoundedCornerShape(10.dp),

        )
}


@Composable
fun MyOutlinedTextFieldEmail(
    isTablet: Boolean, email: String, onLoginTextFieldChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = email,
        onValueChange = {

            onLoginTextFieldChange(it)
        },
        label = { Text("Coreo electronico", fontSize = if (isTablet) 24.sp else 16.sp) },
        textStyle = TextStyle(
            fontSize = if (isTablet) 25.sp else 16.sp
        ),
        //visualTransformation = PasswordVisualTransformation(),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
        },
        //keyboardActions = KeyboardActions(onSearch = {}),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

        singleLine = true, //de solo una linea,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black, unfocusedTextColor = Color.Black,

            focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,

            cursorColor = Color.Black

        ),
        shape = RoundedCornerShape(10.dp),
        placeholder = { Text("example@gmail.com", fontSize = if (isTablet) 25.sp else 16.sp) }

    )

}