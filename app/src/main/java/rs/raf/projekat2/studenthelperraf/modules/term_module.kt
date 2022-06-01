package rs.raf.projekat2.studenthelperraf.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.studenthelperraf.data.datasources.local.TermAndNotesDataBase
import rs.raf.projekat2.studenthelperraf.data.datasources.remote.TermService
import rs.raf.projekat2.studenthelperraf.data.repositories.NoteRepository
import rs.raf.projekat2.studenthelperraf.data.repositories.NoteRepositoryImplementation
import rs.raf.projekat2.studenthelperraf.data.repositories.TermRepository
import rs.raf.projekat2.studenthelperraf.data.repositories.TermRepositoryImplementation
import rs.raf.projekat2.studenthelperraf.presentation.viewmodel.MainViewModel

val termModule = module {

    viewModel { MainViewModel(termRepository = get(), noteRepository = get()) }

    single<TermRepository> { TermRepositoryImplementation(localDataSource = get(), remoteDataSource = get()) }

    single { get<TermAndNotesDataBase>().getTermDao() }

    single<TermService> { create(retrofit = get()) }

    single<NoteRepository> { NoteRepositoryImplementation(localDataSource = get())}

    single { get<TermAndNotesDataBase>().getNoteDao() }

}