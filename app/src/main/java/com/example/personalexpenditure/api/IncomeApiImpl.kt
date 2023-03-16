package com.example.personalexpenditure.api

import com.example.personalexpenditure.model.PostData
import com.example.personalexpenditure.utils.Resource
import retrofit2.http.Body
import javax.inject.Inject

class IncomeApiImpl @Inject constructor(private val api: IncomePostApiService) {
    suspend fun postIncome(postData: PostData): Resource<PostData?> {
        val response = api.postIncome(postData)
        return if (response.isSuccessful){
            Resource.success(response.body())
        }else{
            Resource.error("Not posted", null)
        }
    }
}