package com.example.noteapp.feature_note.data.data_source


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.noteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("Select * from Note")
    fun getNotes(): Flow<List<Note>>

    @Query("Select * from Note where id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Upsert()
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}