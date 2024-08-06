package com.example.todolistapp.RoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes2_table WHERE statusTask = 0 ORDER BY id DESC")
    fun getIncompleteNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM notes2_table WHERE statusTask = 1 ORDER BY id DESC")
    fun getCompletedNotes(): LiveData<List<Notes>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Notes)

    @Update
    suspend fun update(note: Notes)

    @Delete
    suspend fun delete(note: Notes)
}