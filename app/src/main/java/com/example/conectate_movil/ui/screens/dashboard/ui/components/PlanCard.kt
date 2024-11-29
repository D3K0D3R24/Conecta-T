package com.example.conectate_movil.ui.screens.dashboard.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.conectate_movil.data.models.Plan
import kotlin.math.absoluteValue


@Composable
fun PlanCard(
    isTablet: Boolean,
    planList: List<Plan>,
    poppinsFontFamily: FontFamily,
    pagerState: PagerState
) {

    Box (
        modifier = Modifier
            .padding(top = if (isTablet) 50.dp else 25.dp)
    )
    {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .background(
                    Color(0xFF708ADE),
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(horizontal = 30.dp, vertical = 10.dp)
        ) {
            Text(
                text =
                if(planList[pagerState.currentPage].alias.length < 15){
                    planList[pagerState.currentPage].alias
                }else{
                    planList[pagerState.currentPage].alias.substring(0,15) + "..."
                },
                color = Color.White,
                fontSize = if (isTablet) 30.sp else 18.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold
            )
        }
        // HorizontalPager
        HorizontalPager(
            state = pagerState,
        ){ page ->
            InfoCard(
                isTablet = isTablet,
                planList = planList[page],
                poppinsFontFamily = poppinsFontFamily,
                pagerState = pagerState,
                page = page
            )
        }
        Row(
            Modifier
                //.wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                )
            }
        }

    }
}

@Composable
fun InfoCard(
    isTablet: Boolean,
    planList: Plan,
    poppinsFontFamily: FontFamily,
    pagerState: PagerState,
    page: Int,
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = if (isTablet) 40.dp else 35.dp,
                start = if (isTablet) 50.dp else 50.dp,
                end = if (isTablet) 50.dp else 50.dp,
                bottom = if (isTablet) 35.dp else 20.dp
            )
            .graphicsLayer {
                // Calcular el desplazamiento absoluto de la página actual desde la
                // posición de desplazamiento. Usamos el valor absoluto que nos permite reflejar
                // cualquier efecto para ambas direcciones
                val pageOffset = (
                        (pagerState.currentPage - page) + pagerState
                            .currentPageOffsetFraction
                        )
                    .absoluteValue

                //Animamos el alfa, entre el 50% y el 100%
                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        border = BorderStroke(2.dp, Color(0xFF708ADE)),
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(if (isTablet) 30.dp else 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier.padding(start = 15.dp)
                ) {
                    Text(
                        text = planList.nombre_paquete,
                        fontSize = if (isTablet) 26.sp else 16.sp,
                        fontFamily = poppinsFontFamily,
                        color = Color.Black
                    )
                    Text(
                        text = planList.velocidad_paquete ,//+ "Mbps",
                        fontSize = if (isTablet) 22.sp else 12.sp,
                        fontFamily = poppinsFontFamily,
                        color = Color.Black
                    )
                }
                Column(
                    modifier = Modifier.padding(end = 15.dp).width(if (isTablet) 150.dp else 70.dp)
                ) {
                    Text(
                        text = "$"+planList.precio, //"$$planPrice",
                        fontSize = if (isTablet) 26.sp else 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFontFamily,
                        color = Color(0xFF59BAAF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "MXN",
                        fontSize = if (isTablet) 22.sp else 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFontFamily,
                        color = Color(0xFF59BAAF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Spacer(modifier = Modifier.height(if (isTablet) 40.dp else 8.dp))
            Column(
                modifier = Modifier
                    .background(
                        color = Color(0xFFEAFFFF),
                    )
                    .fillMaxWidth()
                    .padding(if (isTablet) 50.dp else 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Periodo de facturación: "+ planList.fecha_inicio_mensualidad, //$billingPeriod",
                    fontSize = if (isTablet) 22.sp else 13.sp,
                    fontFamily = poppinsFontFamily,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
                Text(
                    text = "Fecha de corte: "+ planList.fecha_fin_mensualidad,//$dueDate",
                    fontSize = if (isTablet) 22.sp else 13.sp,
                    fontFamily = poppinsFontFamily,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(if (isTablet) 40.dp else 8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(if (isTablet) 20.dp else 10.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Status del servicio: ",
                    fontSize = if (isTablet) 24.sp else 14.sp,
                    fontFamily = poppinsFontFamily,
                    color = Color.Black
                )
                Text(
                    text = planList.estado_pago,//status,
                    fontSize = if (isTablet) 24.sp else 14.sp,
                    fontFamily = poppinsFontFamily,
                    color = if (planList.estado_pago == "Activo") Color(0xFF59BAAF) else Color.Red
                )
            }
            Spacer(modifier = Modifier.height(if (isTablet) 40.dp else 8.dp))
        }
    }
}