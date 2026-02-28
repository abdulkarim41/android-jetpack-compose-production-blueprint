package com.abdulkarim.navigation

sealed interface AppDestination {
    val route: String

    // Root Graph
    data object SplashGraph : AppDestination {
        override val route: String
            get() = "splash_graph"
    }
    data object LoginGraph : AppDestination {
        override val route: String
            get() = "login_graph"
    }

    data object OnboardingGraph : AppDestination {
        override val route: String
            get() = "onboarding_graph"
    }

    data object AuthGraph : AppDestination {
        override val route: String
            get() = "auth_graph"
    }

    data object MainGraph : AppDestination {
        override val route: String
            get() = "main_graph"
    }

    data object Splash : AppDestination {
        override val route: String
            get() = "splash"
    }

    data object Onboarding : AppDestination {
        override val route: String
            get() = "onboarding"
    }

    data object Login : AppDestination {
        override val route: String
            get() = "login"
    }

    // Bottom Navigation Graph
    data object Home : AppDestination {
        override val route: String
            get() = "home"
    }

    data object Profile : AppDestination {
        override val route: String
            get() = "profile"
    }

}