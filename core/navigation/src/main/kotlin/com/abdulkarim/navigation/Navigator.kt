package com.abdulkarim.navigation

interface Navigator {
    fun navigate(destination: AppDestination,popUpTo: String? = null,inclusive: Boolean = false)
    fun popBack()
}