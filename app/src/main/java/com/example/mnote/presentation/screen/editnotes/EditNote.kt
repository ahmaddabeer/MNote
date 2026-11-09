package com.example.mnote.presentation.screen.editnotes

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLocale
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.mnote.navigation.Route
import com.example.mnote.presentation.NoteViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNote(controller: NavHostController,
             noteId:Int) {


    val viewModel: NoteViewModel = hiltViewModel()

    val context = LocalContext.current

    val note1 = viewModel.getAllNote()
        .collectAsStateWithLifecycle(initialValue =  emptyList())


    val note = note1.value.find { it.id == noteId }

    val updatedCurrentTime = System.currentTimeMillis()
    val updatedCurrentDate = System.currentTimeMillis()

    LaunchedEffect(note?.id) {
        note?.let {
            viewModel.onTitleChange(it.title)
            viewModel.onDescriptionChange(it.description)
        }
    }


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),

        topBar = {
            TopAppBar(
                title = { Text(text = "Edit Note") },

                navigationIcon = {

                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back to Home")


                },


                )


        }) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        )
        {


            Text(text = "Note Title", style = MaterialTheme.typography.bodyLarge)




                OutlinedTextField(
                    value = viewModel.title,
                    onValueChange = {viewModel.onTitleChange(it)},
                    label = { Text(text = "Enter your note title...") },
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true

                )








                OutlinedTextField(
                    value = viewModel.description,
                    onValueChange = { viewModel.onDescriptionChange(it) },
                    label = { Text(text = "Enter your note title...") },
                    modifier = Modifier.height(100.dp),
                    shape = RoundedCornerShape(12.dp),
                    maxLines = 5

                )




            Text(
                "${viewModel.description.length} ",
            )

            Button(
                onClick = {

                    viewModel.onUpdateAt(updatedCurrentDate.toString())

                  note?.let{

                      viewModel.onUpdateNote(it)
                  }
                    Toast.makeText(context,"Updating", Toast.LENGTH_SHORT).show()

                    controller.navigate(Route.HomeScreen){

                        popUpTo<Route.HomeScreen>{
                            inclusive = true
                        }
                        launchSingleTop = true
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {

                Text(text = "Save Note")


            }

        }
    }


}