package com.example.assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment1.ui.theme.Assignment1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment1Theme {
                    StudentInfoScreen()
                }
            }
        }
    }

// Student data class needed to display saved info
data class StudentEntry (
    val name: String,
    val cwid: String
)

@Composable
fun StudentInfoScreen(modifier: Modifier = Modifier) {
    // State Variables
    var inputName by remember { mutableStateOf("") }
    var inputCWID by remember { mutableStateOf("") }
    var showList by remember { mutableStateOf(false)} // List starts as hidden
    // Start structure as empty to avoid a blank first entry
    val savedEntries = remember { mutableStateListOf<StudentEntry>() }

    // Column container to hold all text fields
    Column(
        // Use modifier passed from MainActivity then add on additional properties
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text(
            text = "CPSC 411A",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Programming Assignment 1",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        Text(text = "Vincent Polanco 828045823 ")
        Text(text = "Erl-John Tauto-An 890165608")
        Text(text = "Loc Luong 885478859")

        Spacer(Modifier.height(16.dp))

        // TextField for Name
        OutlinedTextField(
            value = inputName,
            onValueChange = { newText -> inputName = newText},
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        // TextField for CWID
        OutlinedTextField(
            value = inputCWID,
            onValueChange = { newText -> inputCWID = newText},
            label = { Text("CWID") },
            modifier = Modifier.fillMaxWidth(),
            // Display numpad for CWID
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(Modifier.height(32.dp))

        // Save Button
        Button(
            onClick = {
                // On first entry, add it to list state then make the list visible
                val newEntry = StudentEntry(inputName, inputCWID)
                savedEntries.add(newEntry)
                showList = true

                // Clear inputs for next entry
                inputName = ""
                inputCWID = ""
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Save Info")
        }

        Spacer(Modifier.height(32.dp))

        if (showList) {
            LazyColumn (
                modifier = Modifier.fillMaxHeight(),
                contentPadding = PaddingValues(top = 8.dp)
            ) {
                // Iterate through each saved entry and extract the name and CWID for display
                items(savedEntries) { entry ->
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(text = "Name: ${entry.name}")
                        Text(text = "CWID: ${entry.cwid}")
                    }
                    HorizontalDivider()
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment1Theme {
        StudentInfoScreen()
    }
}