package com.ftech.dev.android_my_food.base

class Test1 {
}

//class MyClass(val doThis: () -> Unit, val doThat: (number: Int) -> Unit = {}) {
//
//    fun someFunction() {
//        doThis()
//        doThat(12)
//    }
//
//
//    var sum = { 1 +1}
//}
//
//class UserClass {
//    val myClass = MyClass(::onClickA, ::onClickB)
//
//    private fun onClickA() {
//    }
//
//    private fun onClickB(number: Int) {
//    }
//}


internal class MyClass(private val listener: Listener) {
    fun someFunction() {
        listener.doThis()
        listener.doThat(12)
    }

    internal interface Listener {
        fun doThis()
        fun doThat(number: Int)
    }
}

internal class UserClass {
    var listener: MyClass.Listener = object : MyClass.Listener {
        override fun doThis() {

        }
        override fun doThat(number: Int) {

        }
    }
    val myClass = MyClass(listener)


}