package com.example.personalexpenditure.model


data class Expenditure(
    var entertainment: Int,
    var food: Int,
    var health: Int,
    var postData: PostData?,
    var rent: Int,
    var schoolFee: Int,
    var shopping: Int,
    var transport: Int,
    var id: Int? = null,
    var createdDate: String? = null,
    val total: Int? = null,

)