package com.example.personalexpenditure.model

class IncomeResponse : ArrayList<IncomeResponseItem>()

data class IncomeResponseItem(
    val budget: Int,
    val createdDate: String,
    val id: Int,
    val income: Int

)

