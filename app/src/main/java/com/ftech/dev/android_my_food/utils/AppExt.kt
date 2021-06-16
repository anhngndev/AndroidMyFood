package com.ftech.dev.android_my_food.utils

import android.app.Application
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ftech.dev.android_my_food.data.model.Card
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.data.model.FoodBig
import com.makeramen.roundedimageview.RoundedImageView

private var application : Application?=null

fun getApplication() = application!!

fun Application.initApplication() {
    application = this
}


@BindingAdapter("riv_imageFood")
fun RoundedImageView.setRoundImage(item: Food) {
    setImageResource(item.image)
    // TODO: 16/06/2021 anhnd fix
}

@BindingAdapter("imageFoodBig")
fun RoundedImageView.setImageBigFood(item: FoodBig) {
    setImageResource(item.image[1])
}

@BindingAdapter("iv_image")
fun ImageView.setImage(item: Card) {
    setImageResource(item.image)
}

@BindingAdapter("iv_image_1")
fun ImageView.setImage(item: Int) {
    setImageResource(item)
}



fun <T> Fragment.observer(liveData: LiveData<T>, onChange: (T?) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer(onChange))
}

@BindingAdapter("deBounceClick")
fun View.onDeBoundClick(listener: View.OnClickListener?) {
    listener?.let {
        this.onDebouncedClick { _ ->
            it.onClick(this)
        }
    }
}

private const val DEFAULT_DEBOUNCE_INTERVAL = 400L

fun View.onDebouncedClick(
    delayBetweenClicks: Long = DEFAULT_DEBOUNCE_INTERVAL,
    click: (view: View) -> Unit
) {
    setOnClickListener(object : DebouncedOnClickListener(delayBetweenClicks) {
        override fun onDebouncedClick(v: View) = click(v)
    })
}

abstract class DebouncedOnClickListener(
    private val delayBetweenClicks: Long = DEFAULT_DEBOUNCE_INTERVAL
) : View.OnClickListener {
    private var lastClickTimestamp = -1L

    @Deprecated(
        message = "onDebouncedClick should be overridden instead.",
        replaceWith = ReplaceWith("onDebouncedClick(v)")
    )

    override fun onClick(v: View) {
        val now = System.currentTimeMillis()
        if (lastClickTimestamp == -1L || now >= (lastClickTimestamp + delayBetweenClicks)) {
            onDebouncedClick(v)
        }
        lastClickTimestamp = now
    }

    abstract fun onDebouncedClick(v: View)
}