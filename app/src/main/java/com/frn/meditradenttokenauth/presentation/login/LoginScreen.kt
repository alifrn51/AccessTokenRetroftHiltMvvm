package com.frn.meditradenttokenauth.presentation.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.frn.meditradenttokenauth.ui.component.GradientButton
import com.frn.meditradenttokenauth.ui.component.OutlineTextFieldEmail
import com.frn.meditradenttokenauth.ui.component.OutlineTextFieldPassword
import com.frn.meditradenttokenauth.ui.component.StrokeButton
import com.frn.meditradenttokenauth.R
import com.frn.meditradenttokenauth.navigation.Screen
import com.frn.meditradenttokenauth.ui.EXTRA_LARGE_PADDING
import com.frn.meditradenttokenauth.ui.MEDIUM_PADDING
import com.frn.meditradenttokenauth.ui.SMALL_PADDING
import com.frn.meditradenttokenauth.ui.theme.Blue100
import com.frn.meditradenttokenauth.ui.theme.Blue200
import com.frn.meditradenttokenauth.ui.theme.titleColor

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    var username by remember {
        mutableStateOf("")
    }

    var isValidEmail by remember {
        mutableStateOf(false)
    }

    var password by remember {
        mutableStateOf("")
    }

    var isValidPassword by remember {
        mutableStateOf(false)
    }


    val isFormValid by derivedStateOf {
        isValidPassword && isValidEmail
    }

    val scaffoldState = rememberScaffoldState()



    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        snackbarHost = { scaffoldState.snackbarHostState }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MEDIUM_PADDING),
                verticalArrangement = Arrangement.SpaceAround,


                ) {


                Text(
                    text = "Welcome back",
                    fontSize = MaterialTheme.typography.h4.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.titleColor,
                    modifier = Modifier.padding(MEDIUM_PADDING)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_lock),
                    contentDescription = "Lock",
                    tint = Blue100,
                    modifier = Modifier
                        .padding(MEDIUM_PADDING)
                        .size(64.dp)
                        .border(
                            width = 3.dp,
                            shape = RoundedCornerShape(150.dp),
                            color = Blue200
                        )
                        .padding(SMALL_PADDING)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top
                ) {

                    Text(
                        text = "Login",
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colors.titleColor,
                        modifier = Modifier
                            .padding(vertical = MEDIUM_PADDING)
                    )

                    OutlineTextFieldEmail(
                        value = username,
                        onValueChange = {
                            username = it
                        },
                        isIconClearFiled = true,
                        label = "Enter your email",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        isValid = {
                            isValidEmail = it
                        }
                    )

                    OutlineTextFieldPassword(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        isPasswordField = true,
                        label = "Enter your password",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        isValid = {
                            isValidPassword = it
                        }
                    )


                    TextButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .align(alignment = Alignment.End)
                    ) {
                        Text(
                            text = "Forgot password",
                            fontSize = MaterialTheme.typography.subtitle1.fontSize,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier,
                            style = TextStyle(textDecoration = TextDecoration.Underline)
                        )

                    }


                }



                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    GradientButton(
                        text = "Login",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = EXTRA_LARGE_PADDING, vertical = MEDIUM_PADDING)
                    ) {
                        viewModel.login(username, password)

                    }


                    Text(
                        text = "Or",
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        fontWeight = FontWeight.Light,
                    )


                    StrokeButton(
                        text = "Signup",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = if (isSystemInDarkTheme()) Color.Transparent else Color.White)
                            .padding(horizontal = EXTRA_LARGE_PADDING, vertical = MEDIUM_PADDING)

                    ) {

                        viewModel.getUer()


                    }

                }


            }


        }


    }


      Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
      ) {
          when {
              state.isLoading -> {
                  CircularProgressIndicator()
              }

              state.registration -> {
                  navController.popBackStack()
                  navController.navigate(Screen.Main.route)
              }
              state.error != null -> {


                      Column(
                          modifier = Modifier
                              .fillMaxSize()
                              .background(color = MaterialTheme.colors.background)
                              .clickable {  },
                          verticalArrangement = Arrangement.Center,
                          horizontalAlignment = Alignment.CenterHorizontally
                      ) {

                          Text(
                              text = "Can n't connect to server",
                              style = MaterialTheme.typography.subtitle1,
                              color = MaterialTheme.colors.titleColor,
                              modifier = Modifier
                                  .padding(top = 24.dp)
                          )
                      }


              }

              state.user !=null ->{
                  Column(
                      modifier = Modifier
                          .fillMaxSize()
                          .background(color = MaterialTheme.colors.background)
                          .clickable {  },
                      verticalArrangement = Arrangement.Center,
                      horizontalAlignment = Alignment.CenterHorizontally
                  ) {

                      Text(
                          text = state.user!!.first_name,
                          style = MaterialTheme.typography.subtitle1,
                          color = MaterialTheme.colors.titleColor,
                          modifier = Modifier
                              .padding(top = 24.dp)
                      )
                  }

              }

          }
      }

}

