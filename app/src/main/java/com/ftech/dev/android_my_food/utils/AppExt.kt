package com.ftech.dev.android_my_food.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ftech.dev.android_my_food.data.model.BigFood
import com.ftech.dev.android_my_food.data.model.Card
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.data.model.Voucher
import com.ftech.dev.android_my_food.ui.cart.CartViewModel
import com.makeramen.roundedimageview.RoundedImageView

private var application: Application? = null

fun getApplication() = application!!

fun Application.initApplication() {
    application = this
}


@BindingAdapter("riv_imageFood")
fun RoundedImageView.setRoundImage(item: Food) {
    Glide.with(this).load(item.image).into(this)
}

@BindingAdapter("tv_visibility")
fun TextView.setVisibility(viewModel: CartViewModel) {
    visibility = if (viewModel.amount.value!! > 0)
        View.VISIBLE
    else View.GONE
}

@BindingAdapter("tv_visibility_by_key")
fun TextView.setVisibilityV3(string: String) {
    visibility = if (string.equals(""))
        View.GONE
    else View.VISIBLE
}

@BindingAdapter("tv_set_text_by_amount")
fun TextView.setTextByAmount(amount: Int) {
    if (amount != 0) {
        this.text = "$amount món ăn được tìm thấy"
    } else this.text = "Tìm kiếm gần đây"
}

@BindingAdapter("rv_visibility")
fun RecyclerView.setVisibility(key: String) {
    visibility = if (key.equals(""))
        View.VISIBLE
    else View.GONE
}

@BindingAdapter("rv_visibility_2")
fun RecyclerView.setVisibilityV2(key: String) {
    visibility = if (!key.equals(""))
        View.VISIBLE
    else View.GONE
}

@BindingAdapter("tv_visibility_2")
//fun TextView.setVisibilityV2(viewModel: CartViewModel) {
//    visibility = if (viewModel.amount.value!! > 0)
//        View.GONE
//    else View.VISIBLE
//}

fun TextView.setVisibilityV2(value: Int) {
    visibility = if (value > 0) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("fl_visibility")
fun FrameLayout.setVisibility(value: Boolean) {
    visibility = if (value) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("riv_imageVoucher")
fun RoundedImageView.setImageVoucher(item: Voucher) {
    Glide.with(this).load(item.image).into(this)
}


@BindingAdapter("imageFoodBig")
fun RoundedImageView.setImageBigFood(item: BigFood) {
    Glide.with(this).load(item.image[0]).into(this)
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

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
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