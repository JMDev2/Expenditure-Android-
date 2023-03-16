package com.example.personalexpenditure.di

import com.example.personalexpenditure.api.IncomeApiImpl
import com.example.personalexpenditure.api.IncomePostApi
import com.example.personalexpenditure.api.IncomePostApiService
import com.example.personalexpenditure.constant.Constants.BASE_URL
import com.example.personalexpenditure.model.PostData
import com.example.personalexpenditure.repository.IncomeRepository
import com.example.personalexpenditure.utils.Resource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule  {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
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
    fun getIncomeRepository(incomeApiImpl: IncomeApiImpl): IncomeRepository{
        return IncomeRepository((incomeApiImpl))
    }

}