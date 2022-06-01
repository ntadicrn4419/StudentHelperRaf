package rs.raf.projekat2.studenthelperraf.presentation.view.states

import rs.raf.projekat2.studenthelperraf.data.models.Note

sealed class ForLocalNoteState {
    data class Success(val notes: List<Note>): ForLocalNoteState()
    data class Error(val message: String): ForLocalNoteState()
}