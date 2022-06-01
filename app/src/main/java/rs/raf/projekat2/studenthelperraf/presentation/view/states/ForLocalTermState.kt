package rs.raf.projekat2.studenthelperraf.presentation.view.states

sealed class ForLocalTermState {
    object Success: ForLocalTermState()
    data class Error(val message: String): ForLocalTermState()
}