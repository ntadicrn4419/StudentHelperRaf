package rs.raf.projekat2.studenthelperraf.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.studenthelperraf.data.datasources.local.NoteDao
import rs.raf.projekat2.studenthelperraf.data.models.Note
import rs.raf.projekat2.studenthelperraf.data.models.NoteEntity

class NoteRepositoryImplementation(
    private val localDataSource: NoteDao,
) : NoteRepository {

    override fun insert(note: Note): Completable {
        val noteEntity =
            NoteEntity(
                note.title,
                note.content,
                note.archived,
                note.dateCreated
            )
        return localDataSource.insert(noteEntity)
    }

    override fun getById(id: Int): Observable<Note> {
        return localDataSource
            .getById(id)
            .map {
                Note(
                    it.id,
                    it.title,
                    it.content,
                    it.archived,
                    it.dateCreated
                )
            }
    }

    override fun getAllByTitleOrContent(titleOrContent: String): Observable<List<Note>> {
        return localDataSource
            .getAllByTitleOrContent(titleOrContent)
            .map {
                it.map {
                    Note(
                        it.id,
                        it.title,
                        it.content,
                        it.archived,
                        it.dateCreated
                    )
                }
            }
    }

    override fun getAll(): Observable<List<Note>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Note(
                        it.id,
                        it.title,
                        it.content,
                        it.archived,
                        it.dateCreated
                    )
                }
            }
    }

    override fun getAllByArchived(archived: Boolean): Observable<List<Note>> {
        return localDataSource
            .getAllByArchived(archived)
            .map {
                it.map {
                    Note(
                        it.id,
                        it.title,
                        it.content,
                        it.archived,
                        it.dateCreated
                    )
                }
            }
    }

    override fun delete(id: Int): Completable {
        return localDataSource.delete(id)
    }

    override fun deleteAll(): Completable {
        return localDataSource.deleteAll()
    }

    override fun update(id: Int, title: String, content: String, archived: Boolean): Completable {
        return localDataSource.update(id, title, content, archived)
    }

    override fun getNumberOfNotesOneDayAgo(): Observable<Int>{
        return localDataSource.getNumberOfNotesOneDayAgo()
    }

    override fun getNumberOfNotesTwoDaysAgo(): Observable<Int>{
        return localDataSource.getNumberOfNotesTwoDaysAgo()
    }

    override fun getNumberOfNotesThreeDaysAgo(): Observable<Int>{
        return localDataSource.getNumberOfNotesThreeDaysAgo()
    }

    override fun getNumberOfNotesFourDaysAgo(): Observable<Int>{
        return localDataSource.getNumberOfNotesFourDaysAgo()
    }

    override fun getNumberOfNotesFiveDaysAgo(): Observable<Int>{
        return localDataSource.getNumberOfNotesFiveDaysAgo()
    }
}