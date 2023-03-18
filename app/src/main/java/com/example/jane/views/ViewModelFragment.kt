package com.example.jane.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.jane.app.App

import com.example.jane.viewmodel.StarWarsViewModel
import com.example.jane.viewmodel.ViewModelFactory
import javax.inject.Inject

open class ViewModelFragment : Fragment() {

    @Inject
    lateinit var factory : ViewModelFactory
    protected val viewModel : StarWarsViewModel by activityViewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.daggerComponent.inject(this)
    }
}