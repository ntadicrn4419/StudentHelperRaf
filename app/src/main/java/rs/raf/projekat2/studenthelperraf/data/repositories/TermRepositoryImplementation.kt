package rs.raf.projekat2.studenthelperraf.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.studenthelperraf.data.datasources.local.TermDao
import rs.raf.projekat2.studenthelperraf.data.datasources.remote.TermService
import rs.raf.projekat2.studenthelperraf.data.models.Resource
import rs.raf.projekat2.studenthelperraf.data.models.Term
import rs.raf.projekat2.studenthelperraf.data.models.TermEntity
import timber.log.Timber

class TermRepositoryImplementation(
    private val localDataSource: TermDao,
    private val remoteDataSource: TermService
) : TermRepository{

    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll()
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.map {
                    TermEntity(
                        it.predmet,
                        it.tip,
                        it.nastavnik,
                        it.grupe,
                        it.dan,
                        it.termin,
                        it.ucionica
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAll(): Observable<List<Term>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Term(
                        it.id,
                        it.subject,
                        it.type,
                        it.teacher,
                        it.groups,
                        it.day,
                        it.time,
                        it.classroom
                    )
                }
            }
    }

    override fun getAllBySubject(subject: String): Observable<List<Term>> {
        return localDataSource
            .getBySubject(subject)
            .map {
                it.map {
                    Term(
                        it.id,
                        it.subject,
                        it.type,
                        it.teacher,
                        it.groups,
                        it.day,
                        it.time,
                        it.classroom
                    )
                }
            }
    }

    override fun getAllByGroup(group: String): Observable<List<Term>> {
        return localDataSource
            .getByGroup(group)
            .map {
                it.map {
                    Term(
                        it.id,
                        it.subject,
                        it.type,
                        it.teacher,
                        it.groups,
                        it.day,
                        it.time,
                        it.classroom
                    )
                }
            }
    }

    override fun getAllByTeacher(teacher: String): Observable<List<Term>> {
        return localDataSource
            .getByTeacher(teacher)
            .map {
                it.map {
                    Term(
                        it.id,
                        it.subject,
                        it.type,
                        it.teacher,
                        it.groups,
                        it.day,
                        it.time,
                        it.classroom
                    )
                }
            }
    }

    override fun getAllByDay(day: String): Observable<List<Term>> {
        return localDataSource
            .getByDay(day)
            .map {
                it.map {
                    Term(
                        it.id,
                        it.subject,
                        it.type,
                        it.teacher,
                        it.groups,
                        it.day,
                        it.time,
                        it.classroom
                    )
                }
            }
    }

    override fun getAllByTime(time: String): Observable<List<Term>> {
        return localDataSource
            .getByTime(time)
            .map {
                it.map {
                    Term(
                        it.id,
                        it.subject,
                        it.type,
                        it.teacher,
                        it.groups,
                        it.day,
                        it.time,
                        it.classroom
                    )
                }
            }
    }

    override fun getAllByAllFilters(
        group: String,
        day: String,
        teacherOrSubject: String
    ): Observable<List<Term>> {
        return localDataSource
            .getByAllFilters(group, day, teacherOrSubject)
            .map {
                it.map {
                    Term(
                        it.id,
                        it.subject,
                        it.type,
                        it.teacher,
                        it.groups,
                        it.day,
                        it.time,
                        it.classroom
                    )
                }
            }
    }


    override fun insert(term: Term): Completable {
        val termEntity =
            TermEntity(
                term.subject,
                term.type,
                term.teacher,
                term.groups,
                term.day,
                term.time,
                term.classroom
            )
        return localDataSource
            .insert(termEntity)
    }

}