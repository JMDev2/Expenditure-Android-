package com.example.personalexpenditure.api





import com.example.personalexpenditure.model.Expenditure
import com.example.personalexpenditure.model.Income
import com.example.personalexpenditure.model.TotalResponse
import com.example.personalexpenditure.utils.Resource
import javax.inject.Inject

class IncomeApiImpl @Inject constructor(private val api: IncomePostApiService): IncomePostApi {
    suspend fun postIncome(userId: String, postIncomeResponse: Income): Resource<Income?> {
        val response = api.postIncome(userId, postIncomeResponse)
        return if (response.isSuccessful){
            val success = Resource.success(response.body())
            success
        }else{
            Resource.error("Not posted", null)
        }
    }

    //post expenditure
    suspend fun postExpenditure(userId: String, expenditure: Expenditure): Resource<Expenditure?> {
        val response = api.postExpenditure(userId, expenditure)
        return if (response.isSuccessful){
            Resource.success(response.body(), 201)
        }else{
            Resource.error("No posted", null)
        }
    }



    //get total
    override suspend fun getTotal(userId: String): Resource<TotalResponse?> {
        val response = api.getTotal(userId)
        return if (response.isSuccessful){
            Resource.success(response.body())
        }else{

            Resource.error("Not not Found", null)
        }
    }

    override suspend fun getTotalIncome(userId: String): Resource<TotalResponse?> {
        val response = api.getTotalIncome(userId)
        return if (response.isSuccessful){
            Resource.success(response.body())
        }else{

            Resource.error("Not not Found", null)
        }
    }



    // get total expenditure
    override suspend fun getTotalExpenditure(expenditureId: String): Resource<Expenditure?> {
        val response = api.getExpenditure(expenditureId)
        return if (response.isSuccessful){
            Resource.success(response.body())
        }else{
            Resource.error("no expenditure", null)
        }
    }


}