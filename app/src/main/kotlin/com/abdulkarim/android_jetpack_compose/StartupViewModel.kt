
package com.abdulkarim.android_jetpack_compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class StartupViewModel @Inject constructor() : ViewModel() {

    val uiState: StateFlow<StartupState> = flow {

        emit(StartupState.Loading)

        delay(2000)
        val isUserLoggedIn = false

        if (isUserLoggedIn) {
            emit(StartupState.Authenticated)
        } else {
            emit(StartupState.Unauthenticated)
        }

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = StartupState.Loading
    )
}

sealed interface StartupState {
    data object Loading : StartupState
    data object Authenticated : StartupState
    data object Unauthenticated : StartupState
}
