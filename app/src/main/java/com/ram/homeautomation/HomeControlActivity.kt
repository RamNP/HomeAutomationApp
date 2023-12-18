package com.ram.homeautomation

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.ram.homeautomation.data.BluetoothDataStream
import java.io.FileOutputStream
import java.io.IOException

@Suppress("DEPRECATION")
class HomeControlActivity : ComponentActivity() {

    private lateinit var bluetoothAdapter: BluetoothAdapter
    private lateinit var address: String
    private lateinit var connection: BluetoothHandler
    private lateinit var bluetoothSocket: BluetoothSocket
    private var sensorDataList = arrayOf("")

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bluetoothAdapter = getSystemService(BluetoothManager::class.java).adapter
        address = intent.getStringExtra("Address")!!
        connection = BluetoothHandler(bluetoothAdapter, address)
        connection.start()
        bluetoothSocket = connection.createSocket(bluetoothAdapter.getRemoteDevice(address))
        Thread {
            try {

                bluetoothSocket.connect()
            } catch (_: Exception) {

            }
        }.start()

        setContent {
            Display()
            ControlPanel()
        }
    }

    @Preview
    @Composable
    fun ControlPanel() {
//        val navController = rememberNavController()
        // Main Column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp)
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Column(modifier = Modifier.fillMaxSize()) {
                Row() {
                    Text(text = "First Floor", color = Color.Black , fontSize = 24.sp)
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier
                            .width(200.dp)
                            .height(250.dp)
                    ) {
                        Text(text = "Kitchen Room", Modifier.padding(start = 50.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_kitechen),
                            contentDescription = null, modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                                .padding( start = 20.dp)

                        )
                        ControlButton(
                            text = "Kitchen Light",
                            data = "K",
                            modifier = Modifier

                        )

                    }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Card(
                        modifier = Modifier
                            .width(200.dp)
                            .height(250.dp)
                    ) {
                        Text(text = "Hall Room", Modifier.padding(start = 50.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_hall),
                            contentDescription = null, modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                                .padding( start = 20.dp)


                        )
                        ControlButton(
                            text = "Hall light",
                            data = "H",
                            modifier = Modifier

                        )

                    }
                }

//Second floor
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Text(text = "Second Floor", color = Color.Black , fontSize = 24.sp)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {

                    Card(
                        modifier = Modifier
                            .height(350.dp)
                            .width(450.dp),
                        colors = CardDefaults.cardColors(Color.White)

                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_light),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(100.dp)
                            )
                            Spacer(modifier = Modifier.padding(30.dp))
                            ControlButton(
                                text = "Living room Light",
                                data = "L",
                                modifier = Modifier.padding(start = 100.dp)
                            )
                        }

                        //card1
                        Card(
                            modifier = Modifier
                                .height(300.dp)
                                .width(450.dp),
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_light),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(70.dp)
                                        .width(70.dp)
                                )
                                Spacer(modifier = Modifier.padding(50.dp))
                                ControlButton(
                                    text = "Room Light",
                                    data = "R",
                                    modifier = Modifier.padding(start = 100.dp)
                                )
                            }

                            //card2
                            Card(
                                modifier = Modifier
                                    .height(120.dp)
                                    .width(450.dp)
                                    .padding(start = 80.dp),
                                colors = CardDefaults.cardColors(Color.White)

                            ) {

                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp)) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_curtain),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(70.dp)
                                            .width(70.dp)
                                            .padding(start = 10.dp)
                                    )
                                    Spacer(modifier = Modifier.padding(20.dp))
                                    ControlButton(
                                        text = "Curtain Light",
                                        data = "C",
                                        modifier = Modifier.padding(start = 30.dp)
                                    )
                                }


                            }

                        }


                    }

                }

            }
        }

    }


    private fun saveData(data: String) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream("HCO5_data.txt")
            fileOutputStream.write(data.toByteArray())
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }


    @Composable
    private fun Data(width: Float = 1f) {
        // Get the device screen width
        val configuration = LocalConfiguration.current
        val widgetWidth = configuration.screenWidthDp.dp / 2

        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .border(width = 1.dp, shape = RoundedCornerShape(2.dp), color = Color.Black)
                .background(color = Color.Black),
        ) {
            var sensorValue: String by remember {
                mutableStateOf("")
            }

            val inputStream = bluetoothSocket.inputStream
            val sensorBuffer = ByteArray(1024)
            var sensorBytes: Int
            var sensorReadMessage: String
            Thread {
                // While the hardware is sending data, print the received bytes
                while (true) {
                    try {
                        sensorBytes = inputStream.read(sensorBuffer)
                        val sensorReceiveData = String(sensorBuffer, 0, sensorBytes)
                        if (sensorReceiveData.isNotEmpty()) {
                            sensorReadMessage = sensorReceiveData
                            runOnUiThread {
                                Handler().postDelayed(
                                    {
                                        sensorValue += sensorReceiveData
                                        sensorDataList.plus(sensorReceiveData)
                                    }, 100
                                )
                            }
                        }

                    } catch (e: IOException) {
                        e.printStackTrace()
                        break
                    }
                }
            }.start()
            ScrollableText(text = sensorValue)
        }
    }

    @Composable
    private fun ScrollableText(text: String) {

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 10.dp),
        ) {

            for (data in sensorDataList.distinct()) {
                saveData(data + "\n")
                item {
                    Text(
                        text, fontSize = 20.sp, color = Color(0xFF33FF00),
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun Display() {
        Row(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
                .border(
                    shape = RectangleShape,
                    width = 1.dp,
                    color = Color.White
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Data(width = 0.5f)
        }
    }

    @Composable
    fun ControlButton(
        text: String,
        width: Dp = 160.dp,
        data: String,
        modifier: Modifier
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
                    .width(width)
                    .height(40.dp),
                onClick = { sendData(data) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff1b1b1b))
            ) {
                Text(text = text)
            }

        }
    }

    @SuppressLint("MissingPermission")
    fun sendData(data: String) {
        Thread { BluetoothDataStream(bluetoothSocket).sendData(data) }.start()
    }


}