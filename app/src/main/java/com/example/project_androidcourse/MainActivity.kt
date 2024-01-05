package com.example.project_androidcourse

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.project_androidcourse.ui.theme.Project_AndroidCourseTheme
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Handler
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.getSystemService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface TelegramBotService {
    @FormUrlEncoded
    @POST("sendMessage")
    fun sendMessage(
        @Query("chat_id") chatId: String,
        @Query("text") text: String
    ): String
}

public class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainActivityViewModel>()
    object locManager{
        lateinit var locationManager: LocationManager
    }
    object instance{
        lateinit var context: Context
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ContextHandler.set(applicationContext)
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_FINE_LOCATION),
                2
            )
        }
        locManager.locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        instance.context = this
        setContent {
            Project_AndroidCourseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainPage(settingsPage = viewModel::startSettingsPageActivity)
                }
            }
        }
    }
}

class MainActivityViewModel: ViewModel() {

    fun startSettingsPageActivity() {
        try {

            val context = ContextHandler.get()
            if(context != null) {
                val intent = Intent(ContextHandler.get(), SettingsPageActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent)
            }
        } catch (ex: Exception) {
            Log.e("Crash", ex.toString())
        }
    }
}

@SuppressLint("MissingPermission")
private fun getLocation(): String {
    var locationLink = ""
    val location = MainActivity.locManager.locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
    locationLink = if (location != null){
        val latitude = location.latitude
        val longitude = location.longitude
        "https://www.google.com/maps/place/$latitude,$longitude"
    }
    else{
        "Sorry, there is no valid data"
    }
    return locationLink
}

private fun makeMissCall(phoneNumber: String){
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    MainActivity.instance.context.startActivity(intent)
}

private fun sendMessageTelegram(userName: String, chatId: String, priority: String){
    /*val bot = object : TelegramLongPollingBot(){
        override fun getBotToken(): String {
            return "6477692240:AAGTWzQHKM7syt8q6fX2lZrQ3jjGKXMx8AQ"
        }

        override fun getBotUsername(): String {
            return "PDPSOSBot"
        }

        override fun onUpdateReceived(update: Update?) {
            TODO("Not yet implemented")
            //implemented in python file
        }
    }
    val sendMessage = SendMessage()
    var locationLink = getLocation()
    sendMessage.chatId = chatId
    sendMessage.text = "Hi ${userName}\nI need your help\nlast known location:$locationLink\nPriority:$priority\nfrom SOS App"
    try{
        val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
        botsApi.registerBot(bot)
        bot.execute(sendMessage)
    } catch (e: TelegramApiException){
        e.printStackTrace()
    }*/
    var locationLink = getLocation()
    var messageText = "message from: ${userName}\nI need your help\nlast known location:$locationLink\nPriority:$priority\nfrom SOS App"
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.telegram.org/bot6477692240:AAGTWzQHKM7syt8q6fX2lZrQ3jjGKXMx8AQ/")  // Replace <YourBotToken> with your actual bot token
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val telegramBotService = retrofit.create(TelegramBotService::class.java)
    telegramBotService.sendMessage(chatId, messageText)
}

private fun sendSMS(priority: String) {
    var locationLink = getLocation()
    var obj = SmsManager.getDefault()
    val iterate = SettingsPageActivity.cContactList.chosenContactsList.iterator()
    while (iterate.hasNext()) {
        val nextContact = iterate.next()
        obj.sendTextMessage(
            nextContact.phoneNumber,
            null,
            "Hi ${nextContact.name}\nI need your help\nlast known location:$locationLink\nPriority:$priority\nfrom SOS App",
            null,
            null
        )
    }
    sendMessageTelegram("arash", "133792179", priority)
    makeMissCall(ContactsPageActivity.mostImportantContact.chosenContact)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(modifier: Modifier = Modifier, settingsPage: () -> Unit) {
    Box(
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .requiredHeight(height = 838.dp)
            .background(
                brush = Brush.linearGradient(
                    0f to Color(0xFFFFFFFF/*0xffca263a*/),
                    1f to Color.Black,
                    start = Offset(180f, 0f),
                    end = Offset(180f, 1838f)
                )
            )
    ) {
        TextButton(
            onClick = { settingsPage()},
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 142.dp,
                    y = 709.dp
                )
                .requiredWidth(width = 77.dp)
                .requiredHeight(height = 70.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 77.dp)
                    .requiredHeight(height = 70.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 77.dp)
                        .requiredHeight(height = 70.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icons8_settings_50___),
                        contentDescription = "Ellipse 3",
                        modifier = Modifier
                            .fillMaxSize())
                }
            }
        }
        TextButton(
            onClick = {sendSMS("HIGH")},
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 97.dp,
                    y = 50.dp
                )
                .requiredWidth(width = 165.dp)
                .requiredHeight(height = 168.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 165.dp)
                    .requiredHeight(height = 168.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 165.dp)
                        .requiredHeight(height = 168.dp)
                        .background(color = Color(0xffd91d34)))
                Text(
                    text = "فوریت بالا",
                    color = Color(0xff201a1a).copy(alpha = 0.5f),
                    style = TextStyle(
                        fontSize = 20.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(
                            x = 15.191879272460938.dp,
                            y = 22.dp
                        )
                        .requiredWidth(width = 107.dp)
                        .requiredHeight(height = 74.dp))
            }
        }
        TextButton(
            onClick = {sendSMS("MEDIUM")},
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 97.dp,
                    y = 241.dp
                )
                .requiredWidth(width = 165.dp)
                .requiredHeight(height = 168.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 165.dp)
                    .requiredHeight(height = 168.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 165.dp)
                        .requiredHeight(height = 168.dp)
                        .background(color = Color(0xffd91d34)))
                Text(
                    text = "فوریت متوسط",
                    color = Color(0xff201a1a).copy(alpha = 0.5f),
                    style = TextStyle(
                        fontSize = 20.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(
                            x = 15.191879272460938.dp,
                            y = 22.dp
                        )
                        .requiredWidth(width = 127.dp)
                        .requiredHeight(height = 74.dp))
            }
        }
        TextButton(
            onClick = {sendSMS("LOW")},
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 98.dp,
                    y = 432.dp
                )
                .requiredWidth(width = 165.dp)
                .requiredHeight(height = 168.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 165.dp)
                    .requiredHeight(height = 168.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 165.dp)
                        .requiredHeight(height = 168.dp)
                        .background(color = Color(0xffd91d34)))
                Text(
                    text = "فوریت پایین",
                    color = Color(0xff201a1a).copy(alpha = 0.5f),
                    style = TextStyle(
                        fontSize = 20.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(
                            x = 15.191879272460938.dp,
                            y = 22.dp
                        )
                        .requiredWidth(width = 107.dp)
                        .requiredHeight(height = 74.dp))
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 838)
@Composable
private fun MainPagePreview() {
    Project_AndroidCourseTheme {
        //MainPage(Modifier)
    }

}
