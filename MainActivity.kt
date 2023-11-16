package com.example.sos

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.sos.ui.theme.SOSTheme
import android.Manifest
import androidx.core.app.ActivityCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SOSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val composableFunctions = ComposableFunctions();
                    composableFunctions.buttonHandler()
                }
            }
        }
    }
}

class ComposableFunctions {
    @Composable
    fun buttonHandler(
        modifier: Modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        val context = LocalContext.current
        val activity = MainActivity()
        val READ_CONTACTS_PERMISSION_REQUEST = 1
        val SEND_SMS_PERMISSION_REQUEST = 2
        val READ_PHONE_STATE_PERMISSION_REQUEST = 3
        val CALL_PHONE_PERMISSION_REQUEST = 4
        val MODIFY_PHONE_PERMISSION_REQUEST = 5

        Button(onClick = {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.SEND_SMS
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_PHONE_STATE
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.MODIFY_PHONE_STATE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                sendSMS()
            } else {
                if(ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.SEND_SMS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(
                            Manifest.permission.SEND_SMS
                        ),
                        SEND_SMS_PERMISSION_REQUEST
                    )
                }
                if(ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.SEND_SMS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(
                            Manifest.permission.SEND_SMS
                        ),
                        READ_PHONE_STATE_PERMISSION_REQUEST
                    )
                }
                if(ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.SEND_SMS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(
                            Manifest.permission.SEND_SMS
                        ),
                        CALL_PHONE_PERMISSION_REQUEST
                    )
                }
                if(ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.SEND_SMS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(
                            Manifest.permission.SEND_SMS
                        ),
                        MODIFY_PHONE_PERMISSION_REQUEST
                    )
                }
            }
        }) {
            Text(stringResource(R.string.SOS))
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_CONTACTS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(
                        Manifest.permission.READ_CONTACTS
                    ),
                    READ_CONTACTS_PERMISSION_REQUEST
                )
            } else {
                readContacts()
            }
        }) {
            Text(stringResource(R.string.chooseContacts))
        }
    }

    fun sendSMS(){

    }

    fun readContacts(){

    }
}