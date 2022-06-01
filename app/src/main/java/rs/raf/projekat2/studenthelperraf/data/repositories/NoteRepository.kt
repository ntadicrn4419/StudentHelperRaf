package rs.raf.projekat2.studenthelperraf.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.studenthelperraf.data.models.Note

interface NoteRepository {
    fun insert(note: Note): Completable
    fun getById(id: Int): Observable<Note>
    fun getAll(): Observable<List<Note>>
    fun delete(id: Int): Completable
    fun deleteAll(): Completable
    fun update(note: Note): Completable
}