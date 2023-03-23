package com.example.personalexpenditure.model

data class Expenditure(

    val entertainment: Int,
    val food: Int,
    val health: Int,
    val postData: PostData,
    val rent: Int,
    val schoolFee: Int,
    val shopping: Int,
    val transport: Int,
    val id: Int? = null,
    val createdDate: String? = null
)