package com.ram.homeautomation.Screen

import android.bluetooth.BluetoothDevice
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ram.homeautomation.R
import com.ram.homeautomation.navigation.FloorList


@Composable
fun MainScreen(bondedDevices:MutableList<BluetoothDevice>) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 60.dp)) {
        Card(
            modifier = Modifier
                .padding(20.dp)
                .wrapContentSize()
                .clickable {
//                    navController.navigate(FloorList.GroundFloorScreen.route)
                },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth(),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_floor),
                    contentDescription = null,

                    )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = " Ground Floor", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.padding( end =80.dp))
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Send Data")
                    }

                }
            }
        }
        Card(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .clickable {
//                    navController.navigate(FloorList.FirstFloorScreen.route)
                },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {

                Column(modifier = Modifier.padding(12.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = null,
                    )
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "First Floor", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.padding( end =100.dp))
                         Button(onClick = { /*TODO*/ }) {
                          Text(text = "Send Data")
                      }
                    }
                    
            }
        }
    }

}


