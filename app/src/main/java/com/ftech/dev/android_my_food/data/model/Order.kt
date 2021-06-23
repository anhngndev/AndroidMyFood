package com.ftech.dev.android_my_food.data.model

class Order(
    var name:String,
    var total:Int,
    var paymentMethod:String,
    var amount:Int,
    var date:String,
    var status:Boolean
) {

    fun getFormatStatus():String{
        if (status) return "Đã giao"
        return "Đã hủy"
    }
}