package rs.raf.projekat2.studenthelperraf.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity (
    val title: String,
    val content: String,
    val archived: Boolean
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}