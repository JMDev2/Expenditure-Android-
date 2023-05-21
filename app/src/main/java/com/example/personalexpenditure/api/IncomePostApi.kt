package com.example.personalexpenditure.api

import com.example.personalexpenditure.model.Expenditure
import com.example.personalexpenditure.model.TotalResponse
import com.example.personalexpenditure.utils.Resource

interface IncomePostApi {

    suspend fun getTotal(userId: String): Resource<TotalResponse?>
    suspend fun getTotalIncome(userId: String): Resource<TotalResponse?>

    suspend fun getTotalExpenditure(userId: String): Resource<Expenditure?>
}