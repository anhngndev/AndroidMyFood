package com.ftech.dev.android_my_food.ui.cart

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ViewFlipper
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.data.source.local.ItemInCartEntity
import com.ftech.dev.android_my_food.data.source.local.SearchEntity
import com.ftech.dev.android_my_food.databinding.FragmentCartBinding
import com.ftech.dev.android_my_food.ui.search.RecentSearchAdapter
import com.ftech.dev.android_my_food.ui.search.SearchViewModel
import com.ftech.dev.android_my_food.utils.observer
import java.util.*

class CartFragment : BaseFragment<FragmentCartBinding>(), CartAdapter.CartListener {
    private val TAG = "CartFragment"
    private val cartViewModel: CartViewModel by activityViewModels()
    private var cartAdapter = CartAdapter()
    var list = mutableListOf<ItemInCartEntity>()
    var touchHelper : ItemTouchHelper? = null

    override fun getLayoutId() = R.layout.fragment_cart

    override fun isCanBackPress() = true

    override fun initBinding() {
        binding.viewModel = cartViewModel
    }

    override fun initView() {

        cartViewModel.listItemInCartLiveData.observe(viewLifecycleOwner){
            it?.let { list ->
                cartAdapter.list = list
            }
        }

//        cách viết t2
//        observer(cartViewModel.listItemInCartLiveData) {
//            it?.let { list ->
//                cartAdapter.list = list
//            }
//        }

        cartAdapter.callBack = this
        binding.rcItemInCart.adapter = cartAdapter

//
        touchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
                override fun onMove(
                    p0: RecyclerView,
                    p1: RecyclerView.ViewHolder,
                    p2: RecyclerView.ViewHolder
                ): Boolean {
                    val sourcePosition = p1.adapterPosition
                    val targetPosition = p2.adapterPosition
                    Collections.swap(cartAdapter.list,sourcePosition,targetPosition)
                    binding.rcItemInCart.adapter?.notifyItemMoved(sourcePosition,targetPosition)
                    return true
                }

                override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {

                }

            })

        touchHelper?.attachToRecyclerView(binding.rcItemInCart)
    }

    override fun setAction() {
        binding.ivBack.setOnClickListener {
            onBackPress()
        }

        binding.tvOrder.setOnClickListener {
            cartViewModel.deleteAll()
            findNavController().popBackStack(R.id.homeFragment,false)
        }
        binding.tvAddPromo.setOnClickListener {

        }
        binding.tvDirect.setOnClickListener {

        }
        binding.tvAddDetailAddress.setOnClickListener {

        }
        binding.tvEdit.setOnClickListener {

        }
    }

    override fun onItemClick(index: Int, item: ItemInCartEntity) {

    }

    override fun onDeleteItem(item: ItemInCartEntity) {
        cartViewModel.delete(item)
        cartViewModel.liveItemInCart.value = item
        cartViewModel.downAmount()
    }

}