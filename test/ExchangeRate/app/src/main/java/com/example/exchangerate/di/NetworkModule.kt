package com.example.exchangerate.di

import android.content.Context
import com.example.exchangerate.BuildConfig
import com.example.exchangerate.R
import com.example.exchangerate.data.remote.ListOfCurrenciesImpl
import com.example.exchangerate.data.remote.RateController
import com.example.exchangerate.domain.ListOfCurrenciesUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideUseCase(impl: ListOfCurrenciesImpl) = ListOfCurrenciesUseCase(impl)

    @Singleton
    @Provides
    fun provideRateController(
        retrofit: Retrofit
    ): RateController = retrofit.create(RateController::class.java)

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        OkHttpClient.Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .build()
    } else {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideGSONBuilder(@ApplicationContext context: Context): Gson = GsonBuilder()
        .setLenient()
        .setDateFormat(context.getString(R.string.retrofit_gson_date_format))
        .create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient,@ApplicationContext context: Context): Retrofit =
        Retrofit.Builder()
            .baseUrl(context.getString(R.string.retrofit_url))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
}