package com.frn.meditradenttokenauth.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.frn.meditradenttokenauth.ui.theme.*

@Composable
fun StrokeButton(
    text: String,
    modifier: Modifier = Modifier,
    textColor:Color = MaterialTheme.colors.titleColor,
    borderStrokeColor:Color = MaterialTheme.colors.dividerButton,
    borderStrokeWidth: Dp = 1.dp,
    onClick: () -> Unit,
) {


    Button(
        modifier = modifier.border(
            shape = RoundedCornerShape(150.dp),
            width = borderStrokeWidth,
            color = borderStrokeColor
        ),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        shape = RoundedCornerShape(150.dp),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.Transparent)
                .then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                color = textColor,
                fontSize = MaterialTheme.typography.body1.fontSize
            )
        }
    }
}
