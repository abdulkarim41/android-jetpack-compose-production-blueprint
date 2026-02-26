package com.abdulkarim.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulkarim.datastore.DatastorePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val datastorePreferences: DatastorePreferences
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onNavigateToNext() {
        viewModelScope.launch {
            datastorePreferences.setOnboardingLaunched(true)
            _uiEvent.emit(UiEvent.NavigateNext)
        }
    }

    sealed interface UiEvent {
        data object NavigateNext : UiEvent
    }
}