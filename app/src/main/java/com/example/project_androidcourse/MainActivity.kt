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
                    MainPage(settingsPage = viewModel::startSettingsPageActivity,addSosStarter =  viewModel::startAddSosActivity)
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

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(modifier: Modifier = Modifier, addSosStarter: () -> Unit, settingsPage: () -> Unit) {
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
            onClick = { },
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
            onClick = { },
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
                            x = 0.1918792724609375.dp,
                            y = 22.dp
                        )
                        .requiredWidth(width = 127.dp)
                        .requiredHeight(height = 74.dp))
            }
        }
        TextButton(
            onClick = { addSosStarter()},
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
                            x = 0.1918792724609375.dp,
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
