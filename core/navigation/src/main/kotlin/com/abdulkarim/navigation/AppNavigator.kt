package com.abdulkarim.navigation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AppNavigator : Navigator {

    private val _events = MutableSharedFlow<NavigationEvent>()
    val events = _events.asSharedFlow()

    override fun navigate(
        destination: AppDestination,
        popUpTo: String?,
        inclusive: Boolean
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            _events.emit(
                NavigationEvent.Navigate(
                    route = destination.route,
                    popUpTo = popUpTo,
                    inclusive = inclusive
                )
            )
        }
    }

    override fun popBack() {
        CoroutineScope(Dispatchers.Main).launch {
            _events.emit(NavigationEvent.PopBack)
        }
    }
}

sealed class NavigationEvent {
    data class Navigate(
        val route: String,
        val popUpTo: String? = null,
        val inclusive: Boolean = false
    ) : NavigationEvent()
    data object PopBack : NavigationEvent()
}