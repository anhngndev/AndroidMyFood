package com.ftech.dev.android_my_food.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ftech.dev.android_my_food.FoodDetailViewModel
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.data.model.Card
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.data.model.FoodBig
import com.ftech.dev.android_my_food.databinding.FragmentHomeBinding
import com.ftech.dev.android_my_food.utils.DataFake
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : BaseFragment<FragmentHomeBinding>(), FoodAdapter.FoodListener,
    CardAdapter.CardListener, FoodBigAdapter.FoodBigListener {

    private val TAG = "HomeFragment"

    private lateinit var foodAdapter: FoodAdapter
    private var foodList = mutableListOf<Food>()

    private lateinit var bigAdapter: FoodBigAdapter
    private var bigFoodList = mutableListOf<FoodBig>()

    private val detailViewModel: FoodDetailViewModel by activityViewModels()
    var handler = Handler()

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler.postDelayed(Runnable {
            binding.view.visibility = View.GONE
        }, 800)

        setStateBottomNavigation(true)

    }

    override fun setAction() {
        binding.ivNotify.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_historyOrderFragment)
        }
        binding.tvUsername.setOnClickListener {

        }
        binding.tvTitle1.setOnClickListener {

        }


    }

    override fun initView() {
        foodList = DataFake.getFoodData()
//        cardList = DataFake.getCardData()
        bigFoodList = DataFake.getBigFoodData()

        val layoutManagerFood = StaggeredGridLayoutManager(1, RecyclerView.VERTICAL)
        val layoutManagerCard = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        val layoutManagerBigFood = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.slideRecycler)

        foodAdapter = FoodAdapter()
        bigAdapter = FoodBigAdapter()

        foodAdapter.callBack = this
        foodAdapter.list = foodList

        bigAdapter.callBack = this
        bigAdapter.list = bigFoodList

        binding.popularRecyclerview.layoutManager = layoutManagerFood
        binding.popularRecyclerview.adapter = foodAdapter

        binding.slideRecycler.layoutManager = layoutManagerBigFood
        binding.slideRecycler.adapter = bigAdapter

//        cardAdapter = CardAdapter(cardList, this)
//        binding.cardRecyclerview.layoutManager = layoutManagerCard
//        binding.cardRecyclerview.adapter = cardAdapter

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }


    override fun onItemClick(index: Int, item: Food) {
        Log.d(TAG, "onItemClick: ")
        detailViewModel.liveFood.value = (item)
        findNavController().navigate(R.id.action_homeFragment_to_customBottomSheetDialogFragment)

    }

    override fun onItemClick(index: Int, item: Card) {
    }

    override fun onItemClick(index: Int, item: FoodBig) {
        Log.d(TAG, "onItemClick: ")
        detailViewModel.liveBigFood.value = (item)
        findNavController().navigate(R.id.action_homeFragment_to_bigFoodDetailFragment)
    }
}