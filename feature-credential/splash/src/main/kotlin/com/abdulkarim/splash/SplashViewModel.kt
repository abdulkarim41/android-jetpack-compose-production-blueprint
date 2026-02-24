package com.abdulkarim.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    val action: (SplashUiAction) -> Unit = {
        when(it) {
            is SplashUiAction.FetchProfileApiAction -> fetchProfileApi()
        }
    }

    private val _uiState = MutableStateFlow<SplashUiState<Any>>(SplashUiState.Loading(isLoading = true))
    val uiState get() = _uiState

    init { checkLoginStatus() }

    private fun checkLoginStatus() {
        val isLoggedIn = false
        val isOnboardingLaunch = true
        if (!isLoggedIn) {
            fetchProfileApi()
            return
        }
        if (!isOnboardingLaunch) {
            uiState.value = SplashUiState.NavigateToOnboarding
            return
        }
        uiState.value = SplashUiState.ProfileApiSuccess
    }

    private fun fetchProfileApi() {
        viewModelScope.launch {
            uiState.value = SplashUiState.Loading(isLoading = true)
            delay(2000)
            uiState.value = SplashUiState.Loading(isLoading = false)
            //uiState.value = SplashUiState.ProfileApiSuccess
            //uiState.value = SplashUiState.ProfileApiError("error", 404)
            //uiState.value = SplashUiState.NavigateToOnboarding
            uiState.value = SplashUiState.NavigateToLogin

        }
    }

}

sealed interface SplashUiState<out ResultType> {
    data class Loading(val isLoading: Boolean) : SplashUiState<Loading>
    data class ProfileApiError(val message: String, val code: Int) : SplashUiState<ProfileApiError>
    data object ProfileApiSuccess : SplashUiState<ProfileApiSuccess>
    data object NavigateToLogin : SplashUiState<NavigateToLogin>
    data object NavigateToOnboarding : SplashUiState<NavigateToOnboarding>
}

sealed interface SplashUiAction {
    data object FetchProfileApiAction : SplashUiAction
}
