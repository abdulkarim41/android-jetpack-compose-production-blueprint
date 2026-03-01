package com.abdulkarim.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.abdulkarim.desingsystem.component.button.AppButton
import com.abdulkarim.desingsystem.component.textfield.AppTextField
import com.abdulkarim.desingsystem.component.textfield.TextFieldType
import com.abdulkarim.domain.apiusecase.auth.PostLoginApiUseCase
import com.abdulkarim.ui.NetworkErrorView
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    val isLoading = uiState is LoginUiState.Loading
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is LoginUiEvent.NavigateToMain -> {
                    onLoginSuccess()
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {

        when (val state = uiState) {
            is LoginUiState.Loading -> {}
            is LoginUiState.Idle -> {}
            is LoginUiState.ProfileApiError -> {
                NetworkErrorView(
                    message = state.message,
                    onRetry = {
                        viewModel.onAction(LoginUiAction.FetchProfileApiAction)
                    }
                )
            }
            is LoginUiState.LoginApiError -> {
                NetworkErrorView(
                    message = state.message,
                    onRetry = {
                        viewModel.onAction(LoginUiAction.FetchProfileApiAction)
                    }
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Welcome back!",
                style = MaterialTheme.typography.displaySmall
            )

            AppTextField.Outlined(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                label = "Email",
            )

            AppTextField.Outlined(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                type = TextFieldType.PASSWORD,
                label = "Password"
            )

            AppButton.Filled(
                text = "Login",
                onClick = {
                    scope.launch {
                        viewModel.onAction(LoginUiAction.PostLoginApiAction(
                            PostLoginApiUseCase.Params(
                                username = "emilys",
                                password = "emilyspass"
                            )
                        ))
                    }

                },
                modifier = Modifier.fillMaxWidth(),
                isLoading = isLoading
            )

        }
    }
}