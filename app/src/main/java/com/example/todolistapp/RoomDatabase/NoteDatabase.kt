package com.example.todolistapp.RoomDatabase
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
@Database(entities = arrayOf(Notes::class ), version = 3, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract  fun getNotes():NotesDao


    companion object {

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {


            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note2_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}