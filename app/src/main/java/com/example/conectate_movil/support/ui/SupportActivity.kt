package com.example.conectate_movil.support.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.conectate_movil.R
import com.example.conectate_movil.support.ui.ui.theme.BlueCake
import com.example.conectate_movil.support.ui.ui.theme.Conectate_MovilTheme

class SupportActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SupportApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SupportApp() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    )
    {
        Column {
            Back()

            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item {
                    Box (
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.support_icon_logo),
                            contentDescription = "Support Image",
                            modifier = Modifier
                                .size(80.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                }
                item {
                    Text(
                        text = "Para contactar con nuestro servicio técnico, bríndenos información sobre el problema",
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                }

                item {
                    Text(
                        text = "Descripción del problema:",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                item {
                    OutlineCard()
                }
            }
        }
    }
}

@Composable
fun Back()
{
    Box(modifier = Modifier.fillMaxWidth()
        .padding(top = 20.dp))
    {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ){
            IconButton(
                onClick = { /*TODO*/ }
            )
            {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "Soporte Técnico"
            )
        }
    }
}

@Composable
fun OutlineCard()
{
    var checked by remember {
        mutableStateOf(false)
    }
    Card (
        modifier = Modifier.padding(10.dp).fillMaxSize(),
        shape = CardDefaults.outlinedShape,
        elevation = CardDefaults.outlinedCardElevation(),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column (modifier = Modifier.fillMaxSize().background(Color.White).padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = "Tipo de problema:",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Box (modifier = Modifier.fillMaxWidth(),
                ){
                Row {
                    Text(
                        text = "Sin conexión a internet:",
                        fontSize = 13.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                            .weight(1f)
                    )
                    Checkbox(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        checked = checked, onCheckedChange = {

                        }
                    )
                }
            }

            Box (modifier = Modifier.fillMaxWidth(),
            ){
                Row {
                    Text(
                        text = "Conexión intermitente:",
                        fontSize = 13.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                            .weight(1f)
                    )
                    Checkbox(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        checked = checked, onCheckedChange = {

                        }
                    )
                }
            }
1
            Box (modifier = Modifier.fillMaxWidth(),
            ){
                Row {
                    Text(
                        text = "Velocidad de internet baja:",
                        fontSize = 13.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                            .weight(1f)
                    )
                    Checkbox(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        checked = checked, onCheckedChange = {

                        }
                    )
                }
            }
            Box (modifier = Modifier.fillMaxWidth(),
            ){
                Row {
                    Text(
                        text = "Otra(especifique):",
                        fontSize = 13.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                            .weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            MyOutlinedTextFieldEmail("Ej. El módem no enciende, pero tengo electricidad en mi casa")

            Box (modifier = Modifier.fillMaxWidth(),
            ){
                Row {
                    Text(
                        text = "¿Hace cuánto que comenzó el problema?",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterVertically)
                            .weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))
            MyOutlinedTextFieldEmail("Ej. Hace dos semanas aproximadamente")

            Spacer(modifier = Modifier.height(5.dp))
            Box(modifier = Modifier.align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center)
            {
                ButtonSend()
            }

        }
    }
}

@Composable
fun MyOutlinedTextFieldEmail(texto: String) {
    var textState = remember { mutableStateOf("") }
    OutlinedTextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        placeholder = {Text(text = texto, fontSize = 13.sp)},
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFC2EBF7),
            unfocusedContainerColor = Color(0xFFEBFBFF),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedTextColor = Color.Gray
        ),
        modifier = Modifier.fillMaxWidth()
        )
}
@Composable
fun ButtonSend()
{
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF33ACC1),
            disabledContainerColor = Color(0xFFA6C0C4),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        onClick = {
        }) {
        Text("Enviar", fontSize = 13.sp)
    }
}
