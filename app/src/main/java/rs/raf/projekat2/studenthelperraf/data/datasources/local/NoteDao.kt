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

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :titleOrContent || '%' OR content LIKE '%' || :titleOrContent || '%'")
    abstract fun getAllByTitleOrContent(titleOrContent: String): Observable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE archived = :archived")
    abstract fun getAllByArchived(archived: Boolean): Observable<List<NoteEntity>>

    @Query("SELECT * FROM notes")
    abstract fun getAll(): Observable<List<NoteEntity>>

    @Query("DELETE FROM notes WHERE id = :id")
    abstract fun delete(id: Int): Completable

    @Query("DELETE FROM notes")
    abstract fun deleteAll():Completable

    @Transaction
    @Query("UPDATE notes SET title = :title, content = :content, archived = :archived WHERE id = :id")
    abstract fun update(id:Int, title: String, content: String, archived: Boolean): Completable

    //date('now','-4 day')
    //SELECT COUNT(*) from notes where noteDate = date('now','-4 day')
    @Transaction
    @Query("SELECT COUNT(*) from notes where dateCreated = date('now','-1 day')")
    abstract fun getNumberOfNotesOneDayAgo(): Observable<Int>

    @Transaction
    @Query("SELECT COUNT(*) from notes where dateCreated = date('now','-2 day')")
    abstract fun getNumberOfNotesTwoDaysAgo(): Observable<Int>

    @Transaction
    @Query("SELECT COUNT(*) from notes where dateCreated = date('now','-3 day')")
    abstract fun getNumberOfNotesThreeDaysAgo(): Observable<Int>

    @Transaction
    @Query("SELECT COUNT(*) from notes where dateCreated = date('now','-4 day')")
    abstract fun getNumberOfNotesFourDaysAgo(): Observable<Int>

    @Transaction
    @Query("SELECT COUNT(*) from notes where dateCreated = date('now','-5 day')")
    abstract fun getNumberOfNotesFiveDaysAgo(): Observable<Int>

}