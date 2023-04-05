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
    var createdDate: String? = null
){
    constructor() : this(
        0,
        0,
        0,
        null,
        0,
        0,
        0,
        0,
        null,
        null
    )
}
//) {
//    constructor() : this(null, null, null, null, null, null, null, null, null, null)
//
//}
//{
//    data class Builder(
//        var entertainment: Int? = null,
//        var food: Int? = null,
//        var health: Int? = null,
//        var postData: PostData? = null,
//        var rent: Int? = null,
//        var schoolFee: Int? = null,
//        var shopping: Int? = null,
//        var transport: Int? = null,
//        val id: Int? = null,
//        val createdDate: String? = null
//
//    ){
//        fun entertainment(entertainment: Int) = apply {
//            this.entertainment = entertainment
//        }
//        fun food(food: Int)= apply{
//            this.food = food
//        }
//        fun health(health: Int)= apply{
//            this.health = health
//        }
//
//        fun rent(rent: Int)= apply{
//            this.rent = rent
//        }
//        fun schoolFee(schoolFee: Int)= apply{
//            this.schoolFee = schoolFee
//        }
//        fun shopping(shopping: Int)= apply{
//            this.shopping = shopping
//        }
//        fun transport(transport: Int)= apply{
//            this.transport = transport
//        }
//        fun postData(postData: PostData) = apply {
//            this.postData = postData
//        }
//        fun build(): Expenditure{
//            return Expenditure(entertainment, food, health, postData, rent, schoolFee, shopping, transport)
//        }
//    }
//}