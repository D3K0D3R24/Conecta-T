package com.example.conectate_movil.ui.screens.singup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.conectate_movil.R
import com.example.conectate_movil.ui.screens.inicio_sesion.ui.MyHomePageLogin


@Composable
fun SignUpView(onClick: () -> Unit, istab: Boolean, singUpViewModel: SingUpViewModel) {
    val scrollSate = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBFBFF)),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp) // Ajusta la altura según necesites
                .align(Alignment.BottomStart)
                .clip(RoundedCornerShape(topStart = 80.dp, topEnd = 80.dp))
                .background(Color(0xFF00ACC1)) // Color similar al de tu imagen
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 40.dp, end = 40.dp, top = 16.dp)
                .verticalScroll(scrollSate),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val isLoading by singUpViewModel.isLoading.observeAsState(false)
            if (isLoading) {
                CircularProgressIndicator()
            }else{
                Spacer(modifier = Modifier.height(40.dp))
                // Logo
                Surface(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(190.dp)
                ) {
                    MyImageLogo(
                        painter = painterResource(id = R.drawable.conectate_logo),
                        contentScale = ContentScale.FillWidth,
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Formulario de registro
                FormCard(istab ,onClick, singUpViewModel)

                Spacer(modifier = Modifier.height(24.dp))

                // Pie de la vista
                Text(
                    text = "CONECT@T® 2024\ny todos sus afiliados",
                    fontSize = 12.sp,
                    color = Color(0xFFFFFFFF) // 0xFFA6C0C4
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }


}


@Composable
fun FormCard(istab: Boolean, onClick: () -> Unit, singUpViewModel: SingUpViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    var showInfoDialog by remember { mutableStateOf(false) }
    val isGetDataCorrect by singUpViewModel.isGetDataCorrect.collectAsState()



    LaunchedEffect(isGetDataCorrect) {
        if (isGetDataCorrect) {
            //showDialog = true
            onClick()
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
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            // Titulo del formulario
            Text(
                text = "SIGN UP",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF33ACC1),
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    // MOdificar el padding para que arriba y abajo tenga 16 y a los lados 0
                    .padding(start = 24.dp, end = 24.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {

                // Alias
                Text(
                    text = "Datos en la app",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFA6C0C4)
                )
                Spacer(modifier = Modifier.height(5.dp))

                //var alias by remember { mutableStateOf("") }
                val alias: String by singUpViewModel.alias.observeAsState("")
                val pass: String by singUpViewModel.password.observeAsState("")
                val confPass: String by singUpViewModel.confirmPassword.observeAsState("")
                val isBtnEn: Boolean by singUpViewModel.isBtnEnabled.observeAsState(false)
                val nombreCliente: String by singUpViewModel.nombreCliente.observeAsState("")
                val telefono : String by singUpViewModel.telefono.observeAsState("")
                val direccion : String by singUpViewModel.direccion.observeAsState("")
                val email: String by singUpViewModel.email.observeAsState("")
                val error by singUpViewModel.error.collectAsState()

                InputField(
                    title = "Alias",
                    previewText = "Alias",
                    placeholderText = "ej. Oxxo \"ALUX\"",
                    textValue = alias,
                    icon = Icons.Filled.Person,
                    trailingIcon = Icons.Filled.Info,
                    onTrailingIconClick = { showInfoDialog = true },
                    onValueChange = {singUpViewModel.onChangeValueSingUp(it, pass, confPass)}
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Contraseña
                //var password by remember { mutableStateOf("") }
                InputField(
                    title = "Contraseña",
                    previewText = "Contraseña",
                    placeholderText = "********",
                    textValue = pass,
                    icon = Icons.Filled.Person,
                    trailingIcon = Icons.Filled.Info,
                    isPassword = true,
                    onValueChange = {singUpViewModel.onChangeValueSingUp(alias, it, confPass)},
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Confirmar contraseña
                //var confirmPassword by remember { mutableStateOf("") }
                InputField(
                    title = "Confirmar contraseña",
                    previewText = "Confirmar contraseña",
                    placeholderText = "********",
                    textValue = confPass,
                    icon = Icons.Filled.Person,
                    trailingIcon = Icons.Filled.Info,
                    isPassword = true,
                    onValueChange = {singUpViewModel.onChangeValueSingUp(alias, pass, it)}
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Sus datos de contacto",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFA6C0C4)
                )
                Spacer(modifier = Modifier.height(5.dp))

                // Nombre completo

                //var nombre by remember { mutableStateOf("") }
                InputField(
                    title = "Nombre completo",
                    previewText = "",
                    placeholderText = "Manuel Alvarez",
                    enabled = false,
                    textValue = nombreCliente,
                    icon = Icons.Filled.Person,
                    trailingIcon = Icons.Filled.Info,
                    onValueChange = { it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Número telefónico
                //var telefono by remember { mutableStateOf("") }
                InputField(
                    title = "Número telefónico",
                    previewText = "",
                    placeholderText = "55 1234 5678",
                    enabled = false,
                    textValue = telefono,
                    icon = Icons.Filled.Call,
                    trailingIcon = Icons.Filled.Info,
                    onValueChange = { it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                //Dirección principal
                //var direccion by remember { mutableStateOf("") }
                InputField(
                    title = "Dirección de principal",
                    previewText = "",
                    placeholderText = "Calle 123, Colonia Centro, CDMX",
                    enabled = false,
                    textValue = direccion,
                    icon = Icons.Filled.LocationOn,
                    trailingIcon = Icons.Filled.Info,
                    onValueChange = {  it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Correo electrónico
                //var correo by remember { mutableStateOf("") }
                InputField(
                    title = "Correo electrónico",
                    previewText = "",
                    placeholderText = "ejemplo@gmail.com",
                    enabled = false,
                    textValue = email,
                    icon = Icons.Filled.Email,
                    trailingIcon = Icons.Filled.Info,
                    onValueChange = {  it }
                )

                Spacer(modifier = Modifier.height(24.dp))


                // Botón de registro
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Button(
                        enabled = isBtnEn,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF33ACC1), //si esta habilitado esta de este color
                            disabledContainerColor = Color(0xFFA6C0C4),//si esta inhabilitado esta de este color
                            contentColor = Color.White,
                            disabledContentColor = Color.White
                        ),
                        onClick = {
                            showDialog = true
                        }
                    ) {
                        Text(
                            text = "Registrarme", color = Color.White, fontSize = 18.sp
                        )
                    }
                }
                //establecer error
                error?.let {
                    Text(text = it, color = Color.Red, fontSize = if (istab) 20.sp else 14.sp)
                }

                if (showDialog) {
                    MyAlertDialog(
                        title = "Términos y Condiciones",
                        content = "Antes de registrarte en nuestra aplicación, es importante que leas y aceptes los siguientes Términos y Condiciones y nuestra Política de Privacidad.\n" +
                                "\n" +
                                "1. Uso de la Cuenta: Tu cuenta es personal e intransferible. Te comprometes a mantener la confidencialidad de tu nombre de usuario y contraseña, y serás responsable de cualquier actividad que se realice con tu cuenta.\n" +
                                "2. Protección de Datos: Valoramos tu privacidad. Los datos que nos proporcionas serán tratados conforme a nuestra Política de Privacidad, la cual puedes consultar en cualquier momento desde la aplicación. Tu información personal será utilizada únicamente para mejorar tu experiencia y no será compartida con terceros sin tu consentimiento expreso.\n" +
                                "3. Normas de Conducta: Al registrarte, te comprometes a utilizar la aplicación de manera responsable, respetando a otros usuarios y cumpliendo con las normativas aplicables. Cualquier comportamiento indebido podría resultar en la suspensión o eliminación de tu cuenta.\n" +
                                "4. Actualizaciones y Notificaciones: Al registrarte, aceptas recibir actualizaciones de la aplicación y notificaciones importantes. Puedes gestionar tus preferencias de notificación desde la configuración de tu cuenta en cualquier momento.\n" +
                                "\n" +
                                "Al hacer clic en \"Aceptar\", confirmas que has leído, comprendido y aceptas nuestros términos y políticas. Si no estás de acuerdo, lamentamos que no puedas completar tu registro.",
                        onDismiss = { showDialog = false},
                        singUpViewModel
                    )
                }

                // Mostrar diálogo cuando se presiona el ícono de información
                if (showInfoDialog) {
                    /*
                    MyAlertDialog(
                        title = "Información",
                        content = "El alias es el nombre que se mostrará en la aplicación. Puede ser tu nombre real o un apodo. Recuerda que no podrás cambiarlo después de registrarte.",
                        onDismiss = { showInfoDialog = false }
                    )
                     */

                    MyInfoDialog (
                        onDismiss = { showInfoDialog = false }
                    )
                }
            }
        }
    }
}

@Composable
fun MyImageLogo(painter: Painter, contentScale: ContentScale) {
    //agregamos imagen
    Image(
        modifier = Modifier.background(color = Color(0xFFEBFBFF)),
        painter = painter,
        contentDescription = null, // Descripción nula si no es necesaria para accesibilidad
        contentScale = contentScale,
    )
}

@Composable
fun InputField(
    title: String,
    previewText: String = "",
    placeholderText: String,
    enabled: Boolean = true,
    textValue: String,
    icon: ImageVector,
    trailingIcon: ImageVector,
    onTrailingIconClick: () -> Unit = {},
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {
    Column {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(5.dp))
        CustomTextField(
            previewText = previewText,
            placeholderText = placeholderText,
            enabled = enabled,
            value = textValue,
            onValueChange = onValueChange,
            icon = icon,
            trailingIcon = trailingIcon,
            onTrailingIconClick = onTrailingIconClick,
            isPassword = isPassword,
        )
    }
}


@Composable
fun CustomTextField(
    previewText: String,
    placeholderText: String = "Input text here",
    enabled: Boolean,
    value: String,
    icon: ImageVector,
    trailingIcon: ImageVector,
    onTrailingIconClick: () -> Unit = {},
    isPassword: Boolean, // Campo opcional que por defecto es falso
    onValueChange: (String) -> Unit,
) {
    // Estado para controlar la visibilidad de la contraseña
    var passwordVisible by remember { mutableStateOf(false) }

    if (!isPassword) {
        if (enabled)
        {
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = value,
                enabled = true,
                onValueChange = onValueChange,
                label = { Text(previewText, fontSize = 16.sp) },
                textStyle = TextStyle(
                    fontSize = 16.sp
                ),
                leadingIcon = {
                    Icon(imageVector = icon, contentDescription = null, tint = Color(0xFF33ACC1))
                },
                trailingIcon = {
                    IconButton(
                        onClick = { onTrailingIconClick() }  // Cambia la visibilidad del diálogo
                    ) {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = null,
                            tint = Color(0xFF33ACC1)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black, unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
                    cursorColor = Color.Black
                ),
                shape = RoundedCornerShape(5.dp),
                placeholder = { Text(placeholderText, fontSize = 16.sp) }
            )
        } else {
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = value,
                enabled = false,
                onValueChange = onValueChange,
                label = { Text(previewText, fontSize = 16.sp) },
                textStyle = TextStyle(
                    fontSize = 16.sp
                ),
                leadingIcon = {
                    Icon(imageVector = icon, contentDescription = null, tint = Color(0xFF33ACC1))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black, unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
                    cursorColor = Color.Black
                ),
                shape = RoundedCornerShape(5.dp),
                placeholder = { Text(placeholderText, fontSize = 16.sp) }
            )
        }

    } else {
        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            label = { Text(previewText, fontSize = 16.sp) },
            textStyle = TextStyle(
                fontSize = 16.sp
            ),

            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    } // Cambia la visibilidad de la contraseña
                ) {
                    Image(
                        painter = painterResource(
                            id = if (passwordVisible) R.drawable.login_show_pass else R.drawable.login_not_show_pass
                        ),
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

            singleLine = true, //de solo una linea,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black, unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
                cursorColor = Color.Black
            ),
            shape = RoundedCornerShape(5.dp),
            placeholder = { Text(placeholderText, fontSize = 16.sp) }
        )
    }
}

@Composable
fun MyAlertDialog(
    title: String,
    content: String,
    onDismiss: () -> Unit,
    singUpViewModel: SingUpViewModel
) {
    val scrollState = rememberScrollState()
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = title,
                color = Color(0xFF00414B)
            )
        },
        text = {
            Column(
                modifier = Modifier.verticalScroll(scrollState)
            ) {
                Text(content)
                // Text("Al hacer clic en \"Aceptar\", confirmas que has leído, comprendido y aceptas nuestros términos y políticas. Si no estás de acuerdo, lamentamos que no puedas completar tu registro.", fontWeight = FontWeight.Bold)
            }
        },
        confirmButton = {
            TextButton(onClick = {
                singUpViewModel.onClickBtn()
                onDismiss()
            }) {
                Text(
                    text = "Acepto",
                    color = Color(0xFF00414B)
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(
                    text = "No acepto",
                    color = Color(0xFF00414B)
                )
            }
        }
    )
}

@Composable
fun MyInfoDialog(onDismiss: () -> Unit) {
    val scrollState = rememberScrollState()
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Teléfono",
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Acerca de \"Alias\"",
                        color = Color(0xFF00414B),
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        text = {
            Column(
                modifier = Modifier.verticalScroll(scrollState)
            ) {
                Text(
                    "Para una experiencia más personalizada, le invitamos a crear un alias único que le identifique dentro de nuestra plataforma. Este nombre de usuario será su distintivo en todas sus interacciones."
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text("¿Para qué sirve?", fontWeight = FontWeight.Bold)

                // Aquí están los puntos con negrita
                BulletPoint(text = "Mayor privacidad:", description = " Puedes proteger tu identidad al usar un alias.")
                BulletPoint(text = "Facilidad de uso:", description = " Los alias pueden ser más cortos y fáciles de recordar.")
                BulletPoint(text = "Identidad en línea:", description = " Crea una identidad única en la aplicación.")

                Spacer(modifier = Modifier.height(10.dp))
                Text("Ejemplos:", fontWeight = FontWeight.Bold)
                Text("@OxxoALUX, @SIXJJPACHO, @CASA_DE_DAVID", fontWeight = FontWeight.Bold)
            }
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(
                    text = "Entendido",
                    color = Color(0xFF00414B)
                )
            }
        },
    )
}

@Composable
fun BulletPoint(text: String, description: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = "• ", modifier = Modifier.padding(end = 4.dp)) // Punto antes del texto
        Column {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(text)
                    }
                    append(description)
                }
            )
        }
    }
}
