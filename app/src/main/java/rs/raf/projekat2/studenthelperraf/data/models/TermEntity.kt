package rs.raf.projekat2.studenthelperraf.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "terms")
data class TermEntity (
    val subject: String,
    val type: String,
    val teacher: String,
    val groups: String,
    val day: String,
    val time: String,
    val classroom: String
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}