package rs.raf.projekat2.studenthelperraf.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.studenthelperraf.data.models.MyFilter
import rs.raf.projekat2.studenthelperraf.data.models.Resource
import rs.raf.projekat2.studenthelperraf.data.models.Term
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
    override val localNoteState: LiveData<ForLocalNoteState> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()
    private val publishSubjectMyFilter : PublishSubject<MyFilter> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                termRepository
                    .getAllBySubject(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    remoteTermState.value = ForRemoteTermState.Success(it)
                },
                {
                    remoteTermState.value = ForRemoteTermState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
        //---------------------------------------
        val subscription2 = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                termRepository
                    .getAllByGroup(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    remoteTermState.value = ForRemoteTermState.Success(it)
                },
                {
                    remoteTermState.value = ForRemoteTermState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription2)
        //---------------------------------------
        val subscription3 = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                termRepository
                    .getAllByTeacher(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    remoteTermState.value = ForRemoteTermState.Success(it)
                },
                {
                    remoteTermState.value = ForRemoteTermState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription3)
        //---------------------------------------
        val subscription4 = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                termRepository
                    .getAllByDay(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    remoteTermState.value = ForRemoteTermState.Success(it)
                },
                {
                    remoteTermState.value = ForRemoteTermState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription4)
        //---------------------------------------
        val subscription5 = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                termRepository
                    .getAllByTime(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    remoteTermState.value = ForRemoteTermState.Success(it)
                },
                {
                    remoteTermState.value = ForRemoteTermState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription5)
        //---------------------------------------
        val subscription6 = publishSubjectMyFilter
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                termRepository
                    .getAllByAllFilters(it.group, it.day, it.teacherOrSubject)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    remoteTermState.value = ForRemoteTermState.Success(it)
                },
                {
                    remoteTermState.value = ForRemoteTermState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription6)
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
                        is Resource.Loading -> remoteTermState.value = ForRemoteTermState.Loading
                        is Resource.Success -> remoteTermState.value = ForRemoteTermState.DataFetched
                        is Resource.Error -> remoteTermState.value = ForRemoteTermState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    remoteTermState.value = ForRemoteTermState.Error("Error happened while fetching data from the server")
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
                    remoteTermState.value = ForRemoteTermState.Success(it)
                },
                {
                    remoteTermState.value = ForRemoteTermState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getTermsBySubject(subject: String) {
        publishSubject.onNext(subject)
    }

    override fun getTermsByGroup(group: String) {
        publishSubject.onNext(group)
    }

    override fun getTermsByDay(day: String) {
        publishSubject.onNext(day)
    }

    override fun getTermsByTeacher(teacher: String) {
        publishSubject.onNext(teacher)
    }

    override fun getTermsByAllFilters(filter: MyFilter) {
        publishSubjectMyFilter.onNext(filter)
    }

    override fun addTerm(term: Term) {
        val subscription = termRepository
            .insert(term)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localTermState.value = ForLocalTermState.Success
                },
                {
                    localTermState.value = ForLocalTermState.Error("Error happened while adding movie")
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