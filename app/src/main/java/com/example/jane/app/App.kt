package com.example.jane.app

import android.app.Application
import com.example.jane.dagger.DaggerStarWarsViewModelComponent
import com.example.jane.dagger.StarWarsViewModelComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        daggerComponent = DaggerStarWarsViewModelComponent.create()

    }

    companion object {
        lateinit var daggerComponent : StarWarsViewModelComponent
    }
}