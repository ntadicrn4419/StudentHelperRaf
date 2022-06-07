package rs.raf.projekat2.studenthelperraf.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.studenthelperraf.data.models.MyFilter
import rs.raf.projekat2.studenthelperraf.data.models.Note
import rs.raf.projekat2.studenthelperraf.presentation.view.states.ForLocalNoteState
import rs.raf.projekat2.studenthelperraf.presentation.view.states.ForLocalTermState
import rs.raf.projekat2.studenthelperraf.presentation.view.states.ForRemoteTermState

interface MainContract {
    interface ViewModel {

        val remoteTermState: LiveData<ForRemoteTermState>
        val localTermState: LiveData<ForLocalTermState>
        val localNoteState: LiveData<ForLocalNoteState>

        fun fetchAllTerms()
        fun getAllTerms()
        //fun addTerm(term: Term)

        fun getTermsByDayAndTeacherSubject(filter: MyFilter)
        fun getTermsByGroupAndTeacherSubject(filter: MyFilter)
        fun getTermsByGroupAndDay(filter: MyFilter)

        fun getTermsByTeacherSubject(filter: MyFilter)
        fun getTermsByGroup(filter: MyFilter)
        fun getTermsByDay(filter: MyFilter)
        fun getTermsByAllFilters(filter: MyFilter)

        fun getAllNotes()
        //fun getNoteById(id: Int)
        fun getNotesByTitleOrContent(titleOrContent: String)
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