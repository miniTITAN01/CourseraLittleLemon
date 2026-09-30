package com.example.littlelemon.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.MenuItemRoom
import com.example.littlelemon.Profile
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Home(navController: NavHostController? = null, menuItems: LiveData<List<MenuItemRoom>>? = null) {
    val searchPhrase = remember { mutableStateOf("") }
    val selectedCategory = remember { mutableStateOf("") }
    val menuItemsList = menuItems?.observeAsState()?.value ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header with logo and profile icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo on the left
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f),
                contentScale = ContentScale.Fit
            )

            // Profile icon on the right
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController?.navigate(Profile.route) },
                contentScale = ContentScale.Fit
            )
        }

        // Main content with scroll
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            item {
                // Hero Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF556B5A))
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Little Lemon",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFF4D54B),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )

                    Text(
                        text = "Chicago",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                            fontSize = 14.sp,
                            color = Color.White,
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 16.dp)
                        )

                        // Hero image
                        Image(
                            painter = painterResource(id = R.drawable.hero_image),
                            contentDescription = "Hero Image",
                            modifier = Modifier
                                .size(100.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    // Search bar
                    TextField(
                        value = searchPhrase.value,
                        onValueChange = { searchPhrase.value = it },
                        placeholder = { Text("Enter search phrase") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        )
                    )
                }
            }

            item {
                // Order for Delivery section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "ORDER FOR DELIVERY!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // Category buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("All", "Starters", "Mains", "Desserts").forEach { category ->
                            OutlinedButton(
                                onClick = { selectedCategory.value = category },
                                modifier = Modifier.height(36.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = if (selectedCategory.value == category) Color(0xFF556B5A) else Color.White,
                                    contentColor = if (selectedCategory.value == category) Color.White else Color(0xFF556B5A)
                                )
                            ) {
                                Text(
                                    text = category,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            // Menu items
            val filteredItems = menuItemsList.filter { item ->
                val matchesSearch = searchPhrase.value.isEmpty() || item.title.contains(searchPhrase.value, ignoreCase = true) || item.description.contains(searchPhrase.value, ignoreCase = true)
                val matchesCategory = selectedCategory.value.isEmpty() || selectedCategory.value == "All" || item.category.lowercase() == selectedCategory.value.lowercase()
                matchesSearch && matchesCategory
            }

            items(filteredItems) { menuItem ->
                MenuItemRow(menuItem)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItemRow(menuItem: MenuItemRoom) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFE8E8E8)
            )
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 12.dp)
        ) {
            Text(
                text = menuItem.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 1
            )

            Text(
                text = menuItem.description,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
                maxLines = 2
            )

            Text(
                text = "$%.2f".format(menuItem.price),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF556B5A)
            )
        }

        // Menu item image
        if (menuItem.image.isNotEmpty()) {
            GlideImage(
                model = menuItem.image,
                contentDescription = menuItem.title,
                modifier = Modifier
                    .size(100.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    LittleLemonTheme {
        Home()
    }
}

