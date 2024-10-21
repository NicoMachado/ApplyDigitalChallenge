package com.ngm.applydigitalchallenge

import android.app.Application
import com.ngm.applydigitalchallenge.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplyDigitalChallengeApp: Application()  {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApplyDigitalChallengeApp)
            modules(
                modules = appModule
            )
        }
    }
}