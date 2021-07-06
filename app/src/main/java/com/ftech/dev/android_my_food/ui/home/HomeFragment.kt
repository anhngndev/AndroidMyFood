package com.ftech.dev.android_my_food.ui.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ftech.dev.android_my_food.FoodDetailViewModel
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.UserInforViewModel
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.data.model.Card
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.data.model.BigFood
import com.ftech.dev.android_my_food.data.model.Voucher
import com.ftech.dev.android_my_food.data.source.remote.HttpRequestTask
import com.ftech.dev.android_my_food.databinding.FragmentHomeBinding
import com.ftech.dev.android_my_food.ui.cart.CartViewModel
import com.ftech.dev.android_my_food.ui.voucher.VoucherAdapter
import com.ftech.dev.android_my_food.utils.DataFake
import com.ftech.dev.android_my_food.utils.observer
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding>(), FoodAdapterHor.FoodListener,
    CardAdapter.CardListener, FoodBigAdapter.FoodBigListener, VoucherAdapter.VoucherListener,
    HttpRequestTask.Callback {

    private var touchHelper: ItemTouchHelper? = null
    private val TAG = "HomeFragment"
    private lateinit var foodAdapter: FoodAdapterHor
    private lateinit var voucherAdapter: VoucherAdapter
    private var voucherList = mutableListOf<Voucher>()
    private lateinit var bigAdapter: FoodBigAdapter
    private var bigFoodList = mutableListOf<BigFood>()
    private val detailViewModel: FoodDetailViewModel by activityViewModels()
    private val cartViewModel: CartViewModel by activityViewModels()
    private val userInforViewModel: UserInforViewModel by activityViewModels()

    var handler = Handler()

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initBinding() {
        binding.cartViewModel = cartViewModel
        binding.userInforViewModel = userInforViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observer(cartViewModel.isOrdering) {
            if (!it!!) {
                binding.tvCompleteOrder.visibility = View.VISIBLE
                handler.postDelayed(Runnable {
                    binding.tvCompleteOrder.visibility = View.GONE
                }, 2500)
                cartViewModel.isOrdering.value = true
            }
        }
        handler.postDelayed(Runnable {
            binding.view.visibility = View.GONE
        }, 1000)

    }

    override fun setAction() {
        binding.view.setOnClickListener {

        }
        binding.ivNotify.setOnClickListener {
        }

        binding.tvUsername.setOnClickListener {
        }

        binding.ivCart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }

        binding.tvTitle1.setOnClickListener {

        }

        binding.ivMenu.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        binding.tvSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        binding.tvMore.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_allFoodFragment)
        }
    }

    override fun initView() {
        detailViewModel.getFoods()

        detailViewModel.foodsLiveData.observe(viewLifecycleOwner) {
            Log.d(TAG, "initView: ${it.size}")
            foodAdapter = FoodAdapterHor()
            foodAdapter.list = detailViewModel.foodsLiveData.value!!
            foodAdapter.callBack = this
            binding.popularRecyclerview.adapter = foodAdapter
        }

        voucherList = DataFake.getVoucherData()
        bigFoodList = DataFake.getBigFoodData()

        val layoutManagerVoucher = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        val layoutManagerBigFood = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        val snapHelper = LinearSnapHelper()

        snapHelper.attachToRecyclerView(binding.slideRecycler)

        voucherAdapter = VoucherAdapter()
        voucherAdapter.callBack = this
        voucherAdapter.list = voucherList

        bigAdapter = FoodBigAdapter()
        bigAdapter.callBack = this
        bigAdapter.list = bigFoodList

        binding.rvVoucher.layoutManager = layoutManagerVoucher
        binding.rvVoucher.adapter = voucherAdapter

        binding.slideRecycler.layoutManager = layoutManagerBigFood
        binding.slideRecycler.adapter = bigAdapter

        touchHelper?.attachToRecyclerView(binding.popularRecyclerview)
    }

    override fun onItemClick(index: Int, item: Food) {
        detailViewModel.liveFood.value = (item)
        detailViewModel.amount.value = 1
        detailViewModel.total.value = item.getPriceToInt()
        findNavController().navigate(R.id.action_homeFragment_to_oderDetailFragment)
    }

    override fun onItemClick(index: Int, item: Card) {
    }

    override fun onItemClick(index: Int, item: BigFood) {
        detailViewModel.liveBigFood.value = (item)
        findNavController().navigate(R.id.action_homeFragment_to_bigFoodDetailFragment)
    }

    override fun onItemClick(index: Int, item: Voucher) {
        detailViewModel.liveVoucher.value = item
        findNavController().navigate(R.id.action_homeFragment_to_voucherFragment)

    }

    override fun onSuccess(result: String?) {
        Log.e(TAG, result!!)
    }

    override fun onFailed(error: Exception?) {
        Log.e(TAG, "not connection")
    }

    fun completeOrder() {
    }

}