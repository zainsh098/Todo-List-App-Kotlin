package com.example.todolistapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.Repository.NotesRespository
import com.example.todolistapp.RoomDatabase.Notes
import com.example.todolistapp.RoomDatabase.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val incompleteNotes: LiveData<List<Notes>>
    val completedNotes: LiveData<List<Notes>>

    private val repository: NotesRespository

    init {
        val dao = NoteDatabase.getDatabase(application).getNotes()
        repository = NotesRespository(dao)
        incompleteNotes = repository.allNotes
        completedNotes = repository.completedNotes
    }

    fun addNote(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun deleteNote(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun updateNoteStatus(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateNote(note)
    }
}
