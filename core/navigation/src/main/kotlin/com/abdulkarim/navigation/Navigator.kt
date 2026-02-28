package com.abdulkarim.navigation

interface Navigator {
    fun navigate(destination: AppDestination)
    fun popBack()
    fun popUpTo(destination: AppDestination, inclusive: Boolean = false)
}