package com.deevvdd.politicalpreparedness

import android.app.Application
import com.deevvdd.politicalpreparedness.di.dbModule
import com.deevvdd.politicalpreparedness.di.networkModule
import com.deevvdd.politicalpreparedness.di.repositoryModule
import com.deevvdd.politicalpreparedness.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import timber.log.Timber

/**
 * Created by heinhtet deevvdd@gmail.com on 10,August,2021
 */
class PoliticalPreparednessApp : Application() {


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@PoliticalPreparednessApp)
            modules(
                dbModule,
                networkModule,
                repositoryModule,
                viewModelModule,
            )
        }
    }
}