package com.example.personalexpenditure.repository

import com.example.personalexpenditure.api.IncomeApiImpl
import com.example.personalexpenditure.api.IncomePostApiService
import com.example.personalexpenditure.di.AppModule
import com.example.personalexpenditure.model.PostData
import com.example.personalexpenditure.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class IncomeRepository @Inject constructor(private val api: IncomeApiImpl) {

    suspend fun createPost(postData: PostData) = flow {
        emit(Resource.loading(null))
        emit(api.postIncome(postData))
    }.flowOn(Dispatchers.IO)
}