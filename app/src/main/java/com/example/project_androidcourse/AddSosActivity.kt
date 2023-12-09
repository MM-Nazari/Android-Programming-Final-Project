package com.example.project_androidcourse

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_androidcourse.ui.theme.Project_AndroidCourseTheme
import kotlinx.coroutines.launch

class AddSosActivity : ComponentActivity() {
    private val viewModel by viewModels<AddSosViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        ContextHandler.set(applicationContext)
        setContent {
            Project_AndroidCourseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AddSOSPage(updateFieldValue = viewModel::updateFieldValue, addSOS = viewModel::addSOS)
                }
            }
        }
    }
}


class AddSosViewModel : ViewModel() {

    // these fields and values may be needed in the future
    private val _highPriorityTextFieldValue = mutableStateOf("")
    val highPriorityTextFieldValue: State<String> = _highPriorityTextFieldValue

    private val _mediumPriorityTextFieldValue = mutableStateOf("")
    val mediumPriorityTextFieldValue: State<String> = _mediumPriorityTextFieldValue

    private val _lowPriorityTextFieldValue = mutableStateOf("")
    val lowPriorityTextFieldValue: State<String> = _lowPriorityTextFieldValue

    fun updateFieldValue(priority: Short, newValue: String) {
        when (priority) {
            0.toShort() -> {
                _lowPriorityTextFieldValue.value = newValue
            }
            1.toShort() -> {
                _mediumPriorityTextFieldValue.value = newValue
            }
            else -> {
                _highPriorityTextFieldValue.value = newValue
            }
        }
    }

