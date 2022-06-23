package com.example.trackvendor.di.module

import androidx.lifecycle.ViewModel
import com.example.trackvendor.ui.MainActivityViewModel
import com.example.trackvendor.usecases.StorageInterface
import com.example.trackvendor.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel
}
