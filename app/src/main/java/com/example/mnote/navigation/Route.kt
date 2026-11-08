package com.example.mnote.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {


    @Serializable
    object HomeScreen: Route()



    @Serializable
    data class DetailNotes(
        val noteId: Int
    )






    @Serializable
    data class EditNote(
        val noteId:Int
    )


    @Serializable
    object AddNote: Route()

























}