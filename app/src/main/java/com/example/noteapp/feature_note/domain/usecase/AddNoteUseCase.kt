package com.example.noteapp.feature_note.domain.usecase

import com.example.noteapp.feature_note.domain.model.InvalidNoteException
import com.example.noteapp.feature_note.domain.model.Note
import com.example.noteapp.feature_note.domain.repository.NoteRepository
import kotlin.jvm.Throws

class AddNoteUseCase (
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend fun execute(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title can't be empty.")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content can't be empty")
        }
        repository.insertNote(note)
    }
}