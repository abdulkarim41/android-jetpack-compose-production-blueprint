package com.abdulkarim.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.automirrored.rounded.HelpOutline
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.GroupAdd
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCartCheckout
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.HelpOutline
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.abdulkarim.desingsystem.R


@Composable
fun ProfileScreen(
    onLogout: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onEditProfileClick: () -> Unit = {},
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()

    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .background(Color(0xFFF8F9FA)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val profile by viewModel.profile.collectAsState()

            Box(contentAlignment = Alignment.BottomEnd) {
                AsyncImage(
                    model = profile?.image,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape),
                    contentScale = ContentScale.Crop,
                )
                IconButton(
                    onClick = onEditProfileClick,
                    modifier = Modifier
                        .size(32.dp)
                        .background(MaterialTheme.colorScheme.primary, CircleShape)
                        .padding(4.dp)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${profile?.firstName} ${profile?.lastName}",
                style = MaterialTheme.typography.headlineMedium
            )

            Surface(
                color = Color(0xFFE3F2FD),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    text = profile?.email ?: "",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                    color = Color(0xFF1976D2),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Menu Section ---

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column {
                    ProfileMenuItem(icon = Icons.Outlined.Edit, label = "Edit Profile")
                    ProfileMenuItem(icon = Icons.Outlined.Payment, label = "Payment")
                    ProfileMenuItem(icon = Icons.Outlined.Feedback, label = "Feedback")
                    ProfileMenuItem(icon = Icons.Outlined.ShoppingCartCheckout, label = "Orders")
                }
            }
            Spacer(modifier = Modifier.height(18.dp))

            // --- Menu Section for others---

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column {
                    ProfileMenuItem(icon = Icons.Outlined.QuestionMark, label = "Help")
                    ProfileMenuItem(icon = Icons.Outlined.Notifications, label = "Notification")
                    ProfileMenuItem(
                        icon = Icons.AutoMirrored.Outlined.Logout,
                        label = "Logout",
                        labelColor = Color.Red,
                        onClick = onLogoutClick
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    label: String,
    labelColor: Color = Color.Black,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = if (labelColor == Color.Red) Color.Red else Color(0xFF4A63E7),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = labelColor,
            modifier = Modifier.weight(1f)
        )
        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray)
    }
}