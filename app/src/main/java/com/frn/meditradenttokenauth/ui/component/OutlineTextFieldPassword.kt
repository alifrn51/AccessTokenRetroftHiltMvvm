package com.frn.meditradenttokenauth.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OutlineTextFieldPassword(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    isPasswordField: Boolean = false,
    onPasswordChange: (Boolean) -> Unit = {},
    isIconClearFiled: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isValid:(Boolean) -> Unit = {}
) {

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    onPasswordChange(isPasswordVisible)

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


                errorMessage = when {
                    it == "" -> ""
                    it.length < 8 -> "Use 8 characters or more for your password"
                    it.none { char ->
                        char.isUpperCase()
                    } -> "Please choose a stronger password. Try a mix of letters, numbers, and capital letter."
                    else -> {
                        ""
                    }
                }
            },
            modifier = modifier
                .fillMaxWidth(),
            label = { Text(text = label) },
            isError = errorMessage.isNotEmpty(),
            trailingIcon = {
                if (isIconClearFiled) {

                    AnimatedVisibility(visible = value.isNotEmpty()) {
                        IconButton(onClick = { onValueChange("") }) {

                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear icon"
                            )

                        }
                    }


                }
                if (isPasswordField) {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {

                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = "Toggle icon"
                        )

                    }
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
                    //.offset(y = (-8).dp)
            )
        }


    }


}