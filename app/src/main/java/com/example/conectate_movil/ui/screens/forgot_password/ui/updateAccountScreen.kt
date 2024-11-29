package com.example.conectate_movil.ui.screens.forgot_password.ui

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.conectate_movil.R
import com.example.conectate_movil.ui.screens.forgot_password.view_model.ForgottenPasswordViewModel
import com.example.conectate_movil.ui.screens.singup.FormCard
import com.example.conectate_movil.ui.screens.singup.InputField
import com.example.conectate_movil.ui.screens.singup.MyAlertDialog
import com.example.conectate_movil.ui.screens.singup.MyImageLogo
import com.example.conectate_movil.ui.screens.singup.MyInfoDialog
import com.example.conectate_movil.ui.screens.singup.SingUpViewModel


@Composable
fun UpdateAccountScreen(
    isTablet: Boolean,
    forgotPasswordVM: ForgottenPasswordViewModel,
    onClick: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBFBFF)),
        contentAlignment = Alignment.TopCenter
    ) {
        WaveShapePreview(R.drawable.credentials, "CONFIRMA TUS DATOS")
        UpdateAccountView(isTablet, forgotPasswordVM,onClick)
    }
}

@Composable
fun UpdateAccountView(
    isTablet: Boolean,
    forgotPasswordVM: ForgottenPasswordViewModel,
    onClick: () -> Unit
) {
    val scrollSate = rememberScrollState()
    val isLoading: Boolean by forgotPasswordVM.isLoading.observeAsState(false)
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
            CustomUpdataAccountCard(isTablet, onClick, forgotPasswordVM)
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

@Composable
fun CustomUpdataAccountCard(
    istab: Boolean,
    onClick: () -> Unit,
    forgotPasswordVM: ForgottenPasswordViewModel,
){
    var showDialog by remember { mutableStateOf(false) }
    var showInfoDialog by remember { mutableStateOf(false) }
    val isGetDataCorrect by forgotPasswordVM.isGetDataCorrect.collectAsState()

    Card(
        modifier = Modifier
            .border(
                width = 0.dp,
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(14.dp), clip = true),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            //TITULO
            Text(
                text = "SUS DATOS",
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
                val alias: String by forgotPasswordVM.alias.observeAsState("")
                val pass: String by forgotPasswordVM.password.observeAsState("")
                val confPass: String by forgotPasswordVM.confirmPassword.observeAsState("")
                val isBtnEn: Boolean by forgotPasswordVM.isButtonEnable.observeAsState(false)
                val nombreCliente: String by forgotPasswordVM.nombreCliente.observeAsState("")
                val telefono : String by forgotPasswordVM.telefono.observeAsState("")
                val direccion : String by forgotPasswordVM.direccion.observeAsState("")
                val email: String by forgotPasswordVM.email.observeAsState("")
                val error by forgotPasswordVM.error.collectAsState()

                InputField(
                    title = "Alias",
                    previewText = "Alias",
                    placeholderText = "ej. Oxxo \"ALUX\"",
                    textValue = alias,
                    icon = Icons.Filled.Person,
                    trailingIcon = Icons.Filled.Info,
                    onTrailingIconClick = { showInfoDialog = true },
                    onValueChange = {forgotPasswordVM.onChangeValueSingUp(it, pass, confPass)}
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
                    onValueChange = {forgotPasswordVM.onChangeValueSingUp(alias, it, confPass)},
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
                    onValueChange = {forgotPasswordVM.onChangeValueSingUp(alias, pass, it)}
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
                Text(text = "Confirmas que todos los datos aqui proporcionados son reales para quien tiene el uso de esta cuenta:")

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
                        forgotPasswordVM
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
fun MyAlertDialog(
    title: String,
    content: String,
    onDismiss: () -> Unit,
    forgotPasswordVM: ForgottenPasswordViewModel
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
                forgotPasswordVM.onClickBtn()
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