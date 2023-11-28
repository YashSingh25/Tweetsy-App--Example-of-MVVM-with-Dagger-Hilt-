package com.yash.tweetsyapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yash.tweetsyapp.models.TweetListItem
import com.yash.tweetsyapp.viewmodels.DetailViewModel

@Composable
fun DetailScreen() {
    val detailViewModel:DetailViewModel = hiltViewModel()
    val tweets: State<List<TweetListItem>> = detailViewModel.tweets.collectAsState()

    if(tweets.value.isEmpty()){

        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ){
//            Text(text = "Loading...", style = MaterialTheme.typography.h3 , color = Color.Black)
            RotatingImage()
        }

    }else {

        LazyColumn(
            content = {
                items(tweets.value) {
                    TweetListItem(tweet = it.text)
                }
            }
        )

    }
}

@Composable
fun TweetListItem(tweet:String) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        border = BorderStroke(1.dp , Color(0xFFCCCCCC))
    ){
        Text(
            text = tweet,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.body2
        )
    }
}