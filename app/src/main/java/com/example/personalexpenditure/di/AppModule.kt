package com.example.personalexpenditure.di

import com.example.personalexpenditure.api.IncomeApiImpl
import com.example.personalexpenditure.api.IncomePostApi
import com.example.personalexpenditure.api.IncomePostApiService

import com.example.personalexpenditure.constant.cConstants.BASE_URL
import com.example.personalexpenditure.repository.PostRepository

import com.example.personalexpenditure.utils.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule  {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(NetworkInterceptor())
        .build()

    @Singleton
    @Provides
    fun getRetrofitInstance(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getRetrofitServiceInstance(retrofit: Retrofit): IncomePostApiService{
        return retrofit.create(IncomePostApiService::class.java)
    }

    @Singleton
    @Provides

    fun getIncomeRepository(incomeApiImpl: IncomeApiImpl): PostRepository {
        return PostRepository((incomeApiImpl))
    }


    @Singleton
    @Provides
    fun getIncomeApi(campaignApiService: IncomePostApiService): IncomePostApi {
        return IncomeApiImpl(campaignApiService)
    }


}

