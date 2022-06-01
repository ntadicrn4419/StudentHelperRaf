package rs.raf.projekat2.studenthelperraf.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.studenthelperraf.data.models.NoteEntity

@Dao
abstract class NoteDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: NoteEntity): Completable

    @Query("SELECT * FROM notes WHERE id = :id")
    abstract fun getById(id: Int): Observable<NoteEntity>

    @Query("SELECT * FROM notes")
    abstract fun getAll(): Observable<List<NoteEntity>>

    @Query("DELETE FROM notes WHERE id = :id")
    abstract fun delete(id: Int): Completable

    @Query("DELETE FROM notes")
    abstract fun deleteAll():Completable

    @Transaction
    @Query("UPDATE notes SET title = :title, content = :content, archived = :archived WHERE id = :id")
    abstract fun update(id:Int, title: String, content: String, archived: Boolean): Completable
}