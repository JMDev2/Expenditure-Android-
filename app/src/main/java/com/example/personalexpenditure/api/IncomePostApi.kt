package com.example.personalexpenditure.api

import com.example.personalexpenditure.model.Expenditure
import com.example.personalexpenditure.model.IncomeResponse

import com.example.personalexpenditure.utils.Resource
import okhttp3.ResponseBody

interface IncomePostApi {

    suspend fun getIncomes(): Resource<IncomeResponse?>
    suspend fun getTotalExpenditure(expenditureId: String): Resource<Int?>

   // suspend fun postExpenditure(): Resource<Expenditure?>
}