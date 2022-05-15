package com.frn.meditradenttokenauth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frn.meditradenttokenauth.domain.repository.AuthRepository
import com.frn.meditradenttokenauth.domain.repository.UserRepository
import com.frn.meditradenttokenauth.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val repositoryUser:UserRepository
):ViewModel() {


    private var _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state


    fun getUer(){
        viewModelScope.launch {

            when(val result =repositoryUser.getUser() ){
                is Resource.Success ->{
                    _state.value = _state.value.copy(user = result.value[0])
                }

                is Resource.Failure ->{

                    when {
                        result.isNetworkError ->_state.value = _state.value.copy(error = "Please check your internet connection")
                        result.errorCode == 401 -> {
                            _state.value = _state.value.copy(error = "You've entered incorrect email or password")
                        }
                        else -> {
                            val error = result.errorBody?.string().toString()

                            _state.value =_state.value.copy(error = error)

                        }
                    }

                }
            }
        }
    }

    fun login(username: String , password:String){
        viewModelScope.launch {

            when(val result = repository.login(username =  username , password =  password)){

                is Resource.Loading ->{
                    _state.value = _state.value.copy(isLoading = result.isLoading)
                }
                is Resource.Success ->{

                    saveAccessTokens(result.value.access_token , result.value.access_token)
                    _state.value = _state.value.copy(registration = true)


                }
                is Resource.Failure ->{

                    when {
                        result.isNetworkError ->_state.value = _state.value.copy(error = "Please check your internet connection")
                        result.errorCode == 401 -> {
                            _state.value = _state.value.copy(error = "You've entered incorrect email or password")
                        }
                        else -> {
                            val error = result.errorBody?.string().toString()

                            _state.value =_state.value.copy(error = error)

                        }
                    }

                }
            }

        }
    }



    private suspend fun saveAccessTokens(accessToken: String, refreshToken: String) {
        repository.saveAccessTokens(accessToken, refreshToken)
    }

}
