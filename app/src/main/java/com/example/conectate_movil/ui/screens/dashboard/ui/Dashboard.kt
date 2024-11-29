package com.example.conectate_movil.ui.screens.dashboard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.dp
import com.example.conectate_movil.R
import com.example.conectate_movil.ui.screens.dashboard.ui.components.Advertencia
import com.example.conectate_movil.ui.screens.dashboard.ui.components.Copyrigt
import com.example.conectate_movil.ui.screens.dashboard.ui.components.Encabezado
import com.example.conectate_movil.dashboard.ui.components.Formas
import com.example.conectate_movil.ui.screens.dashboard.ui.components.Opciones
import com.example.conectate_movil.ui.screens.dashboard.ui.components.PlanCard
import com.example.conectate_movil.dashboard.view_model.DashboardViewModel
import com.example.conectate_movil.data.models.Plan


val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val poppinsFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Poppins"),
        fontProvider = provider,
        weight = FontWeight.Normal,
        style = FontStyle.Normal,
    ),
)

// Este será el contenedor principal
// para nuestra pantalla del Dashboard.
//@Preview
@Composable
fun DashboardScreen(
    isTablet: Boolean,
    viewmodel: DashboardViewModel
) {
    val username: String by viewmodel.username.observeAsState(initial = "Felipe")

    // Lista de planes
    val planList: List<Plan> by viewmodel.planList.observeAsState(initial =
        listOf(
            Plan(
                id = 1,
                alias = "Contrato A",
                nombre_paquete = "Plan Mega-200",
                precio = 200.00f,
                velocidad_paquete = "20 Mbps",
                fecha_pago = "Octubre 2024",
                fecha_inicio_mensualidad = "05 de Octubre 2024",
                fecha_fin_mensualidad = "05 de Noviembre 2024",
                estado_pago = "Activo"
            ),
            Plan(
                id =2,
                alias = "Contrato B",
                nombre_paquete = "Plan Mega-500",
                precio = 500.00f,
                velocidad_paquete = "50 Mbps",
                fecha_pago = "Octubre 2024",
                fecha_inicio_mensualidad = "05 de Octubre 2024",
                fecha_fin_mensualidad = "05 de Noviembre 2024",
                estado_pago = "Activo"
            ),
            Plan(
                id = 3,
                alias = "Contrato C",
                nombre_paquete = "Plan Mega-1000",
                precio = 1000.00f,
                velocidad_paquete = "100 Mbps",
                fecha_pago = "Octubre 2024",
                fecha_inicio_mensualidad = "05 de Octubre 2024",
                fecha_fin_mensualidad = "05 de Noviembre 2024",
                estado_pago = "Inactivo"
            )
        )
    )

    // Recordar el estado del pager para manejar la posición actual
    val pagerState = rememberPagerState(pageCount = { planList.size })

    // ScrollState para detectar el desplazamiento de la LazyColumn
    val scrollState = rememberScrollState()

    // Calcular la opacidad de los elementos de fondo en función del desplazamiento
    val backgroundAlpha = (1f - (scrollState.value / 200f)).coerceIn(0f, 1f)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {

        // Formas para dibujar los círculos de fondo
        Formas(
            isTablet = isTablet,
            backgroundAlpha = backgroundAlpha,
        )


        Image(
            painter = painterResource(id = R.drawable.conectate_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(if (isTablet) 700.dp else 450.dp)
                .alpha(0.1f)
                .padding(bottom = 50.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            Encabezado(
                username = username,
                isTablet = isTablet,
                poppinsFontFamily = poppinsFontFamily
            )

            Spacer(modifier = Modifier.height(if (isTablet) 26.dp else 16.dp))


            PlanCard(
                isTablet = isTablet,
                planList = planList,
                poppinsFontFamily = poppinsFontFamily,
                pagerState = pagerState
            )



            if (planList[pagerState.currentPage].estado_pago == "Activo") {
                Spacer(modifier = Modifier.height(if (isTablet) 50.dp else 32.dp))
            } else {
                Advertencia(isTablet = isTablet, poppinsFontFamily = poppinsFontFamily)
            }


            Spacer(modifier = Modifier.height(if (isTablet) 40.dp else 16.dp))

            Opciones(isTablet = isTablet,
                poppinsFontFamily = poppinsFontFamily
            )

            Spacer(modifier = Modifier.height(if (isTablet) 40.dp else 16.dp))

            Copyrigt(isTablet = isTablet,
                poppinsFontFamily = poppinsFontFamily
            )

            Spacer(modifier = Modifier.height(if (isTablet) 80.dp else 60.dp))
        }
    }
}