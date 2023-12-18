package com.ram.homeautomation

import android.bluetooth.BluetoothDevice
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ram.homeautomation.Screen.MainScreen
import com.ram.homeautomation.navigation.NavigationScreenView

@Suppress("UNUSED_EXPRESSION")
class FloorActivity : AppCompatActivity() {
    private val bondedDevices = mutableListOf<BluetoothDevice>()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floor)

        setContent {

        }

    }
}



@Composable
fun ScreenView(
    bondedDevices:MutableList<BluetoothDevice>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxHeight(0.9f)
            .fillMaxWidth()
            .padding(5.dp)
    ) {

        Spacer(modifier = Modifier.height(10.dp))
        for(device in bondedDevices) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "sdhk,fsjhdfjksfdfs")
            Text(text = "sdhk,fsjhdfjksfdfs")
            Text(text = "sdhk,fsjhdfjksfdfs")
            Text(text = "sdhk,fsjhdfjksfdfs")
            Text(text = "sdhk,fsjhdfjksfdfs")
            Text(text = "sdhk,fsjhdfjksfdfs")

        }

    }
}
