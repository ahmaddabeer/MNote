package com.example.mnote.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mnote.presentation.screen.addnotes.AddNote
import com.example.mnote.presentation.screen.detailnotes.DetailNotes
import com.example.mnote.presentation.screen.editnotes.EditNote
import com.example.mnote.presentation.screen.home.HomeScreen

@Composable
fun NavController() {

    val controller = rememberNavController()


    NavHost(
        navController = controller,
        startDestination = Route.HomeScreen
    ) {


        composable<Route.HomeScreen>{

            HomeScreen(controller)
        }

        composable<Route.AddNote>{

            AddNote(controller)
        }

        composable<Route.EditNote>{backstack->

            val route = backstack.toRoute<Route.EditNote>()

            EditNote(controller,
                route.noteId)


        }



        composable<Route.DetailNotes>{backstack->

            val route = backstack.toRoute<Route.DetailNotes>()

            DetailNotes(
                controller,
                noteId= route.noteId
            )



        }



    }
}


