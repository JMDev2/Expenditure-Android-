package com.example.personalexpenditure.model

data class TotalResponse(
    val expenditure: ExpenditureX,
    val income: Income,
    val userId: String
)