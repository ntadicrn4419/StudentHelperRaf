package rs.raf.projekat2.studenthelperraf.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.projekat2.studenthelperraf.modules.coreModule
import rs.raf.projekat2.studenthelperraf.modules.termsNotesModule
import timber.log.Timber

class StudentHelperApp : Application() {
    override fun onCreate() {
        super.onCreate()
        init()

    }

    private fun init() {
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            termsNotesModule
        )
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@StudentHelperApp)
            androidFileProperties()
            fragmentFactory()
            modules(modules)
        }
    }
}