package com.example.todolistapp.Repository

import androidx.lifecycle.LiveData

import com.example.todolistapp.RoomDatabase.Notes
import com.example.todolistapp.RoomDatabase.NotesDao

class NotesRespository(private val noteDao: NotesDao) {


    val allNotes: LiveData<List<Notes>> = noteDao.getIncompleteNotes()
    val completedNotes: LiveData<List<Notes>> = noteDao.getCompletedNotes()




    suspend fun  insert(notes: Notes){

        noteDao.insert(notes)

    }

    suspend fun  delete(notes: Notes){

        noteDao.delete(notes)

    }


    suspend fun  updateNote(notes: Notes){
        noteDao.update(notes)

    }



}