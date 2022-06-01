package rs.raf.projekat2.studenthelperraf.presentation.view.states

import rs.raf.projekat2.studenthelperraf.data.models.Term

sealed class ForRemoteTermState {
    object Loading: ForRemoteTermState()
    object DataFetched: ForRemoteTermState()
    data class Success(val terms: List<Term>): ForRemoteTermState()
    data class Error(val message: String): ForRemoteTermState()
}