package rs.raf.projekat2.studenthelperraf.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.studenthelperraf.data.models.Note
import rs.raf.projekat2.studenthelperraf.data.models.Resource
import rs.raf.projekat2.studenthelperraf.data.repositories.NoteRepository
import rs.raf.projekat2.studenthelperraf.data.repositories.TermRepository
import rs.raf.projekat2.studenthelperraf.presentation.contract.MainContract
import rs.raf.projekat2.studenthelperraf.presentation.view.filters.MyNoteFilter
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
    override val noteCountState: MutableLiveData<ForLocalNoteState> = MutableLiveData()


    private val publishSubjectForTitleAndContentNote: PublishSubject<MyNoteFilter> = PublishSubject.create()

    init {
        val subscription = publishSubjectForTitleAndContentNote
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                noteRepository
                    .getAllByTitleOrContent(it.titleAndContent, it.getArchived)
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
        subscriptions.add(subscription)
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

    override fun getTermsByDayAndTeacherSubject(day: String, teacherOrSubject: String){
        val subscription = termRepository
            .getAllByDayAndTeacherSubject(day, teacherOrSubject)
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
    override fun getTermsByGroupAndTeacherSubject(group: String, teacherOrSubject: String){
        val subscription = termRepository
            .getAllByGroupAndTeacherSubject(group, teacherOrSubject)
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
    override fun getTermsByGroupAndDay(group: String, day: String){
        val subscription = termRepository
            .getAllByGroupAndDay(group, day)
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
    override fun getTermsByTeacherSubject(teacherOrSubject: String){
        val subscription = termRepository
            .getAllByTeacherOrSubject(teacherOrSubject)
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
    override fun getTermsByGroup(group: String){
        val subscription = termRepository
            .getAllByGroup(group)
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
    override fun getTermsByDay(day: String){
        val subscription = termRepository
            .getAllByDay(day)
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
    override fun getTermsByAllFilters(group: String, day: String, teacherOrSubject: String) {
        val subscription = termRepository
            .getAllByAllFilters(group, day, teacherOrSubject)
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
                    noteCountState.value = ForLocalNoteState.GetNotesCountOneDayAgo(it)
                },
                {
                    noteCountState.value = ForLocalNoteState.Error("Error happened while counting")
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
                    noteCountState.value = ForLocalNoteState.GetNotesCountTwoDaysAgo(it)
                },
                {
                    noteCountState.value = ForLocalNoteState.Error("Error happened while counting")
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
                    noteCountState.value = ForLocalNoteState.GetNotesCountThreeDaysAgo(it)
                },
                {
                    noteCountState.value = ForLocalNoteState.Error("Error happened while counting")
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
                    noteCountState.value = ForLocalNoteState.GetNotesCountFourDaysAgo(it)
                },
                {
                    noteCountState.value = ForLocalNoteState.Error("Error happened while counting")
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
                    noteCountState.value = ForLocalNoteState.GetNotesCountFiveDaysAgo(it)
                },
                {
                    noteCountState.value = ForLocalNoteState.Error("Error happened while counting")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getNotesByTitleOrContent(titleOrContent: String, getArchived: Boolean) {
        publishSubjectForTitleAndContentNote.onNext(MyNoteFilter(titleOrContent, getArchived))
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
        subscriptions.clear()
        super.onCleared()
    }
}