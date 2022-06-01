package rs.raf.projekat2.studenthelperraf.data.models

data class Term (
    val id: Int,
    val subject: String,
    val type: String,
    val teacher: String,
    val groups: String,
    val day: String,
    val time: String,
    val classroom: String
)