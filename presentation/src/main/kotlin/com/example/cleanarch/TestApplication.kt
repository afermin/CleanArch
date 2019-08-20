package com.example.cleanarch

import android.app.Application
import com.example.data.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Alexander Fermin (alexfer06@gmail.com) on 2019-08-19.
 */
class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@TestApplication)

            modules(networkModule)
        }
    }
}