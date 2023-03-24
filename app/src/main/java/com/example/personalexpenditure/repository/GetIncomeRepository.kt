package com.example.personalexpenditure.repository

import com.example.personalexpenditure.api.IncomePostApi
import com.example.personalexpenditure.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetIncomeRepository @Inject constructor(private val api: IncomePostApi) {
    suspend fun getIncome()= flow {
        emit(Resource.loading(null))
        emit(api.getIncomes())
    }.flowOn(Dispatchers.IO)
}