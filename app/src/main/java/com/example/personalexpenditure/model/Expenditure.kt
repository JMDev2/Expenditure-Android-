package com.example.personalexpenditure.model

data class Expenditure(
    val createdDate: String,
    val entertainment: Int,
    val food: Int,
    val health: Int,
    val id: Int,
    val postData: PostData,
    val rent: Int,
    val schoolFee: Int,
    val shopping: Int,
    val transport: Int
)