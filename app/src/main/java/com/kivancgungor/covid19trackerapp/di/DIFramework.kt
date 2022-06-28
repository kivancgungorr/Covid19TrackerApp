package com.kivancgungor.covid19trackerapp.di

import android.content.Context
import com.kivancgungor.covid19trackerapp.repository.ItemRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

object DIFramework {

    fun init(context: Context) {
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(context)
            val repoModule = module {
                single { ItemRepository.getInstance() }
            }
            // declare modules
            modules(repoModule)
        }
    }
}