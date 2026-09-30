package com.example.littlelemon.composables

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.example.littlelemon.Home
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonTheme
import androidx.core.content.edit

@Composable
fun Onboarding(navController: NavHostController? = null) {
    val context = LocalContext.current
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val registrationMessage = remember { mutableStateOf("") }

    Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .background(Color.White)
    ) {
        // Header with logo
        Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
        ) {
            Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Little Lemon Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentScale = ContentScale.Fit
            )
        }

        // "Let's get to know you" section
        Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF556B5A))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
        ) {
            Text(
                    text = "Let's get to know you",
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 64.dp),
            )
        }

        // Personal information section
        Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
        ) {
            Text(
                    text = "Personal information",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
            )

            // First name field
            Text(
                    text = "First name",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
            )
            TextField(
                    value = firstName.value,
                    onValueChange = { firstName.value = it },
                    placeholder = { Text("Tilly") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedIndicatorColor = Color(0xFFCCCCCC),
                            focusedIndicatorColor = Color(0xFF556B5A)
                    )
            )

            // Last name field
            Text(
                    text = "Last name",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
            )
            TextField(
                    value = lastName.value,
                    onValueChange = { lastName.value = it },
                    placeholder = { Text("Doe") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedIndicatorColor = Color(0xFFCCCCCC),
                            focusedIndicatorColor = Color(0xFF556B5A)
                    )
            )

            // Email field
            Text(
                    text = "Email",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
            )
            TextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    placeholder = { Text("tilly.doe@example.com") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedIndicatorColor = Color(0xFFCCCCCC),
                            focusedIndicatorColor = Color(0xFF556B5A)
                    )
            )

            // Register button
            Button(
                    onClick = {
                        // Validate input
                        if (firstName.value.isBlank() || lastName.value.isBlank() || email.value.isBlank()) {
                            registrationMessage.value = "Registration unsuccessful. Please enter all data."
                        } else {
                            // Save to SharedPreferences
                            val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
                            sharedPreferences.edit {
                                putString("firstName", firstName.value)
                                putString("lastName", lastName.value)
                                putString("email", email.value)
                            }

                            registrationMessage.value = "Registration successful!"

                            // Navigate to Home screen
                            navController?.navigate(Home.route) {
                                popUpTo(0) // Clear the navigation stack
                            }
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
                        text = "Register",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                )
            }

            // Display registration message
            if (registrationMessage.value.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                        text = registrationMessage.value,
                        fontSize = 14.sp,
                        color = if (registrationMessage.value.contains("successful")) Color.Green else Color.Red,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    LittleLemonTheme {
        Onboarding()
    }
}
