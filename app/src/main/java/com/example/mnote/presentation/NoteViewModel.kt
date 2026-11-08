package com.example.mnote.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mnote.data.local.NoteEntity
import com.example.mnote.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel

class NoteViewModel @Inject constructor(private var repository: NoteRepository) : ViewModel() {


    var title by mutableStateOf("")
        private set


    var description by mutableStateOf("")
        private set


    var time by mutableStateOf("")
        private set



    var createdAt by mutableStateOf("")
        private set

    var updatedAt by mutableStateOf("")
        private set




    fun onTitleChange(title1: String) {

        title = title1
    }

    fun onDescriptionChange(des: String) {

        description = des
    }

    fun onTimeChange(time1: String) {

        time = time1

    }

    fun onCreatedAt(create: String) {

        createdAt = create
    }

    fun onUpdateAt(update: String) {

        updatedAt = update
    }


    private val notes: StateFlow<List<NoteEntity>> =
        repository.getAllNote().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun getAllNote(): StateFlow<List<NoteEntity>> = notes


    fun AddNote(note: NoteEntity) {

        viewModelScope.launch {

            repository.insert(note)
        }
    }

    fun updateNote(note: NoteEntity) {

        viewModelScope.launch {

            repository.update(note)
        }
    }


    fun deleteNote(note: NoteEntity) {

        viewModelScope.launch {

            repository.delete(note)
        }
    }


    fun onSaveNote(){

        if(title.isNotBlank() && description.isNotBlank()){


            val note = NoteEntity(


                title = title,
                description = description,
                createdAt = createdAt.toLong(),
                updatedAt = updatedAt.toLong()

            )
            AddNote(note = note)

        }


    }


}