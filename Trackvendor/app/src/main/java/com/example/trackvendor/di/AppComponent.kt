package com.example.trackvendor.di


import android.content.Context
import com.example.trackvendor.WifiStateApplication
import com.example.trackvendor.di.module.NetworkModule
import com.example.trackvendor.di.module.StorageModule
import com.example.trackvendor.di.module.UseCaseModule
import com.example.trackvendor.di.module.ViewModelModule
import com.example.trackvendor.ui.MainActivity
import com.example.trackvendor.utils.ViewModelFactory
import com.example.trackvendor.worker.TrackService
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        StorageModule::class,
        UseCaseModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(app: WifiStateApplication)
    fun inject(service: TrackService)
    fun viewModelsFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        fun build(): AppComponent
    }
}