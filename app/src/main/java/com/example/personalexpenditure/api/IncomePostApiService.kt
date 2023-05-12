package com.example.personalexpenditure.api


import com.example.personalexpenditure.model.Expenditure
import com.example.personalexpenditure.model.Income

import com.example.personalexpenditure.model.TotalResponse

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface IncomePostApiService {
    //post income
//    @POST("income")
//    suspend fun postIncome(
//        @Body postData: PostData
//    ): Response<PostData>

    @POST("users/{userId}/income")
    suspend fun postIncome(
        @Path("userId") userId: String,
        @Body postIncomeResponse: Income
    ): Response<Income>

    //post expenditure
    @POST("users/{userId}/expenditure")
    suspend fun postExpenditure(
        @Path("userId") userId: String,
        @Body expenditure: Expenditure
    ): Response<Expenditure>



    @GET("users/{userId}")
    suspend fun getTotal(
        @Path("userId") userId: String
    ): Response<TotalResponse>



//    @GET("users/{userId}")
//    suspend fun getTotalIncome(
//        @Path("userId") userId: String
//    ): Response<PostIncomeResponse>

//    @GET("expenditure/{expenditureId}")
//    suspend fun getExpenditure(
//        @Path("expenditureId") expenditureId: String
//    ): Response<Expenditure>



}
