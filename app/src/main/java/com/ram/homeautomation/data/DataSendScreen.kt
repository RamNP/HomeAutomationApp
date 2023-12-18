package com.ram.homeautomation.data

import android.annotation.SuppressLint
import android.bluetooth.BluetoothSocket
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private lateinit var bluetoothSocket: BluetoothSocket
@Composable
fun DataSendButton(
    text: String,
    width: Dp = 100.dp,
    data: String,
) {
    Box {
        Spacer(
            modifier = Modifier
                .height(5.dp)
                .width(10.dp)
        )
        ElevatedButton(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 5.dp)
                .fillMaxWidth(),
            onClick = { sendData(data) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(text = "SendData")
        }

    }
}


@SuppressLint("MissingPermission")
fun sendData(data: String) {
    Thread { BluetoothDataStream(bluetoothSocket).sendData(data) }.start()
}
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun DataSendButton(){
        Row(modifier = Modifier.fillMaxWidth() ,

            ) {
            DataSendButton(text = "SendData", width = 100.dp, data = "3")

        }
    }

