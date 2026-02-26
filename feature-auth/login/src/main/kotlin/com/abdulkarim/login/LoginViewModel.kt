package com.abdulkarim.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulkarim.common.base.Result
import com.abdulkarim.domain.apiusecase.auth.FetchProfileApiUseCase
import com.abdulkarim.domain.apiusecase.auth.PostLoginApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postLoginApiUseCase: PostLoginApiUseCase,
    private val fetchProfileApiUseCase: FetchProfileApiUseCase,
) : ViewModel() {

    val onAction: (LoginUiAction) -> Unit = { action ->
        when (action) {
            is LoginUiAction.PostLoginApiAction -> postLoginApi(action.params)
            is LoginUiAction.FetchProfileApiAction -> fetchProfileApi()
        }
    }

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState get() = _uiState

    private val _uiEvent = MutableSharedFlow<LoginUiEvent>()
    val uiEvent get() = _uiEvent.asSharedFlow()

    private fun postLoginApi(params: PostLoginApiUseCase.Params) {
        viewModelScope.launch {
            postLoginApiUseCase.execute(params).collect { result ->
                when (result) {
                    is Result.Success -> fetchProfileApi()
                    is Result.Loading -> _uiState.value = LoginUiState.Loading
                    is Result.Error -> _uiState.value = LoginUiState.LoginApiError(message = result.message)
                }
            }
        }
    }

    private fun fetchProfileApi() {
        viewModelScope.launch {
            fetchProfileApiUseCase.execute().collect { result ->
                when (result) {
                    is Result.Success -> _uiEvent.emit(LoginUiEvent.NavigateToMain)
                    is Result.Loading -> _uiState.value = LoginUiState.Loading
                    is Result.Error -> _uiState.value = LoginUiState.ProfileApiError(message = result.message)
                }
            }
        }
    }
}

sealed interface LoginUiState {
    data object Idle : LoginUiState
    data object Loading : LoginUiState
    data class LoginApiError(val message: String) : LoginUiState
    data class ProfileApiError(val message: String) : LoginUiState
}

sealed interface LoginUiEvent {
    data object NavigateToMain : LoginUiEvent
}

sealed interface LoginUiAction {
    data class PostLoginApiAction(
        val params: PostLoginApiUseCase.Params
    ) : LoginUiAction
    data object FetchProfileApiAction : LoginUiAction
}
