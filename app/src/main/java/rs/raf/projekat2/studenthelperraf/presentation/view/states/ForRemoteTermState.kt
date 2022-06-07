package rs.raf.projekat2.studenthelperraf.presentation.view.states

import rs.raf.projekat2.studenthelperraf.data.models.Term

sealed class ForRemoteTermState {
    object LoadingRemoteState: ForRemoteTermState()
    object DataFetchedRemoteState: ForRemoteTermState()
    data class SuccessRemoteState(val terms: List<Term>): ForRemoteTermState()
    data class ErrorRemoteState(val message: String): ForRemoteTermState()
}