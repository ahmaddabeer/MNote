package com.example.mnote.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note")
data class NoteEntity(

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,

    val title:String,
    val description:String,
    val createdAt:Long,
    val updatedAt:Long



    )