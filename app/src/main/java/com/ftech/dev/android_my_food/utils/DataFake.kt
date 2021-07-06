package com.ftech.dev.android_my_food.utils

import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.data.model.*

object DataFake {

    fun getBigFoodData(): MutableList<BigFood> {

        val list = mutableListOf<BigFood>()

        val list1 = mutableListOf<Int>(
            R.drawable.r1,
            R.drawable.r11,
            R.drawable.r12
        )

        val list2 = mutableListOf<Int>(
            R.drawable.r21,
            R.drawable.r22,
            R.drawable.r23
        )

        val list3 = mutableListOf<Int>(
            R.drawable.r31,
            R.drawable.r32,
            R.drawable.r33
        )

        val list4 = mutableListOf<Int>(
            R.drawable.r41,
            R.drawable.r42,
            R.drawable.r43
        )
        val list5 = mutableListOf<Int>(
            R.drawable.bbq_1,
            R.drawable.bbq_2,
            R.drawable.bbq_3
        )

        val list6 = mutableListOf<Int>(
            R.drawable.bm_1,
            R.drawable.bm_2,
            R.drawable.bm_3
        )

        val food1 = BigFood(
            "Highsland Coffee", list1,"91 Trần Đại Nghĩa, Bách Khoa, Hai Bà Trưng, Hà Nội, Việt Nam",
            5, "5000", "95", "45","coffee"
        )
        val food2 = BigFood(
            "MC Donal", list2,"2 Phố Hàng Bài, Tràng Tiền, Hoàn Kiếm, Hà Nội, Việt Nam",
            4, "4000", "91", "20","hbg"
        )

        val food3 = BigFood(
            "Vegetable Restaurant", list3,"Gucheng St, Nong'an County, Changchun, Jilin, Trung Quốc",
            4, "1500", "98", "10","vegatable"
        )
        val food4 = BigFood(
            "Wine Cellar Restaurant", list4,"128 Trung Hòa, Yên Hoà, Cầu Giấy, Hà Nội, Việt Nam",
            5, "1000", "98", "10","wine"
        )

        val food5 = BigFood(
            "Deli 4B", list5,"15 Nguyễn Văn Lộc, P. Mộ Lao, Hà Đông, Hà Nội, Việt Nam",
            5, "2000", "98", "10", "bbq"
        )

        val food6 = BigFood(
            "Bread VietNam", list6,"9 Ngõ 4 Ao Sen, P. Mộ Lao, Hà Đông, Hà Nội, Việt Nam",
            5, "2000", "98", "10", "bread"
        )

        list.add(food1)
        list.add(food2)
        list.add(food3)
        list.add(food4)
        list.add(food5)
        list.add(food6)

        return list
    }

    fun getCardData(): MutableList<Card> {
        val list = mutableListOf<Card>()
        val card = Card(R.drawable.card_image_1)
        val card2 = Card(R.drawable.card_image_2)
        val card3 = Card(R.drawable.card_image_3)
        list.add(card)
        list.add(card2)
        list.add(card3)
        list.add(card3)
        list.add(card2)

        return list
    }

    fun getOrderData(): MutableList<Order> {
        val list = mutableListOf<Order>()
        val order1 = Order("Cơm ngon Hà Nội - Hồng Mai", 62000, "ZaloPay", 2, "08, Jun", true)
        val order2 = Order("Bento Delichi - Cơm gà xối mỡ", 44000, "ATM Card", 3, "09, May", true)
        val order3 = Order("Bento Delichi - Cơm gà xối mỡ", 44000, "Tiền mặt", 2, "10, Jun", false)
        val order4 = Order("Cơm ngon Hà Nội - Hồng Mai", 62000, "ZaloPay", 2, "08, Apr", true)
        val order5 = Order("Cơm ngon Hà Nội - Hồng Mai", 33000, "ATM Card", 1, "14, Feb", false)
        val order6 = Order("Jollibee - Hà Nội ", 35000, "ZaloPay", 1, "26, Sep", true)

        list.add(order1)
        list.add(order2)
        list.add(order3)
        list.add(order4)
        list.add(order5)
        list.add(order6)

        return list
    }

    fun getVoucherData(): MutableList<Voucher> {
        val list = mutableListOf<Voucher>()

        val voucher1 = Voucher(
            "MOMO khao 30K nhập MOMOT6", R.drawable.ic_coffee_time,
            "Giảm 30k đơn từ 90k | Nhập mã MOMOT6 | Áp dụng cho thanh toán từ MOMO đến hết 25/06/2021",
            "MOMOT6", "Momo", 30
        )
        val voucher2 = Voucher(
            "Khao 25k nhập ZALOT6", R.drawable.ic_drinking,
            "Giảm 25k đơn từ 50k | Nhập mã MOMOT6 | Áp dụng cho thanh toán từ ZALOPAY đến hết 10/08/2021",
            "ZALOT6", "Zalo", 25
        )
        val voucher3 = Voucher(
            "Cheers Pepsi | Khao tới 50k", R.drawable.ic_tea_time,
            "Khao 55k đơn từ 90k | Nhập mã PEPSI | Áp dụng cho món gắn nhãn Pepsi đến hết 05/07/2021",
            "PEPSI50k", "Pepsi", 55
        )
        val voucher4 = Voucher(
            "Khao bạn mới giảm 40k", R.drawable.ic_play_with_pet,
            "Giảm 35k đơn từ 90k | Nhập mã NEWBIE | Áp dụng cho đơn hàng đầu tiên đến hết 26/09/2021",
            "NEWBIE", "Baemin", 35
        )
        val voucher5 = Voucher(
            "Free ship tới 20k cho mọi quán", R.drawable.ic_guitar,
            "Freeship cả thế giới | Nhập mã FREESHIP | Áp dụng cho mọi thanh toán đến hết 15/10/2021",
            "FREESHIP", "Baemin", 20
        )

        list.add(voucher1)
        list.add(voucher2)
        list.add(voucher3)
        list.add(voucher4)
        list.add(voucher5)

        return list
    }

}