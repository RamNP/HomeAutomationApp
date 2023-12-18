package com.ram.homeautomation

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle

class BluetoothDiscoveryActivity : Activity() {

    private val bluetoothAdapter: BluetoothAdapter? by lazy {
        BluetoothAdapter.getDefaultAdapter()
    }

    @SuppressLint("MissingPermission")
    private val bluetoothReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                BluetoothDevice.ACTION_FOUND -> {
                    // A Bluetooth device is found.
                    val device =
                        intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    val deviceName = device?.name ?: "Unknown Device"
                    val deviceAddress = device?.address ?: "Unknown Address"
                    // Handle the found device as needed.
                    println("Found Bluetooth Device: $deviceName - $deviceAddress")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth_discovery)
        // Check if the device supports Bluetooth.
        if (bluetoothAdapter == null) {
            println("Device does not support Bluetooth")
            return
        }

        // Enable Bluetooth if it's not already enabled.
        @SuppressLint("MissingPermission")
        if (!bluetoothAdapter!!.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)

        } else {
            startBluetoothDiscovery()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK) {
            startBluetoothDiscovery()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
//            finish()
        } else {
            println("Bluetooth is not enabled, cannot discover devices.")
        }
    }
    @SuppressLint("MissingPermission")
    private fun startBluetoothDiscovery() {
        // Register the BroadcastReceiver for Bluetooth discovery.
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(bluetoothReceiver, filter)

        // Start discovery.
        if (bluetoothAdapter!!.startDiscovery()) {
            println("Bluetooth discovery started.")

        } else {
            println("Failed to start Bluetooth discovery.")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Unregister the BroadcastReceiver when the activity is destroyed.
        unregisterReceiver(bluetoothReceiver)
    }

    companion object {
        private const val REQUEST_ENABLE_BT = 1
    }
}