    // this should be called on button onClick event:
    fun addSOS(priority: Short, text: String) {
        val context = ContextHandler.get()

        try {
            if (context != null) {
                val databaseDao = SOSDatabase.getDatabase(context).getDatabaseDao()
                val finalText = when(text.isEmpty()) {
                    true -> "متن پیش فرض"
                    false -> text
                }
                val newSOS = SOSDataEntity(priority = priority, text = finalText)
                viewModelScope.launch {
                    databaseDao.insertSOS(newSOS)
                    val priorityFA = when(priority) {
                        0.toShort() -> "پایین"
                        1.toShort() -> "متوسط"
                        else -> "بالا"

                    }
                    Toast.makeText(context,
                        "درخواست $finalText با اولویت $priorityFA ثبت شد!", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (ex: Exception) {
            Log.e("AddSos Crash", ex.toString())
            Toast.makeText(context, "درخواست کمک با مشل مواجه شد!", Toast.LENGTH_SHORT).show()

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSOSPage(modifier: Modifier = Modifier, updateFieldValue: (Short, String) -> Unit, addSOS: (Short, String) -> Unit) {
    var highPriorityTextFieldValue by remember { mutableStateOf("") }
    var mediumPriorityTextFieldValue by remember { mutableStateOf("") }
    var lowPriorityTextFieldValue by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Box(modifier= modifier
            .fillMaxWidth()
            .requiredHeight(height = 200.dp)
            .padding(all = 10.dp)
            .offset(y=50.dp)
            .border(shape = RectangleShape, border = BorderStroke(1.dp, Color.Red))

        ) {
            Box(modifier= modifier
                .fillMaxSize()
                .padding(all = 20.dp)

            ) {
                Text(
                    text = "اولویت بالا",
                    style = TextStyle(textAlign = TextAlign.End, fontSize = 25.sp),
                    color = Color.White,
                    modifier = modifier
                        .requiredWidth(width = 200.dp)
                        .requiredHeight(40.dp)
                        .align(alignment = Alignment.TopEnd)
                )
                TextField(
                    value = highPriorityTextFieldValue,
                    textStyle = TextStyle(textDirection = TextDirection.Content),
                    maxLines = 3,
                    onValueChange = {
                        highPriorityTextFieldValue = it
                        updateFieldValue(2.toShort(), it)
                    },

                    modifier = modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 50.dp)
                        .background(color = Color.Black)
                        .align(alignment = Alignment.BottomCenter)

                )
                Button(
                    onClick = { addSOS(2.toShort(), highPriorityTextFieldValue) },
                    modifier = modifier
                        .requiredHeight(height = 32.dp)
                        .requiredWidth(width = 120.dp)
                        .align(alignment = Alignment.TopStart)
                ) {
                    Text(text = "ثبت", style = TextStyle(textAlign = TextAlign.Justify))
                }
            }
        }
        Box(modifier= modifier
            .fillMaxWidth()
            .offset(y = 250.dp)
            .requiredHeight(height = 200.dp)
            .padding(all = 10.dp)
            .border(shape = RectangleShape, border = BorderStroke(1.dp, Color.Red))

        ) {
            Box(modifier= modifier
                .fillMaxSize()
                .padding(all = 20.dp)

            ) {
                Text(
                    text = "اولویت متوسط",
                    style = TextStyle(textAlign = TextAlign.End, fontSize = 25.sp),
                    color = Color.White,
                    modifier = modifier
                        .requiredWidth(width = 200.dp)
                        .requiredHeight(40.dp)
                        .align(alignment = Alignment.TopEnd)
                )
                TextField(
                    value = mediumPriorityTextFieldValue,
                    textStyle = TextStyle(textDirection = TextDirection.Content),
                    maxLines = 3,
                    onValueChange = {
                        mediumPriorityTextFieldValue = it
                        updateFieldValue(1.toShort(), it)
                    },

                    modifier = modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 50.dp)
                        .background(color = Color.Black)
                        .align(alignment = Alignment.BottomCenter)

                )
                Button(
                    onClick = { addSOS(1.toShort(), mediumPriorityTextFieldValue) },
                    modifier = modifier
                        .requiredHeight(height = 32.dp)
                        .requiredWidth(width = 120.dp)
                        .align(alignment = Alignment.TopStart)
                ) {
                    Text(text = "ثبت", style = TextStyle(textAlign = TextAlign.Justify))
                }
            }
        }
        Box(modifier= modifier
            .fillMaxWidth()
            .offset(y = 450.dp)
            .requiredHeight(height = 200.dp)
            .padding(all = 10.dp)
            .border(shape = RectangleShape, border = BorderStroke(1.dp, Color.Red))

        ) {
            Box(modifier= modifier
                .fillMaxSize()
                .padding(all = 20.dp)

            ) {
                Text(
                    text = "اولویت پایین",
                    style = TextStyle(textAlign = TextAlign.End, fontSize = 25.sp),
                    color = Color.White,
                    modifier = modifier
                        .requiredWidth(width = 200.dp)
                        .requiredHeight(40.dp)
                        .align(alignment = Alignment.TopEnd)
                )
                TextField(
                    value = lowPriorityTextFieldValue,
                    textStyle = TextStyle(textDirection = TextDirection.Content),
                    maxLines = 3,
                    onValueChange = {
                        lowPriorityTextFieldValue = it
                        updateFieldValue(0.toShort(), it)
                    },

                    modifier = modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 50.dp)
                        .background(color = Color.Black)
                        .align(alignment = Alignment.BottomCenter)

                )

                Button(
                    onClick = { addSOS(0.toShort(), lowPriorityTextFieldValue) },
                    modifier = modifier
                        .requiredHeight(height = 32.dp)
                        .requiredWidth(width = 120.dp)
                        .align(alignment = Alignment.TopStart)
                ) {
                    Text(text = "ثبت", style = TextStyle(textAlign = TextAlign.Justify))
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun AddSOSPagePreview() {
    Project_AndroidCourseTheme {
        //AddSOSPage()
    }
}

/*
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_androidcourse.ui.theme.Project_AndroidCourseTheme
import kotlinx.coroutines.launch

class AddSosActivity : ComponentActivity() {
    private val viewModel by viewModels<AddSosViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        ContextHandler.set(applicationContext)
        setContent {
            Project_AndroidCourseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AddSOSPage(updateFieldValue = viewModel::updateFieldValue, addSOS = viewModel::addSOS)
                }
            }
        }
    }
}

class AddSosViewModel : ViewModel() {

    // these fields and values may be needed in the future
    private val _highPriorityTextFieldValue = mutableStateOf("")
    val highPriorityTextFieldValue: State<String> = _highPriorityTextFieldValue

    private val _mediumPriorityTextFieldValue = mutableStateOf("")
    val mediumPriorityTextFieldValue: State<String> = _mediumPriorityTextFieldValue

    private val _lowPriorityTextFieldValue = mutableStateOf("")
    val lowPriorityTextFieldValue: State<String> = _lowPriorityTextFieldValue

    fun updateFieldValue(priority: Short, newValue: String) {
        when (priority) {
            0.toShort() -> {
                _lowPriorityTextFieldValue.value = newValue
            }
            1.toShort() -> {
                _mediumPriorityTextFieldValue.value = newValue
            }
            else -> {
                _highPriorityTextFieldValue.value = newValue
            }
        }
    }

    // this should be called on button onClick event:
    fun addSOS(priority: Short, text: String) {
        val context = ContextHandler.get()

        try {
            if (context != null) {
                val databaseDao = SOSDatabase.getDatabase(context).getDatabaseDao()
                val finalText = when(text.isEmpty()) {
                    true -> "متن پیش فرض"
                    false -> text
                }
                val newSOS = SOSDataEntity(priority = priority, text = finalText)
                viewModelScope.launch {
                    databaseDao.insertSOS(newSOS)
                    val priorityFA = when(priority) {
                        0.toShort() -> "پایین"
                        1.toShort() -> "متوسط"
                        else -> "بالا"

                    }
                    Toast.makeText(context,
                        "درخواست $finalText با اولویت $priorityFA ثبت شد!", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (ex: Exception) {
            Log.e("AddSos Crash", ex.toString())
            Toast.makeText(context, "درخواست کمک با مشکل مواجه شد!", Toast.LENGTH_SHORT).show()

        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSOSPage(modifier: Modifier = Modifier, updateFieldValue: (Short, String) -> Unit, addSOS: (Short, String) -> Unit){
    var highPriorityTextFieldValue by remember { mutableStateOf("") }
    var mediumPriorityTextFieldValue by remember { mutableStateOf("") }
    var lowPriorityTextFieldValue by remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .requiredHeight(height = 834.dp)
            .background(brush = Brush.linearGradient(
                0f to Color(0xffc72c3f),
                1f to Color.Black,
                start = Offset(180f, 0f),
                end = Offset(180f, 834f)))
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 33.dp,
                    y = 39.dp)
                .requiredWidth(width = 300.dp)
                .requiredHeight(height = 207.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 300.dp)
                    .requiredHeight(height = 207.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 300.dp)
                        .requiredHeight(height = 207.dp)
                ) {
                    Text(
                        text = "اولویت بالا\n",
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 20.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 108.dp,
                                y = 0.dp))
                    Image(
                        painter = painterResource(id = R.drawable.icons8_edit_36___),
                        contentDescription = "icons8-edit-36(-hdpi) 1",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 128.dp,
                                y = 100.dp)
                            .requiredWidth(width = 38.dp)
                            .requiredHeight(height = 40.dp))
                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = 57.dp)
                            .requiredWidth(width = 300.dp)
                            .requiredHeight(height = 150.dp))
                    TextButton(
                        onClick = {addSOS(2.toShort(), highPriorityTextFieldValue)},
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 116.dp,
                                y = 100.dp)
                            .requiredWidth(width = 63.dp)
                            .requiredHeight(height = 51.dp)){}
                }
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 22.dp,
                    y = 30.dp)
                .requiredWidth(width = 322.dp)
                .requiredHeight(height = 179.dp)
                .clip(shape = RoundedCornerShape(15.dp)))
              //  .background(color = Color(0xffd9d980)))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 33.dp,
                    y = 262.dp)
                .requiredWidth(width = 300.dp)
                .requiredHeight(height = 207.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 300.dp)
                    .requiredHeight(height = 207.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 300.dp)
                        .requiredHeight(height = 207.dp)
                ) {
                    Text(
                        text = "اولویت متوسط\n",
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 20.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 89.dp,
                                y = 0.dp))
                    Image(
                        painter = painterResource(id = R.drawable.icons8_edit_36___),
                        contentDescription = "icons8-edit-36(-hdpi) 1",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 128.dp,
                                y = 100.dp)
                            .requiredWidth(width = 38.dp)
                            .requiredHeight(height = 40.dp))
                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = 57.dp)
                            .requiredWidth(width = 300.dp)
                            .requiredHeight(height = 150.dp))
                    TextButton(
                        onClick = {addSOS(1.toShort(), mediumPriorityTextFieldValue)},
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 116.dp,
                                y = 97.dp)
                            .requiredWidth(width = 63.dp)
                            .requiredHeight(height = 48.dp)){}
                }
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 22.dp,
                    y = 246.dp)
                .requiredWidth(width = 322.dp)
                .requiredHeight(height = 179.dp)
                .clip(shape = RoundedCornerShape(15.dp))
                .background(color = Color(0xffd9d9d9)))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 22.dp,
                    y = 467.dp)
                .requiredWidth(width = 322.dp)
                .requiredHeight(height = 179.dp)
                .clip(shape = RoundedCornerShape(15.dp))
                .background(color = Color(0xffd9d9d9)))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 33.dp,
                    y = 485.dp)
                .requiredWidth(width = 300.dp)
                .requiredHeight(height = 207.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 300.dp)
                    .requiredHeight(height = 207.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 300.dp)
                        .requiredHeight(height = 207.dp)
                ) {
                    Text(
                        text = "اولویت پایین\n",
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 20.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 99.dp,
                                y = 0.dp))
                    Image(
                        painter = painterResource(id = R.drawable.icons8_edit_36___),
                        contentDescription = "icons8-edit-36(-hdpi) 1",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 128.dp,
                                y = 100.dp)
                            .requiredWidth(width = 38.dp)
                            .requiredHeight(height = 40.dp))
                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 0.dp,
                                y = 57.dp)
                            .requiredWidth(width = 300.dp)
                            .requiredHeight(height = 150.dp))
                    TextButton(
                        onClick = {addSOS(0.toShort(), lowPriorityTextFieldValue)},
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 119.dp,
                                y = 94.dp)
                            .requiredWidth(width = 58.dp)
                            .requiredHeight(height = 53.dp)){}
                }
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 834)
@Composable
private fun AddSOSPagePreview() {
    Project_AndroidCourseTheme {
        //MainPage(Modifier)
    }

}

*/