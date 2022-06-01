package rs.raf.projekat2.studenthelperraf.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.studenthelperraf.data.models.MyFilter
import rs.raf.projekat2.studenthelperraf.data.models.Note
import rs.raf.projekat2.studenthelperraf.data.models.Term
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
        fun addTerm(term: Term)
        fun getTermsBySubject(subject: String)
        fun getTermsByGroup(group: String)
        fun getTermsByDay(day: String)
        fun getTermsByTeacher(teacher: String)
        fun getTermsByAllFilters(filter: MyFilter)

        fun getAllNotes()
        fun getNoteById(id: Int)
        fun addNote(note: Note)
        fun deleteNote(id: Int)
        fun updateNote(note: Note)

    }
}