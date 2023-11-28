package com.yash.tweetsyapp.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yash.tweetsyapp.viewmodels.CategoryViewModel
import com.yash.tweetsyapp.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryScreen(onClick: (category: String) -> Unit) {

    val categoryViewModel:CategoryViewModel = hiltViewModel()
    val categories: State<List<String>> = categoryViewModel.categories.collectAsState()

    if(categories.value.isEmpty()){

        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ){
//            Text(text = "Loading...", style = MaterialTheme.typography.h3 , color = Color.Black)
            RotatingImage()
        }

    }else {

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround,
            content = {
                items(categories.value.distinct()) { item ->
                    CategoryItem(category = item, onClick)
                }
            }
        )

    }

}

@Composable
fun RotatingImage() {
    var rotationState by remember { mutableStateOf(0f) }

    // Infinite rotation loop
    LaunchedEffect(Unit) {
        while (true) {
            delay(1) // Adjust the delay as needed for the desired rotation speed
            rotationState = (rotationState + 5) % 360
        }
    }

    Image(
        painter = painterResource(id = R.drawable.ic_baseline_rotate_right_24),
        contentDescription = null,
        modifier = Modifier
            .size(48.dp)
            .rotate(rotationState)
    )
}

@Composable
fun CategoryItem(category: String , onClick: (category: String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick(category) }
            .size(160.dp)
            .clip(RoundedCornerShape(8.dp))
            .paint(
                painter = painterResource(id = R.drawable.wave_haikei),
                contentScale = ContentScale.Crop
            )
            .border(1.dp, Color(0xFFEEEEEE)),
        contentAlignment = Alignment.BottomCenter
    )
    {
        Text(
            text = category,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(0.dp , 20.dp),
            style = MaterialTheme.typography.body1
        )
    }
}