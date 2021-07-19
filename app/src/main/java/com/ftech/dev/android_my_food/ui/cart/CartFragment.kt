package com.ftech.dev.android_my_food.ui.cart

import android.os.Build
import android.os.Handler
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.data.source.local.ItemInCartEntity
import com.ftech.dev.android_my_food.databinding.FragmentCartBinding
import com.ftech.dev.android_my_food.utils.observer
import com.ftech.dev.android_my_food.utils.onDebouncedClick
import java.util.*

class CartFragment : BaseFragment<FragmentCartBinding>(), CartAdapter.CartListener {
    private val TAG = "CartFragment"
    private val cartViewModel: CartViewModel by activityViewModels()
    private var cartAdapter = CartAdapter()
    private var handler = Handler()
    var list = mutableListOf<ItemInCartEntity>()
    var touchHelper: ItemTouchHelper? = null

    override fun getLayoutId() = R.layout.fragment_cart

    override fun isCanBackPress() = true

    override fun initBinding() {
        binding.viewModel = cartViewModel
    }

    override fun initView() {

        cartViewModel.listItemInCartLiveData.observe(viewLifecycleOwner) {
            it?.let { list ->
                cartAdapter.list = list
            }
        }

        observer(cartViewModel.amount){
            cartViewModel.isAmountValid()
        }

//        cách viết t2
//        observer(cartViewModel.listItemInCartLiveData) {
//            it?.let { list ->
//                cartAdapter.list = list
//            }
//        }

        cartAdapter.callBack = this
        binding.rcItemInCart.adapter = cartAdapter

        touchHelper =
            ItemTouchHelper(object :
                ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
                override fun onMove(
                    p0: RecyclerView,
                    p1: RecyclerView.ViewHolder,
                    p2: RecyclerView.ViewHolder
                ): Boolean {
                    val sourcePosition = p1.adapterPosition
                    val targetPosition = p2.adapterPosition
                    Collections.swap(cartAdapter.list, sourcePosition, targetPosition)
                    binding.rcItemInCart.adapter?.notifyItemMoved(sourcePosition, targetPosition)
                    return true
                }

                override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {

                }

            })

        touchHelper?.attachToRecyclerView(binding.rcItemInCart)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setAction() {
        binding.ivBack.onDebouncedClick {
            onBackPress()
        }

        binding.tvOrder.onDebouncedClick {
            if (cartViewModel.checkOut()) {
                findNavController().popBackStack(R.id.homeFragment, false)
            } else{
                binding.tvNotifyAdd.visibility = View.VISIBLE
                handler.postDelayed(Runnable {
                    binding.tvNotifyAdd.visibility = View.GONE
                }, 1500)
            }
        }
        binding.tvAddPromo.onDebouncedClick {

        }
        binding.tvDirect.onDebouncedClick {

        }
        binding.tvAddDetailAddress.onDebouncedClick {

        }
        binding.tvEdit.onDebouncedClick {

        }
    }

    override fun onItemClick(index: Int, item: ItemInCartEntity) {

    }

    override fun onDeleteItem(item: ItemInCartEntity) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("TastyVN")
        builder.setMessage("Remove ${item.nameItem.toString()} ?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                cartViewModel.delete(item)
                cartViewModel.liveItemInCart.value = item
                cartViewModel.downAmount()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.cancel()
            }
        val alert = builder.create()
        alert.show()
    }
}