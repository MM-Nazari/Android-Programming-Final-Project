package com.example.project_androidcourse

import android.app.Application
import android.content.Context
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.project_androidcourse.ui.theme.Project_AndroidCourseTheme
import kotlin.math.log

public class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ContextHandler.set(applicationContext)
       // viewModel.setAppContext(applicationContext)
        setContent {
            Project_AndroidCourseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainPage(addSosStarter =  viewModel::startAddSosActivity)
                }
            }
        }
    }
}


class MainActivityViewModel: ViewModel() {

    fun startAddSosActivity() {
        // val context = getApplication<Application>().applicationContext
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
}

@Composable
fun MainPage(modifier: Modifier = Modifier, addSosStarter: () -> Unit) {
    Box(
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .requiredHeight(height = 838.dp)
            .background(
                brush = Brush.linearGradient(
                    0f to Color(0xffca263a),
                    1f to Color.Black,
                    start = Offset(180f, 0f),
                    end = Offset(180f, 838f)
                )
            )
    ) {
        TextButton(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 142.dp,
                    y = 654.dp
                )
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
                        .requiredWidth(width = 77.dp)
                        .requiredHeight(height = 70.dp))
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 75.dp,
                    y = 111.dp
                )
                .requiredWidth(width = 211.dp)
                .requiredHeight(height = 227.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 211.dp)
                    .requiredHeight(height = 227.dp)
                    .background(color = Color(0xffd91d34))) {

                TextButton(onClick = { addSosStarter() },
                        modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .fillMaxSize()
                ) {

                    Text(
                        text = "کمک",
                        color = Color(0xff201a1a).copy(alpha = 0.5f),
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 50.sp
                        ),

                        modifier = Modifier
                            .offset(
                                x = 2.724639892578125.dp,
                                y = 20.dp
                            )
                            .background(color = Color.Transparent)
                            .requiredWidth(width = 98.dp)
                            .requiredHeight(height = 113.dp)
                    )
                }

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

@Composable
fun SettingsPage(modifier: Modifier = Modifier) {
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
                    y = 619.dp
                )
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
                            painter = painterResource(id = R.drawable.icons8_whatsapp_50___),
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
                    y = 480.dp
                )
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
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 146.dp,
                    y = 335.dp
                )
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
                .offset(
                    x = 146.dp,
                    y = 191.dp
                )
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
                            painter = painterResource(id = R.drawable.icons8_contacts_50___),
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
    SettingsPage(Modifier)
}