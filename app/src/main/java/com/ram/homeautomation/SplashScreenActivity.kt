package com.ram.homeautomation

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : ComponentActivity() {
    private val REQUEST_ENABLE_BT = 0

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
        )
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            },
            1000
        )
        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
//                BluetoothApp()
                Text(text = "0x53 0x68 0x75 0x6A 0x61", fontSize = 30.sp)
//                Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription = "")
            }
        }
    }

    @Composable
    fun BluetoothApp() {
        var isBluetoothEnabled by remember { mutableStateOf(false) }
        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        val context = LocalContext.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (isBluetoothEnabled) "Bluetooth is ON" else "Bluetooth is OFF",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (isBluetoothEnabled) {
                        disableBluetooth()
                    } else {
                        enableBluetooth()
                    }
                }
            ) {
                Text(if (isBluetoothEnabled) "Turn OFF Bluetooth" else "Turn ON Bluetooth")
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun enableBluetooth() {
        val bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        bluetoothAdapter?.takeIf { !it.isEnabled }?.apply {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivity(enableBtIntent)
            if (!bluetoothAdapter.isEnabled) {
//            showToast("Turning on Bluetooth..")
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(intent, REQUEST_ENABLE_BT)
            } else {
//            showToast("Bluetooth is already on")
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun disableBluetooth() {
        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        bluetoothAdapter?.takeIf { it.isEnabled }?.apply {
            disable()
        }
    }

}


