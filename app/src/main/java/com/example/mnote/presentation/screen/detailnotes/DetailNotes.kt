
package com.example.mnote.presentation.screen.detailnotes

import android.app.LocaleConfig
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLocale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.mnote.data.local.NoteEntity
import com.example.mnote.navigation.Route
import com.example.mnote.presentation.NoteViewModel
import java.text.SimpleDateFormat
import java.util.Date

private val PrimaryGreen = Color(0xFF2E7D32)
private val LightGreen = Color(0xFFE8F5E9)
private val DeleteRed = Color(0xFFD32F2F)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailNotes(controller: NavHostController,
                noteId:Int) {


 val viewModel: NoteViewModel = hiltViewModel()

 val note1 = viewModel.getAllNote()
  .collectAsStateWithLifecycle(initialValue = emptyList())

 val note = note1.value.find { it.id == noteId }

 val context = LocalContext.current

 val date = SimpleDateFormat(

  "dd MMM yyyy",
  LocalLocale.current.platformLocale
 ).format(Date(note?.createdAt ?: 0))





 Scaffold(
  modifier = Modifier.fillMaxSize(),
  containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,

  topBar = {
   TopAppBar(
    title = {
     Text(
      text = "Note Details",
      fontWeight = FontWeight.Bold,
      style = MaterialTheme.typography.titleLarge
     )
    },

    navigationIcon = {
     IconButton(
      onClick = {
       controller.popBackStack()
      }
     ) {
      Icon(
       imageVector = Icons.Default.ArrowBack,
       contentDescription = "Back to Home"
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
    .verticalScroll(rememberScrollState()),

   verticalArrangement = Arrangement.spacedBy(18.dp)
  ) {

   Spacer(modifier = Modifier.height(4.dp))

   // Note Title
   Text(
    text = "${note?.title}",
    style = MaterialTheme.typography.headlineSmall,
    fontWeight = FontWeight.Bold,
    color = MaterialTheme.colorScheme.onSurface
   )

   // Created Date
   Row(
    modifier = Modifier
     .background(
      color = LightGreen,
      shape = RoundedCornerShape(10.dp)
     )
     .padding(horizontal = 12.dp, vertical = 8.dp),

    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp)
   ) {
    Icon(
     imageVector = Icons.Default.CalendarMonth,
     contentDescription = "Created date",
     tint = PrimaryGreen,
     modifier = Modifier.size(18.dp)
    )

    Text(
     text = "$date",
     style = MaterialTheme.typography.bodySmall,
     color = PrimaryGreen,
     fontWeight = FontWeight.Medium
    )
   }

   HorizontalDivider(
    color = MaterialTheme.colorScheme.outlineVariant
   )

   // Description
   Column(
    verticalArrangement = Arrangement.spacedBy(10.dp)
   ) {

    Text(
     text = "Description",
     style = MaterialTheme.typography.titleMedium,
     fontWeight = FontWeight.SemiBold
    )

    Text(
     text = "${note?.description}",
     style = MaterialTheme.typography.bodyLarge,
     color = MaterialTheme.colorScheme.onSurfaceVariant,
     lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.4
    )
   }

   Spacer(modifier = Modifier.height(20.dp))

   // Action Buttons
   Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(12.dp)
   ) {

    // Edit Button
    Button(
     onClick = {
      // Edit functionality


      controller.navigate(Route.EditNote(noteId)){

       popUpTo<Route.EditNote>{

        inclusive = true
       }
       launchSingleTop = true
      }





     },

     modifier = Modifier
      .weight(1f)
      .height(58.dp),

     colors = ButtonDefaults.buttonColors(
      containerColor = PrimaryGreen,
      contentColor = Color.White
     ),

     shape = RoundedCornerShape(12.dp)
    ) {
     Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(8.dp)
     ) {
      Icon(
       imageVector = Icons.Default.Edit,
       contentDescription = "Edit"
      )

      Text(
       text = "Edit",
       fontWeight = FontWeight.SemiBold
      )
     }
    }

    // Delete Button
    Button(
     onClick = {
      // Delete functionality


      Toast.makeText(context,"Deleting..",Toast.LENGTH_SHORT).show()

      note?.let {

       viewModel.deleteNote(note)

      }

      controller.navigate(Route.HomeScreen){

       popUpTo<Route.HomeScreen>{

        inclusive= true
       }
       launchSingleTop = true
      }
     },

     modifier = Modifier
      .weight(1f)
      .height(58.dp),

     colors = ButtonDefaults.buttonColors(
      containerColor = Color(0xFFFFEBEE),
      contentColor = DeleteRed
     ),

     shape = RoundedCornerShape(12.dp)
    ) {
     Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(8.dp)
     ) {
      Icon(
       imageVector = Icons.Default.Delete,
       contentDescription = "Delete"
      )

      Text(
       text = "Delete",
       fontWeight = FontWeight.SemiBold
      )
     }
    }
   }

   Spacer(modifier = Modifier.height(16.dp))
  }
 }
}
