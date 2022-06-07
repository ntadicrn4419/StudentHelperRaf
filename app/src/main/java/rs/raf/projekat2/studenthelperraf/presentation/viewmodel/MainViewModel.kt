package rs.raf.projekat2.studenthelperraf.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.studenthelperraf.data.models.MyFilter
import rs.raf.projekat2.studenthelperraf.data.models.Note
import rs.raf.projekat2.studenthelperraf.data.models.Resource
import rs.raf.projekat2.studenthelperraf.data.repositories.NoteRepository
import rs.raf.projekat2.studenthelperraf.data.repositories.TermRepository
import rs.raf.projekat2.studenthelperraf.presentation.contract.MainContract
import rs.raf.projekat2.studenthelperraf.presentation.view.states.ForLocalNoteState
import rs.raf.projekat2.studenthelperraf.presentation.view.states.ForLocalTermState
import rs.raf.projekat2.studenthelperraf.presentation.view.states.ForRemoteTermState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainViewModel(
    private val termRepository: TermRepository,
    private val noteRepository: NoteRepository
) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val remoteTermState: MutableLiveData<ForRemoteTermState> = MutableLiveData()
    override val localTermState: MutableLiveData<ForLocalTermState> = MutableLiveData()
    override val localNoteState: MutableLiveData<ForLocalNoteState> = MutableLiveData()

    private val publishSubjectForTitleAndContentNote: PublishSubject<String> = PublishSubject.create()

