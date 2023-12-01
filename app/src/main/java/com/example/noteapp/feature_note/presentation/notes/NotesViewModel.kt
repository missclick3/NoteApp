package com.example.noteapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.feature_note.domain.model.Note
import com.example.noteapp.feature_note.domain.usecase.NoteUseCases
import com.example.noteapp.feature_note.domain.util.NoteOrder
import com.example.noteapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor (
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf<NotesState>(NotesState())
    val state: State<NotesState> = _state

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    private var lastDeletedNote: Note? = null
    private var getNotesJob: Job? = null
    fun onEvent(event: NotesEvent) {
        when(event) {
            is NotesEvent.Order -> {
                if(state.value.notesOrder::class == event.noteOrder::class &&
                    state.value.notesOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNoteUseCase.execute(event.note)
                    lastDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNoteUseCase.execute(lastDeletedNote ?: return@launch)
                    lastDeletedNote = null
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotesUseCase.execute(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    notesOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}