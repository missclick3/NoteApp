package com.example.noteapp.feature_note.domain.usecase

import com.example.noteapp.feature_note.domain.model.Note
import com.example.noteapp.feature_note.domain.repository.NoteRepository

class GetSingleNoteUseCase(
    private val repository: NoteRepository
) {
    suspend fun execute(id: Int): Note? {
        return repository.getNoteById(id)
    }
}