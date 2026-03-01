package com.abdulkarim.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulkarim.datastore.DatastorePreferences
import com.abdulkarim.entity.auth.ProfileApiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val datastore: DatastorePreferences
) : ViewModel() {

    private val _profile = MutableStateFlow<ProfileApiEntity?>(null)
    val profile: StateFlow<ProfileApiEntity?> = _profile.asStateFlow()

    private val _showLogoutSheet = MutableStateFlow(false)
    val showLogoutSheet: StateFlow<Boolean> = _showLogoutSheet.asStateFlow()

    private val _event = MutableSharedFlow<ProfileUiEvent>()
    val event: SharedFlow<ProfileUiEvent> = _event.asSharedFlow()

    init { observeProfile() }

    private fun observeProfile() {
        datastore.getCachedProfile()
            .distinctUntilChanged()
            .onEach { profile ->
                _profile.value = profile
            }
            .launchIn(viewModelScope)
    }

    fun onLogoutClick() {
        _showLogoutSheet.value = true
    }

    fun onDismissLogout() {
        _showLogoutSheet.value = false
    }

    fun confirmLogout() {
        viewModelScope.launch {

            // datastore.clearAll()

            _showLogoutSheet.value = false

            _event.emit(ProfileUiEvent.NavigateToLogin)
        }
    }
}

sealed interface ProfileUiEvent {
    data object NavigateToLogin : ProfileUiEvent
}