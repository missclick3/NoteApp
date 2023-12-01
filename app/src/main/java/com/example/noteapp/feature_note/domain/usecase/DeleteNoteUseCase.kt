package com.example.noteapp.feature_note.domain.usecase

import com.example.noteapp.feature_note.domain.model.Note
import com.example.noteapp.feature_note.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {
    suspend fun execute(
        note: Note
    ) {
        repository.deleteNote(note)
    }
}