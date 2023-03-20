package com.example.jane.dagger


import com.example.jane.views.ViewModelFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StarWarsViewModelModule::class])
interface StarWarsViewModelComponent {

    fun inject(vmFragment: ViewModelFragment)

}