package com.example.personalexpenditure.model

class TotalResponse(
    val userId: String,
    val expenditure: Expenditure,
    val income: Income

)

data class Expenditure(
    val entertainment: Int,
    val food: Int,
    val health: Int,
    val rent: Int,
    val schoolFee: Int,
    val shopping: Int,
    val transport: Int,

    val createdDate: String? = null,
    val total: Int? = null,
    val shoppingPercentage: Double? = null,
    val schoolFeePercentage: Double? = null,
    val rentPercentage: Double? = null,
    val healthPercentage: Double? = null,
    val foodPercentage: Double? = null,
    val entertainmentPercentage: Double? = null,
    val transportPercentage: Double? = null

)

data class Income(
    val income: Int,
    val budget: Int,
    val createdDate: String? = null
)