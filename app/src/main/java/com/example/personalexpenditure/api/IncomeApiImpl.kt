package com.example.personalexpenditure.api

import com.example.personalexpenditure.model.Expenditure
import com.example.personalexpenditure.model.IncomeResponse
import com.example.personalexpenditure.model.PostData
import com.example.personalexpenditure.utils.Resource
import retrofit2.http.Body
import javax.inject.Inject 

class IncomeApiImpl @Inject constructor(private val api: IncomePostApiService): IncomePostApi {
   suspend fun postIncome(postData: PostData): Resource<PostData?> {
        val response = api.postIncome(postData)
        return if (response.isSuccessful){
            val success = Resource.success(response.body())
            success
        }else{
            Resource.error("Not posted", null)
        }
    }

    //post expenditure
    suspend fun postExpenditure(incomeId: String, expenditure: Expenditure): Resource<Expenditure?> {
        val response = api.postExpenditure(incomeId,expenditure)
        return if (response.isSuccessful){
            Resource.success(response.body(), 201)
        }else{
            Resource.error("No posted", null)
        }
    }

    //get income
     override suspend fun getIncomes(): Resource<IncomeResponse?> {
        val response = api.getIncome()
        return if (response.isSuccessful){
            Resource.success(response.body())
        }else{

            Resource.error("Income not Found", null)
        }
    }


}