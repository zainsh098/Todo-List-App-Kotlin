package com.example.todolistapp.RoomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes2_table")
data class Notes(
    val titleTask: String,
    val priorityTask: String,
    var statusTask: Boolean
)
{

    @PrimaryKey(autoGenerate = true) var id=0

}
