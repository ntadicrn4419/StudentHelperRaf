package rs.raf.projekat2.studenthelperraf.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.studenthelperraf.data.models.Resource
import rs.raf.projekat2.studenthelperraf.data.models.Term

interface TermRepository {
    fun insert(term: Term): Completable
    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Term>>

    fun getAllByTeacherOrSubject(teacherOrSubject: String): Observable<List<Term>>
    fun getAllByGroup(group: String): Observable<List<Term>>
    fun getAllByDay(day: String): Observable<List<Term>>
    fun getAllByDayAndTeacherSubject(day: String, teacherOrSubject: String): Observable<List<Term>>
    fun getAllByGroupAndTeacherSubject(group: String, teacherOrSubject: String): Observable<List<Term>>
    fun getAllByGroupAndDay(group: String, day: String): Observable<List<Term>>


    fun getAllByAllFilters(group: String, day: String, teacherOrSubject: String): Observable<List<Term>>
}