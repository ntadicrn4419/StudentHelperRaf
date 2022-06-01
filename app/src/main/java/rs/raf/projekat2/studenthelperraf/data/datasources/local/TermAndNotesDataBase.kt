package rs.raf.projekat2.studenthelperraf.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2.studenthelperraf.data.models.NoteEntity
import rs.raf.projekat2.studenthelperraf.data.models.TermEntity

@Database(
    entities = [TermEntity::class, NoteEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters()
abstract class TermAndNotesDataBase : RoomDatabase() {
    abstract fun getTermDao(): TermDao
    abstract fun getNoteDao(): NoteDao
}