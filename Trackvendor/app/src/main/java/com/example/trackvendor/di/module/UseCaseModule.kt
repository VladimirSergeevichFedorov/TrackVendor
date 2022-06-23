package com.example.trackvendor.di.module

import com.example.trackvendor.storage.StorageImpl
import com.example.trackvendor.usecases.StorageInterface
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {
    @Binds
    fun bindLoginRepo(impl: StorageImpl): StorageInterface
}