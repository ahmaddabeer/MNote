package com.example.mnote.data.repository

import com.example.mnote.data.local.NoteDao
import com.example.mnote.data.local.NoteEntity
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow


class NoteRepository @Inject constructor(private val dao: NoteDao) {


    fun getAllNote(): Flow<List<NoteEntity>> {


        return dao.getAllNotes()

    }

    suspend fun insert(note: NoteEntity) = dao.insertNote(note)

    suspend fun update(note: NoteEntity) = dao.updateNote(note)

    suspend fun delete(note: NoteEntity) = dao.deleteNote(note)



}