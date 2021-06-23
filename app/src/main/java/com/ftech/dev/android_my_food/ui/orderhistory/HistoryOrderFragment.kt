package com.ftech.dev.android_my_food.ui.orderhistory

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.data.model.Order
import com.ftech.dev.android_my_food.databinding.FragmentHistoryOrderBinding
import com.ftech.dev.android_my_food.ui.home.FoodAdapter
import com.ftech.dev.android_my_food.utils.DataFake


class HistoryOrderFragment : BaseFragment<FragmentHistoryOrderBinding>(),
    OrderAdapter.OrderListener {

    private lateinit var orderAdapter: OrderAdapter
    private var orderList = mutableListOf<Order>()

    override fun isCanBackPress() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        setStateBottomNavigation(false)

    }

    override fun getLayoutId() = R.layout.fragment_history_order

    override fun initView() {

        orderList = DataFake.getOrderData()
        val layoutManagerOrder = StaggeredGridLayoutManager(1, RecyclerView.VERTICAL)
        orderAdapter = OrderAdapter()

        orderAdapter.list = orderList
        orderAdapter.callBack = this

        binding.rcOrder.layoutManager = layoutManagerOrder
        binding.rcOrder.adapter = orderAdapter


    }

    override fun setAction() {
        binding.ivBack.setOnClickListener {
            onBackPress()
        }
    }

    override fun onItemClick(index: Int, item: Order) {


    }


}