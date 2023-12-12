package com.example.mozart.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mozart.R

val fonts = FontFamily(
    Font(R.font.monsmedium, FontWeight.Medium),
    Font(R.font.monstsemibold, FontWeight.SemiBold),
    Font(R.font.poppinbold, FontWeight.Bold),
    Font(R.font.poppinreguler, FontWeight.Normal),
)

val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.5.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.5.sp
    ),
)

