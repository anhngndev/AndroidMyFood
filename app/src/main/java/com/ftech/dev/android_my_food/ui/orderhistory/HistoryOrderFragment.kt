package com.ftech.dev.android_my_food.ui.orderhistory

import androidx.fragment.app.activityViewModels
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.data.source.local.OrderEntity
import com.ftech.dev.android_my_food.databinding.FragmentHistoryOrderBinding
import com.ftech.dev.android_my_food.ui.cart.CartViewModel
import com.ftech.dev.android_my_food.utils.observer


class HistoryOrderFragment : BaseFragment<FragmentHistoryOrderBinding>(),
    OrderAdapter.OrderListener {

    private lateinit var orderAdapter: OrderAdapter
    private var orderList = mutableListOf<OrderEntity>()
    private val cartViewModel : CartViewModel by activityViewModels()

    override fun isCanBackPress() = true

    override fun getLayoutId() = R.layout.fragment_history_order

    override fun initView() {

        observer(cartViewModel.liveOrders){
            orderAdapter.list = it!!
        }

        orderAdapter = OrderAdapter()

        orderAdapter.list = orderList
        orderAdapter.callBack = this

        binding.rcOrder.adapter = orderAdapter

    }

    override fun setAction() {
        binding.ivBack.setOnClickListener {
            onBackPress()
        }
    }

    override fun onItemClick(index: Int, item: OrderEntity) {


    }


}