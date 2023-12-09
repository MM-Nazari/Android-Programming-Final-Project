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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.project_androidcourse.ui.theme.Project_AndroidCourseTheme
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

data class Contacts(val name:String, val phoneNumber:String)

class SettingsPageActivity : ComponentActivity() {
//    private val viewModel by viewModels<AddSettingsPageViewModel>()
    object cContactList {
        val chosenContactsList = mutableSetOf<Contacts>()
    }
    private val viewModel by viewModels<SettingPageViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ContextHandler.set(applicationContext)

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                1
            )
        }
        setContent {
            Project_AndroidCourseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SettingsPage(addSosStarter = viewModel::startAddSosActivity, addContactStareter = viewModel::startContactsPageActivity)

                }

            }
        }
    }
}

class SettingPageViewModel: ViewModel() {
    fun startAddSosActivity() {
        try {

            val context = ContextHandler.get()
            if(context != null) {
                val intent = Intent(ContextHandler.get(), AddSosActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent)
            }
        } catch (ex: Exception) {
            Log.e("Crash", ex.toString())
        }
    }

    fun startContactsPageActivity() {
        try {

            val context = ContextHandler.get()
            if(context != null) {
                val intent = Intent(ContextHandler.get(), ContactsPageActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent)
            }
        } catch (ex: Exception) {
            Log.e("Crash", ex.toString())
        }
    }
}

@Composable
fun SettingsPage(modifier: Modifier = Modifier,  addSosStarter: () -> Unit, addContactStareter:() -> Unit) {
    Box(
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .requiredHeight(height = 834.dp)
            .background(
                brush = Brush.linearGradient(
                    0f to Color(0xffc72c3f),
                    1f to Color.Black,
                    start = Offset(180f, 0f),
                    end = Offset(180f, 834f)
                )
            )
        ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 146.dp,
                    y = 157.dp
                )
                .requiredWidth(width = 68.dp)
                .requiredHeight(height = 65.dp)
            ) {
            TextButton(
                onClick = {addContactStareter()},
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .requiredWidth(width = 68.dp)
                    .requiredHeight(height = 65.dp)
                ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 68.dp)
                        .requiredHeight(height = 65.dp)
                    ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 68.dp)
                            .requiredHeight(height = 65.dp)
                        ) {
                        Image(
                            painter = painterResource(id = R.drawable.icons8_contacts_50___),
                            contentDescription = "Ellipse 4",
                            modifier = Modifier
                                .requiredWidth(width = 68.dp)
                                .requiredHeight(height = 65.dp))
                        }
                    }
                }
            }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 146.dp,
                    y = 318.dp
                )
                .requiredWidth(width = 68.dp)
                .requiredHeight(height = 65.dp)
            ) {
            TextButton(
                onClick = {addSosStarter()},
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .requiredWidth(width = 68.dp)
                    .requiredHeight(height = 65.dp)
                ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 68.dp)
                        .requiredHeight(height = 65.dp)
                    ) {
                    Box(
                        modifier = Modifier
                                                .requiredWidth(width = 68.dp)
                                                .requiredHeight(height = 65.dp)
                        ) {
                        Image(
                            painter = painterResource(id = R.drawable.icons8_text_50),
                            contentDescription = "Ellipse 4",
                            modifier = Modifier
                                                        .requiredWidth(width = 68.dp)
                                                        .requiredHeight(height = 65.dp))
                        }
                    }
                }
            }
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 146.dp,
                                    y = 480.dp)
                        .requiredWidth(width = 68.dp)
                        .requiredHeight(height = 65.dp)
            ) {
            TextButton(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier
                                .requiredWidth(width = 68.dp)
                                .requiredHeight(height = 65.dp)
                ) {
                Box(
                    modifier = Modifier
                                        .requiredWidth(width = 68.dp)
                                        .requiredHeight(height = 65.dp)
                    ) {
                    Box(
                        modifier = Modifier
                                                .requiredWidth(width = 68.dp)
                                                .requiredHeight(height = 65.dp)
                        ) {
                        Image(
                            painter = painterResource(id = R.drawable.icons8_telegram_50___),
                            contentDescription = "Ellipse 4",
                            modifier = Modifier
                                                        .requiredWidth(width = 68.dp)
                                                        .requiredHeight(height = 65.dp))
                        }
                    }
                }
            }
        }
 }

@Preview(widthDp = 360, heightDp = 834)
@Composable
private fun SettingsPagePreview() {
    Project_AndroidCourseTheme {
        //MainPage(Modifier)
    }
}