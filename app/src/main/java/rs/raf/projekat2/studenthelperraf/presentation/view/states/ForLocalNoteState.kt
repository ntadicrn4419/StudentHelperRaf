package rs.raf.projekat2.studenthelperraf.presentation.view.states

import rs.raf.projekat2.studenthelperraf.data.models.Note

sealed class ForLocalNoteState {
    data class Success(val notes: List<Note>): ForLocalNoteState()
    class AddedNote(val message: String) : ForLocalNoteState()// successfully added note
    class DeletedNote(val message: String) : ForLocalNoteState()// successfully deleted note
    class UpdatedNote(val message: String) : ForLocalNoteState()// successfully updated note
    class GetNote(val message: String) : ForLocalNoteState()// successfully got note
    data class Error(val message: String): ForLocalNoteState()
}