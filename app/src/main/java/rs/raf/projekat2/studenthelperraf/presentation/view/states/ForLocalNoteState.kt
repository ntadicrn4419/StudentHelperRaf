package rs.raf.projekat2.studenthelperraf.presentation.view.states

import rs.raf.projekat2.studenthelperraf.data.models.Note

sealed class ForLocalNoteState {
    data class SuccessfullyGotAllNotes(val notes: List<Note>): ForLocalNoteState()
    data class SuccessfullyGotNonArchiveNotes(val notes: List<Note>): ForLocalNoteState()
    data class SuccessfullyGotFilteredNotes(val notes: List<Note>): ForLocalNoteState()
    class AddedNote(val message: String) : ForLocalNoteState()// successfully added note
    class DeletedNote(val message: String) : ForLocalNoteState()// successfully deleted note
    class UpdatedNote(val message: String) : ForLocalNoteState()// successfully updated note
    class GetNote(val message: String) : ForLocalNoteState()// successfully got note

    class GetNotesCountOneDayAgo(val cnt: Int) : ForLocalNoteState()
    class GetNotesCountTwoDaysAgo(val cnt: Int) : ForLocalNoteState()
    class GetNotesCountThreeDaysAgo(val cnt: Int) : ForLocalNoteState()
    class GetNotesCountFourDaysAgo(val cnt: Int) : ForLocalNoteState()
    class GetNotesCountFiveDaysAgo(val cnt: Int) : ForLocalNoteState()

    data class Error(val message: String): ForLocalNoteState()
}