package com.loc.newsapp.di

import android.app.Application
import com.loc.newsapp.data.LocalUserManagerImpl
import com.loc.newsapp.data.remote.ApiService
import com.loc.newsapp.data.remote.repository.NewsRepositoryImpl
import com.loc.newsapp.domain.manager.LocalUserManager
import com.loc.newsapp.domain.repository.NewsRepository
import com.loc.newsapp.domain.usecase.app_entry.AppEntryUsecase
import com.loc.newsapp.domain.usecase.app_entry.ReadAppEntry
import com.loc.newsapp.domain.usecase.app_entry.SaveAppEntry
import com.loc.newsapp.domain.usecase.news.GetNews
import com.loc.newsapp.domain.usecase.news.NewsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) = AppEntryUsecase(
        readAppEntry = ReadAppEntry(localUserManager), saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsRepository(newsApiService: ApiService): NewsRepository =
        NewsRepositoryImpl(apiService = newsApiService)

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository): NewsUseCases {
        return NewsUseCases(getNews = GetNews(newsRepository))
    }

}