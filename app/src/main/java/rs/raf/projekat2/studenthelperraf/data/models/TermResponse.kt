package rs.raf.projekat2.studenthelperraf.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TermResponse (
//    val subject: String,
//    val type: String,
//    val teacher: String,
//    val groups: String,
//    val day: String,
//    val time: String,
//    val classroom: String

    val predmet: String,
    val tip: String,
    val nastavnik: String,
    val grupe: String,
    val dan: String,
    val termin: String,
    val ucionica: String

)