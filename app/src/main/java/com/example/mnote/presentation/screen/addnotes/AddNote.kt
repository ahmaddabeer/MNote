package com.example.mnote.presentation.screen.addnotes

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mnote.navigation.Route
import com.example.mnote.presentation.NoteViewModel

private val PrimaryGreen = Color(0xFF2E7D32)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNote(controller: NavController) {

    val context = LocalContext.current

    val viewModel: NoteViewModel = hiltViewModel()

    val currentTime = System.currentTimeMillis()
    val currentDate = System.currentTimeMillis()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,

        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Create Note",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Write down your thoughts",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },

                navigationIcon = {
                    IconButton(
                        onClick = {
                            controller.navigate(Route.HomeScreen) {
                                popUpTo<Route.HomeScreen> {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
                )
            )
        }

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(top = 12.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Note Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Description,
                    contentDescription = null,
                    tint = PrimaryGreen,
                    modifier = Modifier.size(24.dp)
                )

                Text(
                    text = "New Note",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Title Field
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Note Title",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Medium
                )

                OutlinedTextField(
                    value = viewModel.title,
                    onValueChange = {
                        viewModel.onTitleChange(it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text("Enter your note title...")
                    },
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryGreen,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface
                    )
                )
            }

            // Description Field
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Medium
                )

                OutlinedTextField(
                    value = viewModel.description,
                    onValueChange = {
                        viewModel.onDescriptionChange(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    placeholder = {
                        Text("Write your thoughts here...")
                    },
                    shape = RoundedCornerShape(14.dp),
                    maxLines = 7,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryGreen,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface
                    )
                )

                Text(
                    text = "${viewModel.description.length} characters",
                    modifier = Modifier.align(Alignment.End),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Save Button
            Button(
                onClick = {

                    if (viewModel.title.isBlank()) {
                        Toast.makeText(
                            context,
                            "Please enter note title",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {

                        viewModel.onTimeChange(currentTime.toString())
                        viewModel.onCreatedAt(currentDate.toString())
                        viewModel.onUpdateAt(currentDate.toString())

                        viewModel.onSaveNote()

                        Toast.makeText(
                            context,
                            "Saved",
                            Toast.LENGTH_SHORT
                        ).show()

                        controller.navigate(Route.HomeScreen) {
                            popUpTo<Route.HomeScreen> {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                },
                enabled = viewModel.title.isNotBlank() && viewModel.description.isNotBlank(),

                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),

                shape = RoundedCornerShape(14.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryGreen,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Save Note",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
