package com.example.project_androidcourse

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.sp

var Username: String = ""
fun registerUser(
    name: String,
    lastname: String,
    username: String,
    password: String) {
    val context = ContextHandler.get()
    val userDao = context?.let { UserDatabase.getDatabase(it).getDatabaseDao() }
    val existingUser = userDao?.getUserByUsername(username)
    if (existingUser != null) {
        Toast.makeText(context,"Username already exists.",Toast.LENGTH_SHORT).show()
        return
    }

    val hashedPassword = hashPassword(password)
    val newUser =
        User(name = name, lastname = lastname, username = username, password = hashedPassword)
    if (userDao != null) {
        userDao.insertUser(newUser)
    }
    Toast.makeText(context,"User registered successfully.",Toast.LENGTH_SHORT).show()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
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
            text = "Sign Up",
            color = Color(0xff620202),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 130.dp,
                    y = 46.dp))
        TextButton(
            onClick = { registerUser(name = name, lastname = lastname,username=username,password=password) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 37.dp,
                    y = 595.dp)
                .requiredWidth(width = 286.dp)
                .requiredHeight(height = 50.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 286.dp)
                    .requiredHeight(height = 50.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 286.dp)
                        .requiredHeight(height = 50.dp)
                        .clip(shape = RoundedCornerShape(15.dp))
                        .background(color = Color(0xffe50505).copy(alpha = 0.5f)))
                Text(
                    text = "Sign Up",
                    color = Color(0xff340202),
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 112.dp,
                            y = 16.dp)
                        .requiredWidth(width = 94.dp))
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 50.dp,
                    y = 685.dp)
                .requiredWidth(width = 242.dp)
                .requiredHeight(height = 18.dp)
        ) {
            Text(
                text = "Already have an account ? ",
                color = Color(0xffc70c0c),
                style = TextStyle(
                    fontSize = 15.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = (-27.5).dp,
                        y = 0.dp))
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffc70c0c)),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = 96.dp,
                        y = 0.dp)){ }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 29.dp,
                    y = 202.dp)
                .requiredWidth(width = 302.dp)
                .requiredHeight(height = 50.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.phuserlight),
                contentDescription = "ph:user-light",
                colorFilter = ColorFilter.tint(Color(0xff2d0202).copy(alpha = 0.75f)),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 6.dp,
                        y = 10.dp)
                    .requiredWidth(width = 26.dp)
                    .requiredHeight(height = 30.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .requiredWidth(width = 302.dp)
                    .requiredHeight(height = 50.dp)
                    .clip(shape = RoundedCornerShape(15.dp)))
            Text(
                text = "First Name",
                color = Color(0xff2d0202).copy(alpha = 0.75f),
                style = TextStyle(
                    fontSize = 15.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 37.dp,
                        y = 16.dp)
                    .requiredWidth(width = 152.dp))
            TextField(
                value = name,
                textStyle = TextStyle(textDirection = TextDirection.Content),
                maxLines = 3,
                onValueChange = {},

                modifier = modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 50.dp)
                    .background(color = Color.Black)
                    .align(alignment = Alignment.BottomCenter)

            )
        }

        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 29.dp,
                    y = 202.dp)
                .requiredWidth(width = 302.dp)
                .requiredHeight(height = 50.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.phuserlight),
                contentDescription = "ph:user-light",
                colorFilter = ColorFilter.tint(Color(0xff2d0202).copy(alpha = 0.75f)),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 6.dp,
                        y = 10.dp)
                    .requiredWidth(width = 26.dp)
                    .requiredHeight(height = 30.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .requiredWidth(width = 302.dp)
                    .requiredHeight(height = 50.dp)
                    .clip(shape = RoundedCornerShape(15.dp)))
            Text(
                text = "Last Name",
                color = Color(0xff2d0202).copy(alpha = 0.75f),
                style = TextStyle(
                    fontSize = 15.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 37.dp,
                        y = 16.dp)
                    .requiredWidth(width = 152.dp))
            TextField(
                value = lastname,
                textStyle = TextStyle(textDirection = TextDirection.Content),
                maxLines = 3,
                onValueChange = {},

                modifier = modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 50.dp)
                    .background(color = Color.Black)
                    .align(alignment = Alignment.BottomCenter)

            )
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 29.dp,
                    y = 202.dp)
                .requiredWidth(width = 302.dp)
                .requiredHeight(height = 50.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.phuserlight),
                contentDescription = "ph:user-light",
                colorFilter = ColorFilter.tint(Color(0xff2d0202).copy(alpha = 0.75f)),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 6.dp,
                        y = 10.dp)
                    .requiredWidth(width = 26.dp)
                    .requiredHeight(height = 30.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .requiredWidth(width = 302.dp)
                    .requiredHeight(height = 50.dp)
                    .clip(shape = RoundedCornerShape(15.dp)))
            Text(
                text = "username",
                color = Color(0xff2d0202).copy(alpha = 0.75f),
                style = TextStyle(
                    fontSize = 15.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 37.dp,
                        y = 16.dp)
                    .requiredWidth(width = 152.dp))
            TextField(
                value = username,
                textStyle = TextStyle(textDirection = TextDirection.Content),
                maxLines = 3,
                onValueChange = { Username = username},

                modifier = modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 50.dp)
                    .background(color = Color.Black)
                    .align(alignment = Alignment.BottomCenter)

            )
        }

        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 29.dp,
                    y = 382.dp)
                .requiredWidth(width = 302.dp)
                .requiredHeight(height = 50.dp)
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 273.dp,
                        y = 14.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 16.dp)
                        .requiredHeight(height = 20.dp)
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
                colorFilter = ColorFilter.tint(Color(0xff2d0202).copy(alpha = 0.69f)),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 7.22314453125.dp,
                        y = 10.dp)
                    .requiredWidth(width = 24.dp)
                    .requiredHeight(height = 30.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .requiredWidth(width = 302.dp)
                    .requiredHeight(height = 50.dp)
                    .clip(shape = RoundedCornerShape(15.dp)))
            Text(
                text = "Password",
                color = Color(0xff2d0202).copy(alpha = 0.69f),
                style = TextStyle(
                    fontSize = 15.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 37.dp,
                        y = 15.dp)
                    .requiredWidth(width = 85.dp))
            TextField(
                value = password,
                textStyle = TextStyle(textDirection = TextDirection.Content),
                maxLines = 3,
                onValueChange = {},

                modifier = modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 50.dp)
                    .background(color = Color.Black)
                    .align(alignment = Alignment.BottomCenter)

            )
        }

    }
}

@Preview(widthDp = 360, heightDp = 838)
@Composable
private fun SignUpPreview() {
    SignUp(Modifier)
}