package com.loc.newsapp.di

import android.content.Context
import com.loc.newsapp.data.LocalUserManagerImpl
import com.loc.newsapp.data.remote.dto.ApiService
import com.loc.newsapp.data.remote.repository.NewsRepositoryImpl
import com.loc.newsapp.domain.manager.LocalUserManager
import com.loc.newsapp.domain.repository.NewsRepository
import com.loc.newsapp.domain.usecase.app_entry.AppEntryUsecase
import com.loc.newsapp.domain.usecase.app_entry.ReadAppEntry
import com.loc.newsapp.domain.usecase.app_entry.SaveAppEntry
import com.loc.newsapp.domain.usecase.news.GetNews
import com.loc.newsapp.domain.usecase.news.NewsUseCases
import com.loc.newsapp.domain.usecase.news.search.SearchNews
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(@ApplicationContext context: Context): LocalUserManager =
        LocalUserManagerImpl(context)

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
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    }

}