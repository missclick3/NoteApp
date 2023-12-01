package com.example.noteapp.feature_note.domain.usecase

data class NoteUseCases(
    val getNotesUseCase: GetNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val addNoteUseCase: AddNoteUseCase,
    val getSingleNoteUseCase: GetSingleNoteUseCase
) {
    
}
