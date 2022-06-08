package rs.raf.projekat2.studenthelperraf.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.studenthelperraf.data.models.Note
import rs.raf.projekat2.studenthelperraf.presentation.view.states.ForLocalNoteState
import rs.raf.projekat2.studenthelperraf.presentation.view.states.ForLocalTermState
import rs.raf.projekat2.studenthelperraf.presentation.view.states.ForRemoteTermState

interface MainContract {
    interface ViewModel {

        val remoteTermState: LiveData<ForRemoteTermState>
        val localTermState: LiveData<ForLocalTermState>
        val localNoteState: LiveData<ForLocalNoteState>
        val noteCountState: LiveData<ForLocalNoteState>

        fun fetchAllTerms()
        fun getAllTerms()

        fun getTermsByDayAndTeacherSubject(day: String, teacherOrSubject: String)
        fun getTermsByGroupAndTeacherSubject(group: String, teacherOrSubject: String)
        fun getTermsByGroupAndDay(group: String, day: String)

        fun getTermsByTeacherSubject(teacherOrSubject: String)
        fun getTermsByGroup(group: String)
        fun getTermsByDay(day: String)
        fun getTermsByAllFilters(group: String, day: String, teacherOrSubject: String)

        fun getAllNotes()
        fun getNotesByTitleOrContent(titleOrContent: String, getArchived: Boolean)

        fun getNotesByArchived(archived: Boolean)
        fun addNote(note: Note)
        fun deleteNote(id: Int)
        fun updateNote(id: Int, title: String, content: String, archived: Boolean)

        fun getNumberOfNotesOneDayAgo()
        fun getNumberOfNotesTwoDaysAgo()
        fun getNumberOfNotesThreeDaysAgo()
        fun getNumberOfNotesFourDaysAgo()
        fun getNumberOfNotesFiveDaysAgo()
    }
}