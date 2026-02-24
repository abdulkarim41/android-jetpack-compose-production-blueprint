package com.abdulkarim.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulkarim.common.base.Result
import com.abdulkarim.domain.apiusecase.auth.PostLoginApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postLoginApiUseCase: PostLoginApiUseCase,
) : ViewModel() {

    val action: (LoginUiAction) -> Unit = { action ->
        when (action) {
            is LoginUiAction.PostLoginApiAction -> postLoginApi(action.params)
        }
    }

    private val _uiEvent = Channel<LoginUiEvent<Any>>()
    val uiEvent get() = _uiEvent.receiveAsFlow()

    private fun postLoginApi(params: PostLoginApiUseCase.Params) {
        Log.d("LoginViewModel", "postLoginApi: $params")
        viewModelScope.launch {
            postLoginApiUseCase.execute(params).collect { result ->
                when (result) {
                    is Result.Success -> _uiEvent.send(LoginUiEvent.ApiSuccess)
                    is Result.Loading -> _uiEvent.send(LoginUiEvent.Loading(isLoading = result.isLoading))
                    is Result.Error -> _uiEvent.send(LoginUiEvent.LoginApiError(message = result.message))
                }
            }
        }
    }
}

sealed interface LoginUiEvent<out ResultType> {
    data class Loading(val isLoading: Boolean) : LoginUiEvent<Loading>
    data object ApiSuccess : LoginUiEvent<ApiSuccess>
    data class LoginApiError(val message: String) : LoginUiEvent<LoginApiError>
}

sealed interface LoginUiAction {
    data class PostLoginApiAction(val params: PostLoginApiUseCase.Params) : LoginUiAction
}
