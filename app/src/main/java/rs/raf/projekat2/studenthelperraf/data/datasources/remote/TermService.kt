package rs.raf.projekat2.studenthelperraf.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.projekat2.studenthelperraf.data.models.TermResponse

interface TermService {
    @GET("terms")
    fun getAll(@Query("limit") limit: Int = 1000): Observable<List<TermResponse>>
}