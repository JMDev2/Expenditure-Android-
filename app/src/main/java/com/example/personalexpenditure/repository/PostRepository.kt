package com.example.personalexpenditure.repository

import com.example.personalexpenditure.api.IncomeApiImpl
import com.example.personalexpenditure.model.Expenditure
import com.example.personalexpenditure.model.Income

import com.example.personalexpenditure.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRepository @Inject constructor(private val api: IncomeApiImpl) {

    suspend fun createPost(userId: String, postIncomeResponse: Income) = flow {
        emit(Resource.loading(null))
        emit(api.postIncome(userId, postIncomeResponse))
    }.flowOn(Dispatchers.IO)

    //expenditure repo
    suspend fun postExpenditure(userId: String, expenditure: Expenditure) = flow {
        emit(Resource.loading(null))
        emit(api.postExpenditure(userId, expenditure))
    }.flowOn(Dispatchers.IO)
}