package com.abdulkarim.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulkarim.datastore.DatastorePreferences
import com.abdulkarim.entity.auth.ProfileApiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    datastore: DatastorePreferences
) : ViewModel() {

    val profile: StateFlow<ProfileApiEntity?> =
        datastore.getCachedProfile()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = null
            )

}