//    private val publishSubjectForAllFilters : PublishSubject<MyFilter> = PublishSubject.create()
//    private val publishSubjectForDayAndTeacherSubject : PublishSubject<MyFilter> = PublishSubject.create()
//    private val publishSubjectForGroupAndTeacherSubject : PublishSubject<MyFilter> = PublishSubject.create()
//    private val publishSubjectForGroupAndDay : PublishSubject<MyFilter> = PublishSubject.create()
//    private val publishSubjectForDay : PublishSubject<MyFilter> = PublishSubject.create()
//    private val publishSubjectForTeacherOrSubject : PublishSubject<MyFilter> = PublishSubject.create()
//    private val publishSubjectForGroup : PublishSubject<MyFilter> = PublishSubject.create()


    init {
//        val subscription1 = publishSubjectForAllFilters
//            .debounce(200, TimeUnit.MILLISECONDS)
//            .distinctUntilChanged()
//            .switchMap {
//                termRepository
//                    .getAllByAllFilters(it.group, it.day, it.teacherOrSubject)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnError {
//                        Timber.e("Error in publish subject doing getAllByAllFilters")
//                        Timber.e(it)
//                    }
//            }
//            .subscribe(
//                {
//                    localTermState.value = ForLocalTermState.SuccessfullyGotByAllFilters(it)
//                },
//                {
//                    localTermState.value = ForLocalTermState.ErrorLocalState("Error happened while fetching data from db")
//                    Timber.e(it)
//                }
//            )
//        subscriptions.add(subscription1)
//        //--------------------------------------------------------
//        val subscription2 = publishSubjectForDayAndTeacherSubject
//            .debounce(200, TimeUnit.MILLISECONDS)
//            .distinctUntilChanged()
//            .switchMap {
//                termRepository
//                    .getAllByDayAndTeacherSubject(it.day, it.teacherOrSubject)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnError {
//                        Timber.e("Error in publish subject")
//                        Timber.e(it)
//                    }
//            }
//            .subscribe(
//                {
//                    localTermState.value = ForLocalTermState.SuccessfullyGotByDayAndTeacherSubject(it)
//                },
//                {
//                    localTermState.value = ForLocalTermState.ErrorLocalState("Error happened while fetching data from db")
//                    Timber.e(it)
//                }
//            )
//        subscriptions.add(subscription2)
//        //---------------------------------------
//        val subscription3 = publishSubjectForGroupAndTeacherSubject
//            .debounce(200, TimeUnit.MILLISECONDS)
//            .distinctUntilChanged()
//            .switchMap {
//                termRepository
//                    .getAllByGroupAndTeacherSubject(it.group, it.teacherOrSubject)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnError {
//                        Timber.e("Error in publish subject")
//                        Timber.e(it)
//                    }
//            }
//            .subscribe(
//                {
//                    localTermState.value = ForLocalTermState.SuccessfullyGotByGroupAndTeacherSubject(it)
//                },
//                {
//                    localTermState.value = ForLocalTermState.ErrorLocalState("Error happened while fetching data from db")
//                    Timber.e(it)
//                }
//            )
//        subscriptions.add(subscription3)
//        //---------------------------------------
//        val subscription4 = publishSubjectForGroupAndDay
//            .debounce(200, TimeUnit.MILLISECONDS)
//            .distinctUntilChanged()
//            .switchMap {
//                termRepository
//                    .getAllByGroupAndDay(it.group, it.day)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnError {
//                        Timber.e("Error in publish subject")
//                        Timber.e(it)
//                    }
//            }
//            .subscribe(
//                {
//                    localTermState.value = ForLocalTermState.SuccessfullyGotByGroupAndDay(it)
//                },
//                {
//                    localTermState.value = ForLocalTermState.ErrorLocalState("Error happened while fetching data from db")
//                    Timber.e(it)
//                }
//            )
//        subscriptions.add(subscription4)
//        //---------------------------------------
//        val subscription5 = publishSubjectForDay
//            .debounce(200, TimeUnit.MILLISECONDS)
//            .distinctUntilChanged()
//            .switchMap {
//                termRepository
//                    .getAllByDay(it.day)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnError {
//                        Timber.e("Error in publish subject")
//                        Timber.e(it)
//                    }
//            }
//            .subscribe(
//                {
//                    localTermState.value = ForLocalTermState.SuccessfullyGotByDay(it)
//                },
//                {
//                    localTermState.value = ForLocalTermState.ErrorLocalState("Error happened while fetching data from db")
//                    Timber.e(it)
//                }
//            )
//        subscriptions.add(subscription5)
//        //---------------------------------------
//        val subscription6 = publishSubjectForTeacherOrSubject
//            .debounce(200, TimeUnit.MILLISECONDS)
//            .distinctUntilChanged()
//            .switchMap {
//                termRepository
//                    .getAllByTeacherOrSubject(it.teacherOrSubject)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnError {
//                        Timber.e("Error in publish subject")
//                        Timber.e(it)
//                    }
//            }
//            .subscribe(
//                {
//                    localTermState.value = ForLocalTermState.SuccessfullyGotByTeacherOrSubject(it)
//                },
//                {
//                    localTermState.value = ForLocalTermState.ErrorLocalState("Error happened while fetching data from db")
//                    Timber.e(it)
//                }
//            )
//        subscriptions.add(subscription6)
//        //---------------------------------------
//        val subscription7 = publishSubjectForGroup
//            .debounce(200, TimeUnit.MILLISECONDS)
//            .distinctUntilChanged()
//            .switchMap {
//                termRepository
//                    .getAllByGroup(it.group)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnError {
//                        Timber.e("Error in publish subject")
//                        Timber.e(it)
//                    }
//            }
//            .subscribe(
//                {
//                    localTermState.value = ForLocalTermState.SuccessfullyGotByGroup(it)
//                },
//                {
//                    localTermState.value = ForLocalTermState.ErrorLocalState("Error happened while fetching data from db")
//                    Timber.e(it)
//                }
//            )
//        subscriptions.add(subscription7)

        val subscription10 = publishSubjectForTitleAndContentNote
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                noteRepository
                    .getAllByTitleOrContent(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject doing getAllByTitleOrContent")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    localNoteState.value = ForLocalNoteState.SuccessfullyGotFilteredNotes(it)
                },
                {
                    localNoteState.value = ForLocalNoteState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription10)

    }

    override fun fetchAllTerms() {
        val subscription = termRepository
            .fetchAll()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> remoteTermState.value = ForRemoteTermState.LoadingRemoteState
                        is Resource.Success -> remoteTermState.value = ForRemoteTermState.DataFetchedRemoteState
                        is Resource.Error -> remoteTermState.value = ForRemoteTermState.ErrorRemoteState("Error happened while fetching data from the server")
                    }
                },
                {
                    remoteTermState.value = ForRemoteTermState.ErrorRemoteState("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllTerms() {
        val subscription = termRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localTermState.value = ForLocalTermState.SuccessfullyGotAll(it)
                },
                {
                     localTermState.value = ForLocalTermState.ErrorLocalState("Error happened while getting data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

//    override fun getTermsByDayAndTeacherSubject(filter: MyFilter){
//        publishSubjectForDayAndTeacherSubject.onNext(filter)
//    }
//    override fun getTermsByGroupAndTeacherSubject(filter: MyFilter){
//        publishSubjectForGroupAndTeacherSubject.onNext(filter)
//    }
//    override fun getTermsByGroupAndDay(filter: MyFilter){
//        publishSubjectForGroupAndDay.onNext(filter)
//    }
//    override fun getTermsByTeacherSubject(filter: MyFilter){
//        publishSubjectForTeacherOrSubject.onNext(filter)
//    }
//    override fun getTermsByGroup(filter: MyFilter){
//        publishSubjectForGroup.onNext(filter)
//    }
//    override fun getTermsByDay(filter: MyFilter){
//        publishSubjectForDay.onNext(filter)
//    }
//    override fun getTermsByAllFilters(filter: MyFilter) {
//        publishSubjectForAllFilters.onNext(filter)
//    }

    override fun getTermsByDayAndTeacherSubject(filter: MyFilter){
        val subscription = termRepository
            .getAllByDayAndTeacherSubject(filter.day, filter.teacherOrSubject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localTermState.value = ForLocalTermState.SuccessfullyGotByDayAndTeacherSubject(it)
                },
                {
                    localTermState.value = ForLocalTermState.ErrorLocalState("Error happened while getting data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun getTermsByGroupAndTeacherSubject(filter: MyFilter){
        val subscription = termRepository
            .getAllByGroupAndTeacherSubject(filter.group, filter.teacherOrSubject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localTermState.value = ForLocalTermState.SuccessfullyGotByGroupAndTeacherSubject(it)
                },
                {
                    localTermState.value = ForLocalTermState.ErrorLocalState("Error happened while getting data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun getTermsByGroupAndDay(filter: MyFilter){
        val subscription = termRepository
            .getAllByGroupAndDay(filter.group, filter.day)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localTermState.value = ForLocalTermState.SuccessfullyGotByGroupAndDay(it)
                },
                {
                    localTermState.value = ForLocalTermState.ErrorLocalState("Error happened while getting data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun getTermsByTeacherSubject(filter: MyFilter){
        val subscription = termRepository
            .getAllByTeacherOrSubject(filter.teacherOrSubject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localTermState.value = ForLocalTermState.SuccessfullyGotByTeacherOrSubject(it)
                },
                {
                    localTermState.value = ForLocalTermState.ErrorLocalState("Error happened while getting data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun getTermsByGroup(filter: MyFilter){
        val subscription = termRepository
            .getAllByGroup(filter.group)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localTermState.value = ForLocalTermState.SuccessfullyGotByGroup(it)
                },
                {
                    localTermState.value = ForLocalTermState.ErrorLocalState("Error happened while getting data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun getTermsByDay(filter: MyFilter){
        val subscription = termRepository
            .getAllByDay(filter.day)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localTermState.value = ForLocalTermState.SuccessfullyGotByDay(it)
                },
                {
                    localTermState.value = ForLocalTermState.ErrorLocalState("Error happened while getting data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun getTermsByAllFilters(filter: MyFilter) {
        val subscription = termRepository
            .getAllByAllFilters(filter.group, filter.day, filter.teacherOrSubject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localTermState.value = ForLocalTermState.SuccessfullyGotByAllFilters(it)
                },
                {
                    localTermState.value = ForLocalTermState.ErrorLocalState("Error happened while getting data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun addNote(note: Note) {
        val subscription = noteRepository
            .insert(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localNoteState.value = ForLocalNoteState.AddedNote("Successfully added note")
                },
                {
                    localNoteState.value = ForLocalNoteState.Error("Error happened while adding note")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllNotes() {
        val subscription = noteRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localNoteState.value = ForLocalNoteState.SuccessfullyGotAllNotes(it)
                },
                {
                    localNoteState.value = ForLocalNoteState.Error("Error happened while fetching notes from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun deleteNote(id: Int) {
        val subscription = noteRepository
            .delete(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localNoteState.value = ForLocalNoteState.DeletedNote("Successfully deleted note.")
                },
                {
                    localNoteState.value = ForLocalNoteState.Error("Error happened while deleting note")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun updateNote(id: Int, title: String, content: String, archived: Boolean) {
        val subscription = noteRepository
            .update(id, title, content, archived)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localNoteState.value = ForLocalNoteState.UpdatedNote("Successfully updated note.")
                },
                {
                    localNoteState.value = ForLocalNoteState.Error("Error happened while updating note")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getNumberOfNotesOneDayAgo(){
        val subscription = noteRepository
            .getNumberOfNotesOneDayAgo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localNoteState.value = ForLocalNoteState.GetNotesCountOneDayAgo(it)
                },
                {
                    localNoteState.value = ForLocalNoteState.Error("Error happened while counting")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun getNumberOfNotesTwoDaysAgo(){
        val subscription = noteRepository
            .getNumberOfNotesTwoDaysAgo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localNoteState.value = ForLocalNoteState.GetNotesCountTwoDaysAgo(it)
                },
                {
                    localNoteState.value = ForLocalNoteState.Error("Error happened while counting")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun getNumberOfNotesThreeDaysAgo(){
        val subscription = noteRepository
            .getNumberOfNotesThreeDaysAgo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localNoteState.value = ForLocalNoteState.GetNotesCountThreeDaysAgo(it)
                },
                {
                    localNoteState.value = ForLocalNoteState.Error("Error happened while counting")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun getNumberOfNotesFourDaysAgo(){
        val subscription = noteRepository
            .getNumberOfNotesFourDaysAgo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localNoteState.value = ForLocalNoteState.GetNotesCountFourDaysAgo(it)
                },
                {
                    localNoteState.value = ForLocalNoteState.Error("Error happened while counting")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun getNumberOfNotesFiveDaysAgo(){
        val subscription = noteRepository
            .getNumberOfNotesFiveDaysAgo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localNoteState.value = ForLocalNoteState.GetNotesCountFiveDaysAgo(it)
                },
                {
                    localNoteState.value = ForLocalNoteState.Error("Error happened while counting")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getNotesByTitleOrContent(titleOrContent: String) {
        publishSubjectForTitleAndContentNote.onNext(titleOrContent)
    }

    override fun getNotesByArchived(archived: Boolean) {
        val subscription = noteRepository
            .getAllByArchived(archived)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localNoteState.value = ForLocalNoteState.SuccessfullyGotNonArchiveNotes(it)
                },
                {
                    localNoteState.value = ForLocalNoteState.Error("Error happened while fetching notes from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)

    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}