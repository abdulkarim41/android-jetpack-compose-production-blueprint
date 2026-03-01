package com.abdulkarim.profile


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.abdulkarim.desingsystem.component.AppButton
import com.abdulkarim.desingsystem.component.AppOutlinedButton
import com.abdulkarim.desingsystem.icon.AppIcons
import com.abdulkarim.entity.auth.ProfileApiEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateToLogin: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val profile by viewModel.profile.collectAsStateWithLifecycle()
    val showLogoutSheet by viewModel.showLogoutSheet.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                ProfileUiEvent.NavigateToLogin -> onNavigateToLogin()
            }
        }
    }

    ProfileContent(
        profile = profile,
        showLogoutSheet = showLogoutSheet,
        onLogoutClick = viewModel::onLogoutClick,
        onDismissLogout = viewModel::onDismissLogout,
        onConfirmLogout = viewModel::confirmLogout
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    profile: ProfileApiEntity?,
    showLogoutSheet: Boolean,
    onLogoutClick: () -> Unit,
    onDismissLogout: () -> Unit,
    onConfirmLogout: () -> Unit
) {

    Scaffold { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                profile?.let {
                    ProfileHeader(it)
                }
            }

            // Menu Items
            item {
                ProfileMenuItem(
                    label = "Edit Profile",
                    leadingIcon = AppIcons.Edit,
                    onItemClick = { /* navigate */ }
                )
            }

            item {
                ProfileMenuItem(
                    label = "Orders",
                    leadingIcon = AppIcons.ShoppingCartCheckout,
                    onItemClick = { /* navigate */ }
                )
            }

            item {
                ProfileMenuItem(
                    label = "Feedback",
                    leadingIcon = AppIcons.Feedback,
                    onItemClick = { /* navigate */ }
                )
            }

            item {
                ProfileMenuItem(
                    label = "Help",
                    leadingIcon = AppIcons.QuestionMark,
                    onItemClick = { /* navigate */ }
                )
            }

            item {
                ProfileMenuItem(
                    label = "Logout",
                    leadingIcon = AppIcons.Logout,
                    onItemClick = onLogoutClick
                )
            }

        }
    }

    if (showLogoutSheet) {
        LogoutBottomSheet(
            onConfirm = onConfirmLogout,
            onDismiss = onDismissLogout
        )
    }
}

private data class MenuItem(
    val label: String,
    val leadingIcon: ImageVector,
    val isLogout: Boolean = false
)

private val profileMenuItems = listOf(
    MenuItem(label = "Edit Profile", leadingIcon = AppIcons.EditDefault),
    MenuItem(label = "Payment",leadingIcon = AppIcons.Payment),
    MenuItem(label = "Orders", leadingIcon = AppIcons.ShoppingCartCheckout),
    MenuItem("Help", leadingIcon = AppIcons.QuestionMark),
    MenuItem("Logout", isLogout = true, leadingIcon = AppIcons.Logout)
)

@Composable
private fun ProfileHeader(profile: ProfileApiEntity) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        AsyncImage(
            model = profile.image,
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${profile.firstName} ${profile.lastName}",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = profile.email,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun ProfileMenuItem(
    label: String,
    leadingIcon: ImageVector,
    onItemClick: () -> Unit
) {
    Card(
        onClick = onItemClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(leadingIcon, contentDescription = null)
            Text(
                text = label,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(AppIcons.ArrowRight, contentDescription = null)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogoutBottomSheet(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Are you sure you want to logout?")

            Spacer(Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                AppOutlinedButton(
                    modifier = Modifier.weight(1f),
                    text = "Cancel",
                    onClick = onDismiss
                )

                AppButton(
                    modifier = Modifier.weight(1f),
                    text = "Logout",
                    onClick = onConfirm
                )
            }
        }
    }
}