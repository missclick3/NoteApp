package com.example.noteapp.feature_note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent{
    data class EnterTitle(val value: String) : AddEditNoteEvent()
    data class EnterContent(val value: String) : AddEditNoteEvent()
    data class ChangeFocusTitle(val focusState: FocusState) : AddEditNoteEvent()
    data class ChangeFocusContent(val focusState: FocusState) : AddEditNoteEvent()
    data class ChangeColor(val color: Int) : AddEditNoteEvent()
    object SaveNote: AddEditNoteEvent()
}
