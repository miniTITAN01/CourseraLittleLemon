package com.example.littlelemon.composables

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.core.content.edit
import com.example.littlelemon.Onboarding
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun Profile(navController: NavHostController? = null) {
    val context = LocalContext.current

    // Retrieve user data from SharedPreferences
    val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString("firstName", "N/A") ?: "N/A"
    val lastName = sharedPreferences.getString("lastName", "N/A") ?: "N/A"
    val email = sharedPreferences.getString("email", "N/A") ?: "N/A"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        // Top bar with back button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF556B5A))
                .padding(8.dp)
        ) {
            IconButton(onClick = { navController?.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }

        // Header with logo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                contentScale = ContentScale.Fit
            )
        }

        // Profile content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Profile information title
            Text(
                text = "Personal information",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // First name display
            Text(
                text = "First name",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextField(
                value = firstName,
                onValueChange = {},
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedIndicatorColor = Color(0xFFCCCCCC),
                    focusedIndicatorColor = Color(0xFF556B5A),
                    disabledContainerColor = Color(0xFFF5F5F5),
                    disabledIndicatorColor = Color(0xFFCCCCCC),
                    disabledTextColor = Color.Black
                )
            )

            // Last name display
            Text(
                text = "Last name",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextField(
                value = lastName,
                onValueChange = {},
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedIndicatorColor = Color(0xFFCCCCCC),
                    focusedIndicatorColor = Color(0xFF556B5A),
                    disabledContainerColor = Color(0xFFF5F5F5),
                    disabledIndicatorColor = Color(0xFFCCCCCC),
                    disabledTextColor = Color.Black
                )
            )

            // Email display
            Text(
                text = "Email",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextField(
                value = email,
                onValueChange = {},
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedIndicatorColor = Color(0xFFCCCCCC),
                    focusedIndicatorColor = Color(0xFF556B5A),
                    disabledContainerColor = Color(0xFFF5F5F5),
                    disabledIndicatorColor = Color(0xFFCCCCCC),
                    disabledTextColor = Color.Black
                )
            )

            // Log out button
            Button(
                onClick = {
                    // Clear user data from SharedPreferences
                    val sharedPrefs = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
                    sharedPrefs.edit {
                        clear()
                    }

                    // Navigate to Onboarding
                    navController?.navigate(Onboarding.route) {
                        popUpTo(0) // Clear the navigation stack
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF4D54B)
                )
            ) {
                Text(
                    text = "Log out",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    LittleLemonTheme {
        Profile()
    }
}

