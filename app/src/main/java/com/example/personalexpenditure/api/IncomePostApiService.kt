package com.example.personalexpenditure.api

import com.example.personalexpenditure.model.Expenditure
import com.example.personalexpenditure.model.IncomeResponse
import com.example.personalexpenditure.model.PostData
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



    @GET("income")
    suspend fun getIncome(): Response<IncomeResponse>

}
