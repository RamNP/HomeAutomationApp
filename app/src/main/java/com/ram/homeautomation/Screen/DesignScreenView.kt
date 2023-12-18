package com.ram.homeautomation.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ram.homeautomation.data.DataSendButton

@Composable
fun GroundFloorViewScreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxWidth() ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "GroundFloorViewScreen")
         DataSendButton()
    }

}



@Composable
fun FirstFloorViewScreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxWidth() ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "FirstFloorViewScreen")
        DataSendButton()
    }

}