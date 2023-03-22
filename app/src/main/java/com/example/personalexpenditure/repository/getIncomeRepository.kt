package com.example.personalexpenditure.repository

import com.example.personalexpenditure.api.IncomeApiImpl
import com.example.personalexpenditure.api.IncomePostApi
import com.example.personalexpenditure.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class getIncomeRepository @Inject constructor(private val api: IncomeApiImpl) {
    suspend fun getIncome()= flow {
        emit(Resource.loading(null))
        emit(api.getIncome())
    }.flowOn(Dispatchers.IO)
}