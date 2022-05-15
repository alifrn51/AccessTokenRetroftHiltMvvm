package com.frn.meditradenttokenauth.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.frn.meditradenttokenauth.ui.EXTRA_LARGE_PADDING
import com.frn.meditradenttokenauth.ui.MEDIUM_PADDING
import com.frn.meditradenttokenauth.ui.theme.*

@Composable
fun GradientButton(
    text: String,
    modifier: Modifier = Modifier,
    gradient: Brush = Brush.horizontalGradient(listOf(Blue200, Blue500)),
    gradientDisable: Brush = Brush.horizontalGradient(listOf(Gray100, Gray300)),
    enabled: Boolean = true,
    onClick: () -> Unit,
) {


    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        shape = RoundedCornerShape(150.dp),
        contentPadding = PaddingValues(),
        enabled = enabled,
        onClick = { onClick() },
    ) {
        Box(
            modifier = Modifier
                .background(brush = gradient, alpha = if (enabled) 1f else 0.4f)
                .then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = MaterialTheme.typography.body1.fontSize
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GradientButtonPreview() {
    GradientButton(text ="Start",
            modifier = Modifier
            .fillMaxWidth()
        .padding(horizontal = EXTRA_LARGE_PADDING, vertical = MEDIUM_PADDING)
    ) {

    }
}