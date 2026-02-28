package com.abdulkarim.navigation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AppNavigator : Navigator {

    private val _events = MutableSharedFlow<NavigationEvent>()
    val events = _events.asSharedFlow()

    override fun navigate(destination: AppDestination) {
        CoroutineScope(Dispatchers.Main).launch {
            _events.emit(NavigationEvent.Navigate(destination.route))
        }
    }

    override fun popBack() {
        CoroutineScope(Dispatchers.Main).launch {
            _events.emit(NavigationEvent.PopBack)
        }
    }

    override fun popUpTo(destination: AppDestination, inclusive: Boolean) {
        CoroutineScope(Dispatchers.Main).launch {
            _events.emit(
                NavigationEvent.Navigate(destination.route)
            )
        }
    }
}

sealed class NavigationEvent {
    data class Navigate(val route: String) : NavigationEvent()
    data object PopBack : NavigationEvent()
}