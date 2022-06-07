package rs.raf.projekat2.studenthelperraf.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.studenthelperraf.data.models.TermEntity

@Dao
abstract class TermDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: TermEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<TermEntity>): Completable

    @Query("SELECT * FROM terms")
    abstract fun getAll(): Observable<List<TermEntity>>

    @Query("DELETE FROM terms")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<TermEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM terms WHERE groups LIKE '%' || :group || '%' " +
            "AND day = :day AND (teacher LIKE '%' || :teacherOrSubject || '%' OR subject LIKE '%' || :teacherOrSubject || '%')")
    abstract fun getByAllFilters(group: String, day: String, teacherOrSubject: String): Observable<List<TermEntity>>

    @Query("SELECT * FROM terms WHERE teacher LIKE '%' || :teacherOrSubject || '%' OR subject LIKE '%' || :teacherOrSubject || '%'")
    abstract fun getByTeacherOrSubject(teacherOrSubject: String): Observable<List<TermEntity>>

    @Query("SELECT * FROM terms WHERE groups LIKE '%' || :group || '%'")
    abstract fun getByGroup(group: String): Observable<List<TermEntity>>

    @Query("SELECT * FROM terms WHERE day = :day")
    abstract fun getByDay(day: String): Observable<List<TermEntity>>

    @Query("SELECT * FROM terms WHERE day = :day AND " +
            "(teacher LIKE '%' || :teacherOrSubject || '%' OR subject LIKE '%' || :teacherOrSubject || '%')")
    abstract fun getByDayAndTeacherSubject(day: String, teacherOrSubject: String): Observable<List<TermEntity>>

    @Query("SELECT * FROM terms WHERE groups LIKE '%' || :group || '%' AND " +
            "(teacher LIKE '%' || :teacherOrSubject || '%' OR subject LIKE '%' || :teacherOrSubject || '%')")
    abstract fun getByGroupAndTeacherSubject(group: String, teacherOrSubject: String): Observable<List<TermEntity>>

    @Query("SELECT * FROM terms WHERE groups LIKE '%' || :group || '%' AND day = :day")
    abstract fun getByGroupAndDay(group: String, day: String): Observable<List<TermEntity>>

}