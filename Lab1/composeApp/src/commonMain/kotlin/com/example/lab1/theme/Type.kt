package com.example.lab1.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import lab1.composeapp.generated.resources.Res
import lab1.composeapp.generated.resources.montserrat_italic
import lab1.composeapp.generated.resources.montserrat_regular
import org.jetbrains.compose.resources.Font

@Composable
fun appTypography(): Typography {
    val montserratFamily = FontFamily(
        Font(
            resource = Res.font.montserrat_regular,
            weight = FontWeight.Normal
        ),
        Font(
            resource = Res.font.montserrat_regular,
            weight = FontWeight.Bold
        ),
        Font(
            resource = Res.font.montserrat_italic,
            weight = FontWeight.Normal,
            style = FontStyle.Italic
        )
    )

    return Typography(
        titleLarge = TextStyle(
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        ),
        titleMedium = TextStyle(
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        labelLarge = TextStyle(
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
    )
}