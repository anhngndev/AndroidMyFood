package com.ftech.dev.android_my_food.data.model

class BigFood(
    var name:String, // name
    var image: MutableList<Int>, // 1 list ảnh
    var rate: Int, // hàm lượng % (vd: 90, 80, 85...)
    var verified :String, // 1 số bất kỳ (ví dụ 5000, 3000)
    var range :String, // khoảng cách - tính theo km (vd: 9)
    var timer :String // thời gian (vd: 45)
) {
}