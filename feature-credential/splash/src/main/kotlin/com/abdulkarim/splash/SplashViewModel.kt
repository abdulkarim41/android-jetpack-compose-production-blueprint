package com.abdulkarim.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulkarim.datastore.DatastorePreferences
import com.abdulkarim.domain.apiusecase.auth.FetchProfileApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.abdulkarim.common.base.Result
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val datastorePreferences: DatastorePreferences,
    private val fetchProfileApiUseCase: FetchProfileApiUseCase
) : ViewModel() {

    val action: (SplashUiAction) -> Unit = {
        when(it) {
            is SplashUiAction.FetchProfileApiAction -> fetchProfileApi()
        }
    }

    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiState get() = _uiState

    private val _uiEvent = MutableSharedFlow<SplashUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            when {
                !datastorePreferences.isOnboardingLaunched.first() -> {
                    _uiEvent.emit(SplashUiEvent.NavigateToOnboarding)
                }
                !datastorePreferences.isUserLoggedIn.first() -> {
                    _uiEvent.emit(SplashUiEvent.NavigateToLogin)
                }
                else -> fetchProfileApi()
            }
        }

    }

    private fun fetchProfileApi() {
        viewModelScope.launch {
            fetchProfileApiUseCase.execute().collect { result ->
                when(result){
                    is Result.Loading -> {
                        uiState.value = SplashUiState.Loading
                    }
                    is Result.Error -> {
                        uiState.value = SplashUiState.ProfileApiError(result.message, result.code)
                    }
                    is Result.Success -> {
                        _uiEvent.emit(SplashUiEvent.NavigateToMain)
                    }
                }
            }
        }
    }

}

sealed interface SplashUiState {
    data object Loading : SplashUiState
    data class ProfileApiError(val message: String, val code: Int) : SplashUiState
}

sealed interface SplashUiEvent {
    data object NavigateToMain : SplashUiEvent
    data object NavigateToLogin : SplashUiEvent
    data object NavigateToOnboarding : SplashUiEvent
}

sealed interface SplashUiAction {
    data object FetchProfileApiAction : SplashUiAction
}
