package com.example.personalexpenditure.repository

import com.example.personalexpenditure.api.IncomeApiImpl
import com.example.personalexpenditure.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class IncomeRepository @Inject constructor(private val api: IncomeApiImpl) {

    suspend fun createPost(postData: PostData) = flow {
        emit(Resource.loading(null))
        emit(api.postIncome(postData))
    }.flowOn(Dispatchers.IO)

    //expenditure repo
    suspend fun postExpenditure(incomeId: String, expenditure: Expenditure) = flow {
        emit(Resource.loading(null))
        emit(api.postExpenditure(incomeId, expenditure))
    }.flowOn(Dispatchers.IO)
}