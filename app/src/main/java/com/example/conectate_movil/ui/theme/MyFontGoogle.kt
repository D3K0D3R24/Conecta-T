package com.example.conectate_movil.ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.conectate_movil.R

// no se modifica
private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)


//aqui se detalla la tipografia de la app que se necesite
private val fontRoboto = GoogleFont("Roboto")
private val fontOswals = GoogleFont("Oswald")
private val fontPoppins = GoogleFont("Poppins")


//por defecto esta sera la familia a usar en la app cuando se aplique
/*
* Ejemplo de como usar la tipografia de google
*
*               por defecto la fontWeight es normal
*
* Text(text = "Hello", fontFamily = robotoFontFamily fontSize = 24.sp)
*
* Text(text = "Hello", fontFamily = oswaldFontFamily, fontSize = 24.sp)
*
*
*                    aqui se puede cambiar la fontWeight
*
* Text(text = "Hello", fontFamily = robotoFontFamily, fontWeight = FontWeight.Bold, fontSize = 24.sp)
*
* Text(text = "Hello", fontFamily = oswaldFontFamily, fontWeight = FontWeight.Bold, fontSize = 24.sp)

*
* */

val robotoFontFamily = FontFamily(
    Font(
        googleFont = fontRoboto,
        fontProvider = provider,
        weight = FontWeight.Normal
    ),
)


val poppinsFontFamily = FontFamily(
    Font(
        googleFont = fontPoppins,
        fontProvider = provider,
        weight = FontWeight.Bold
    ),
)

val oswaldFontFamily = FontFamily(
    Font(
        googleFont = fontOswals,
        fontProvider = provider,
        weight = FontWeight.Normal
    ),
)

//ir agregando mas familias de tipografias





