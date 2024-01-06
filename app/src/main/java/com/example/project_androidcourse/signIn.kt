package com.example.project_androidcourse

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import java.security.MessageDigest
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun hashPassword(password: String): String {
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(password.toByteArray())
    return digest.fold("", { str, it -> str + "%02x".format(it) })
}


fun login(username: String, password: String): String {
    val context = ContextHandler.get()
    val userDao = context?.let { UserDatabase.getDatabase(it).getDatabaseDao() }
    val user = userDao?.getUserByUsername(username)
    if (user != null) {
        val inputPasswordHash = hashPassword(password)
        if (user.password == inputPasswordHash) {
            return "Sign in successful"
        }
    }
    return "Wrong username or password"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIn(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .requiredHeight(height = 838.dp)
            .background(brush = Brush.linearGradient(
                0f to Color(0xffca263a),
                1f to Color.Black,
                start = Offset(180f, 0f),
                end = Offset(180f, 838f)))
    ) {
        Text(
            text = "Sign in",
            color = Color(0xff5e0707),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 130.dp,
                    y = 46.dp))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 27.dp,
                    y = 232.dp)
                .requiredWidth(width = 307.dp)
                .requiredHeight(height = 51.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.phuserlight),
                contentDescription = "ph:user-light",
                colorFilter = ColorFilter.tint(Color(0xff340303).copy(alpha = 0.78f)),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 6.dp,
                        y = 10.03271484375.dp)
                    .requiredWidth(width = 35.dp)
                    .requiredHeight(height = 31.dp))
            OutlinedTextField(
                value = username,
                onValueChange = { Username = username},
                modifier = Modifier
                    .requiredWidth(width = 307.dp)
                    .requiredHeight(height = 51.dp)
                    .clip(shape = RoundedCornerShape(15.dp)))
            Text(
                text = "User Name",
                color = Color(0xff340303).copy(alpha = 0.78f),
                style = TextStyle(
                    fontSize = 15.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 46.dp,
                        y = 16.dp)
                    .requiredWidth(width = 204.dp)
                    .requiredHeight(height = 18.dp))
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 27.dp,
                    y = 323.dp)
                .requiredWidth(width = 307.dp)
                .requiredHeight(height = 48.dp)
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 274.dp,
                        y = 13.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 16.dp)
                        .requiredHeight(height = 19.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.vector),
                        contentDescription = "Vector",
                        modifier = Modifier
                            .fillMaxSize())
                }
            }
            Image(
                painter = painterResource(id = R.drawable.mdipasswordoutline),
                contentDescription = "mdi:password-outline",
                colorFilter = ColorFilter.tint(Color(0xff340303).copy(alpha = 0.75f)),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 7.dp,
                        y = 9.90478515625.dp)
                    .requiredWidth(width = 34.dp)
                    .requiredHeight(height = 28.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {},
                modifier = Modifier
                    .requiredWidth(width = 307.dp)
                    .requiredHeight(height = 48.dp)
                    .clip(shape = RoundedCornerShape(15.dp)))
            Text(
                text = "Password",
                color = Color(0xff340303).copy(alpha = 0.75f),
                style = TextStyle(
                    fontSize = 15.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 46.dp,
                        y = 15.dp)
                    .requiredWidth(width = 131.dp)
                    .requiredHeight(height = 18.dp))
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 37.dp,
                    y = 589.dp)
                .requiredWidth(width = 286.dp)
                .requiredHeight(height = 50.dp)
        ) {
            Button(
                onClick = { login(username = username,password=password) },
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffb11212).copy(alpha = 0.97f)),
                modifier = Modifier
                    .requiredWidth(width = 286.dp)
                    .requiredHeight(height = 50.dp)){ }
            Text(
                text = "Sign in",
                color = Color(0xff310202),
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 112.dp,
                        y = 16.dp)
                    .requiredWidth(width = 94.dp))
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 75.dp,
                    y = 679.dp)
                .requiredWidth(width = 210.dp)
                .requiredHeight(height = 18.dp)
        ) {
            Text(
                text = "Don’t have account ? ",
                color = Color(0xffc70c0c),
                style = TextStyle(
                    fontSize = 15.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = (-30.5).dp,
                        y = 0.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffc70c0c)),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = 76.5.dp,
                        y = 0.dp)){ }
        }

    }
}

@Preview(widthDp = 360, heightDp = 838)
@Composable
private fun SignInPreview() {
    SignIn(Modifier)
}