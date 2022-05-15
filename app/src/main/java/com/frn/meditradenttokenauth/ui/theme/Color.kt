package com.frn.meditradenttokenauth.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)


val Blue50 = Color(0xFFD3EAFC)
val Blue100 = Color(0xFF8EB7E0)
val Blue200 = Color(0xFF5496D6)
val Blue500 = Color(0xFF2F5EB5)

val Red = Color(0xFFE62424)

val BackgroundBlack = Color(0xFF000000)

val Gray300 = Color(0xFF595959)
val Gray100 = Color(0xFF999999)
val Gray50 = Color(0xFFDADADA)


val Colors.titleColor
    @Composable
    get() = if (isLight) Gray300 else Color.White.copy(alpha = 0.9f)

val Colors.descriptionColor
    @Composable
    get() = if (isLight) Gray300.copy(alpha = 0.7f) else Color.White.copy(alpha = 0.7f)


val Colors.dividerButton
    @Composable
    get() = if (isLight) Gray100 else Color.White.copy(alpha = 0.3f)


val Colors.dividerChipSection
    @Composable
    get() = if (isLight) Blue50 else MaterialTheme.colors.onSurface.copy(alpha = 0.05f)