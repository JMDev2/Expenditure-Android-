package com.example.personalexpenditure.api

import com.example.personalexpenditure.model.PostData
import dagger.Provides
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import javax.inject.Singleton


interface IncomePostApiService {
    @POST("income")
    suspend fun postIncome(
        @Body postData: PostData
    ): Response<PostData>


}