package com.example.personalexpenditure.api

import com.example.personalexpenditure.model.Expenditure

import com.example.personalexpenditure.model.IncomeResponseItem

import com.example.personalexpenditure.utils.Resource
import okhttp3.ResponseBody

interface IncomePostApi {

    suspend fun getIncomes(incomeId: String): Resource<IncomeResponseItem?>
    suspend fun getTotalExpenditure(expenditureId: String): Resource<Expenditure?>

   // suspend fun postExpenditure(): Resource<Expenditure?>
}