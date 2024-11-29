package com.example.conectate_movil.ui.screens.support.support.ui
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
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

@Composable
fun OrangeSupportView() {
    val scrollSate = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .verticalScroll(scrollSate),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        // Logo
        Surface(
            modifier = Modifier
                .padding(10.dp)
                .size(80.dp)
        ) {
            MyImageLogo(
                painter = painterResource(id = R.drawable.support_icon_logo),
                contentScale = ContentScale.FillWidth,
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Al hacer uso de este servicio, se avisará a la companía sobre tu problema, tendrás respuesta en un lapso de, máximo 24 horas y generaremos una orden de servicio técnico, por lo que es muy importante que confirmes tus datos, para poder ponernos en contacto contigo en la brevedad:",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Formulario de registro
        FormCard()

        Spacer(modifier = Modifier.height(24.dp))
    }
}


@Composable
fun FormCard() {
    var showDialog by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color(0xFFA6C0C4),
                shape = RoundedCornerShape(14.dp) // Borde redondeado
            )
            .background(
                Color.White
            ),

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
                text = "Sus datos de contacto",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFA6C0C4),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    // MOdificar el padding para que arriba y abajo tenga 16 y a los lados 0
                    .padding(start = 24.dp, end = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // Nombre completo
                var nombre by remember { mutableStateOf("") }
                InputField(
                    title = "Nombre completo",
                    placeholderText = "Manuel Alvarez",
                    textValue = nombre,
                    icon = Icons.Filled.Person,
                    onValueChange = { nombre = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Número telefónico
                var telefono by remember { mutableStateOf("") }
                InputField(
                    title = "Número telefónico",
                    placeholderText = "55 1234 5678",
                    textValue = telefono,
                    icon = Icons.Filled.Call,
                    onValueChange = { telefono = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Dirección de facturación
                var direccion by remember { mutableStateOf("") }
                InputField(
                    title = "Dirección de facturación",
                    placeholderText = "Calle 123, Colonia Centro, CDMX",
                    textValue = direccion,
                    icon = Icons.Filled.LocationOn,
                    onValueChange = { direccion = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Correo electrónico
                var correo by remember { mutableStateOf("") }
                InputField(
                    title = "Correo electrónico",
                    placeholderText = "ejemplo@gmail.com",
                    textValue = correo,
                    icon = Icons.Filled.Email,
                    onValueChange = { correo = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Botón de registro
                Button(
                    enabled = nombre.isNotEmpty() && telefono.isNotEmpty() && direccion.isNotEmpty() && correo.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF33ACC1), //si esta habilitado esta de este color
                        disabledContainerColor = Color(0xFFA6C0C4),//si esta inhabilitado esta de este color
                        contentColor = Color.White,
                        disabledContentColor = Color.White
                    ),
                    onClick = { showDialog = true }
                ) {
                    Text(
                        text = "Enviar", color = Color.White, fontSize = 18.sp
                    )
                }
                if (showDialog) {
                    MyAlertDialog(onDismiss = { showDialog = false })
                }
            }
        }
    }
}

@Composable
fun MyImageLogo(painter: Painter, contentScale: ContentScale) {
    //agregamos imagen
    Image(
        painter = painter,
        contentDescription = null, // Descripción nula si no es necesaria para accesibilidad
        contentScale = contentScale,
    )
}

@Composable
fun InputField(
    title: String,
    placeholderText: String,
    textValue: String,
    icon: ImageVector,
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
            previewText = title,
            placeholderText = placeholderText,
            value = textValue,
            onValueChange = onValueChange,
            icon = icon,
            isPassword = isPassword

        )
    }
}

@Composable
fun CustomTextField(
    previewText: String,
    placeholderText: String = "Input text here",
    value: String,
    icon: ImageVector,
    isPassword: Boolean, // Campo opcional que por defecto es falso
    onValueChange: (String) -> Unit,
) {
    // Estado para controlar si la contraseña se muestra o no
    var passwordVisible by remember { mutableStateOf(false) }

    if (!isPassword) {
        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            label = { Text(previewText, fontSize = 16.sp) },
            textStyle = TextStyle(
                fontSize = 16.sp
            ),
            leadingIcon = {

                Icon(imageVector = icon, contentDescription = null, tint = Color(0xFF33ACC1))

            },
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
fun MyAlertDialog(onDismiss: () -> Unit) {
    val scrollState = rememberScrollState()
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            )
            {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Teléfono",
                    modifier = Modifier.size(48.dp),
                    tint = Color.Gray // Cambia el color si lo necesitas
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Orden de servicio: 000213 ",
                    color = Color(0xFF00414B),
                    textAlign = TextAlign.Center,
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
            ) {
                Text("Nos contactaremos a la brevedad para poder atender su problema, gracias por usar nuestos servicios, disculpe las molestias.")
                 }
        },
        confirmButton = {
            TextButton(onClick = {
                onDismiss()
                //Esta parte deberia mandar al siguiente page
            }) {
                Text(
                    text = "Action 1",
                    color = Color(0xFF00414B)
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text(
                    text = "Entendido",
                    color = Color(0xFF00414B)
                )
            }
        }
    )
}

