package com.frn.meditradenttokenauth.ui.component

import android.util.Patterns
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OutlineTextFieldEmail(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    isIconClearFiled: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isValid: (Boolean) -> Unit = {}
) {

    var errorMessage by remember {
        mutableStateOf("")
    }

    isValid(errorMessage.isEmpty() && value.isNotEmpty())



    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)

                errorMessage =
                    if (it == "") ""
                    else if (!Patterns.EMAIL_ADDRESS.matcher(it).matches())
                        "Invalid email address"
                    else {
                        ""
                    }


            },
            modifier = modifier
                .fillMaxWidth(),
            label = { Text(text = label) },
            isError = if (errorMessage == "") false else errorMessage.isNotEmpty(),
            trailingIcon = {
                if (isIconClearFiled) {

                    AnimatedVisibility(visible = value.isNotEmpty()) {
                        IconButton(onClick = {
                            onValueChange("")
                            errorMessage = ""
                        }) {

                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear icon"
                            )

                        }
                    }


                }

            },
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            singleLine = true
        )

        AnimatedVisibility(visible = errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                //.offset(y = (-0).dp)
            )
        }


    }


}