package com.example.personalexpenditure.api

import com.example.personalexpenditure.model.Expenditure

import com.example.personalexpenditure.model.IncomeResponseItem
import com.example.personalexpenditure.model.PostData
import okhttp3.ResponseBody

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface IncomePostApiService {
    //post income
    @POST("income")
    suspend fun postIncome(
        @Body postData: PostData
    ): Response<PostData>

    //post expenditure
    @POST("expenditure/{incomeId}")
    suspend fun postExpenditure(@Path("incomeId") incomeId: String,
        @Body expenditure: Expenditure
    ): Response<Expenditure>



    @GET("income/{incomeId}")
    suspend fun getIncome(@Path("incomeId") incomeId: String
    ): Response<IncomeResponseItem>

    @GET("expenditure/{expenditureId}")
    suspend fun getExpenditure(@Path("expenditureId") expenditureId: String
    ): Response<Expenditure>

}
