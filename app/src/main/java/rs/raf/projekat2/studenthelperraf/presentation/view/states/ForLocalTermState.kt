package rs.raf.projekat2.studenthelperraf.presentation.view.states

import rs.raf.projekat2.studenthelperraf.data.models.Term

sealed class ForLocalTermState {

    data class SuccessfullyGotAll(val terms: List<Term>): ForLocalTermState()
    data class SuccessfullyGotByAllFilters(val terms: List<Term>): ForLocalTermState()
    data class SuccessfullyGotByDayAndTeacherSubject(val terms: List<Term>): ForLocalTermState()
    data class SuccessfullyGotByGroupAndTeacherSubject(val terms: List<Term>): ForLocalTermState()
    data class SuccessfullyGotByGroupAndDay(val terms: List<Term>): ForLocalTermState()
    data class SuccessfullyGotByDay(val terms: List<Term>): ForLocalTermState()
    data class SuccessfullyGotByTeacherOrSubject(val terms: List<Term>): ForLocalTermState()
    data class SuccessfullyGotByGroup(val terms: List<Term>): ForLocalTermState()


    data class ErrorLocalState(val message: String): ForLocalTermState()
}