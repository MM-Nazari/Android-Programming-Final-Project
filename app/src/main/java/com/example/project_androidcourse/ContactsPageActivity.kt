package com.example.project_androidcourse

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_androidcourse.ui.theme.Project_AndroidCourseTheme
import kotlinx.coroutines.launch
import java.time.format.TextStyle

class ContactsPageActivity : ComponentActivity() {
    private val viewModel by viewModels<ReadContactViewModel>()
    object mostImportantContact {
        var chosenContact = ""
    }
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
                    Contacts(updateFieldValue = viewModel::updateFieldValue, readContacts = viewModel::readContacts)
                }
            }
        }
    }
}

class ReadContactViewModel : ViewModel() {

    // these fields and values may be needed in the future
    private val _phoneNumber = mutableStateOf("")
    val phoneNumber: State<String> = _phoneNumber

    private val _contactName = mutableStateOf("")
    val contactName: State<String> = _contactName

    fun updateFieldValue(field: Short, newValue: String) {
        when (field) {
            0.toShort() -> {
                _contactName.value = newValue
            }
            1.toShort() -> {
                _phoneNumber.value = newValue
            }
        }
    }

    fun readContacts(phoneNumber: String, contactName: String){
        SettingsPageActivity.cContactList.chosenContactsList.add(Contacts(contactName, phoneNumber))
        if(SettingsPageActivity.cContactList.chosenContactsList.size == 1){
            ContactsPageActivity.mostImportantContact.chosenContact = phoneNumber
        }
        val context = ContextHandler.get()
        if (context != null) {
            Toast.makeText(context,
                "مخاطب اضافه شد!", Toast.LENGTH_SHORT).show()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Contacts(modifier: Modifier = Modifier, updateFieldValue: (Short, String) -> Unit, readContacts: (String, String) -> Unit) {
    var phoneNumber by remember { mutableStateOf("") }
    var contactName by remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .requiredHeight(height = 838.dp)
            .background(brush = Brush.linearGradient(
                0f to Color(0xFFFFFFFF),
                1f to Color.Black,
                start = Offset(180f, 0f),
                end = Offset(180f, 1838f)))
    ) {
        Text(
            text = "Contacts",
            color = Color(0xff5e0707),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 108.dp,
                    y = 46.dp))
        Text(
            text = "first one: the most important one",
            color = Color(0xff5e0707),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 30.dp,
                    y = 100.dp))
        TextField(
            value = contactName,
            onValueChange = {contactName = it
                updateFieldValue(0.toShort(), it)},
            label = {
                Text(
                    text = "Name",
                    color = Color(0xff340303).copy(alpha = 0.78f),
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 15.sp),
                    modifier = Modifier
                        .requiredHeight(height = 18.dp))
            },
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 27.dp,
                    y = 232.dp)
                .requiredWidth(width = 307.dp)
                .requiredHeight(height = 51.dp))
        TextField(
            value = phoneNumber,
            onValueChange = {phoneNumber = it
                updateFieldValue(1.toShort(), it)},
            label = {
                Text(
                    text = "Number",
                    color = Color(0xff340303).copy(alpha = 0.75f),
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 15.sp),
                    modifier = Modifier
                        .requiredHeight(height = 18.dp))
            },
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 27.dp,
                    y = 323.dp)
                .requiredWidth(width = 307.dp)
                .requiredHeight(height = 48.dp))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 84.dp,
                    y = 419.dp)
                .requiredWidth(width = 193.dp)
                .requiredHeight(height = 50.dp)
        ) {
            Button(
                onClick = {readContacts(phoneNumber, contactName)},
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffb11212).copy(alpha = 0.97f)),
                modifier = Modifier
                    .requiredWidth(width = 193.dp)
                    .requiredHeight(height = 50.dp)){ }
            Text(
                text = "Add",
                color = Color(0xff310202),
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 80.97900390625.dp,
                        y = 16.dp)
                    .requiredWidth(width = 63.dp))
        }
    }
}