package rs.raf.projekat2.studenthelperraf.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.studenthelperraf.data.models.Note

interface NoteRepository {
    fun insert(note: Note): Completable
    fun getById(id: Int): Observable<Note>
    fun getAllByTitleOrContent(titleOrContent: String): Observable<List<Note>>
    fun getAll(): Observable<List<Note>>
    fun getAllByArchived(archived: Boolean): Observable<List<Note>>
    fun delete(id: Int): Completable
    fun deleteAll(): Completable
    fun update(id: Int, title: String, content: String, archived: Boolean): Completable

    fun getNumberOfNotesOneDayAgo(): Observable<Int>
    fun getNumberOfNotesTwoDaysAgo(): Observable<Int>
    fun getNumberOfNotesThreeDaysAgo(): Observable<Int>
    fun getNumberOfNotesFourDaysAgo(): Observable<Int>
    fun getNumberOfNotesFiveDaysAgo(): Observable<Int>

}