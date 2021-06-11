package com.ftech.dev.android_my_food.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ftech.dev.android_my_food.DetailFoodViewModel
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

    private lateinit var cardAdapter: CardAdapter
    private var cardList = mutableListOf<Card>()

    private lateinit var bigAdapter: FoodBigAdapter
    private var bigFoodList = mutableListOf<FoodBig>()

    private val viewModel: DetailFoodViewModel by activityViewModels()


    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation)
        navBar.visibility = View.VISIBLE

        initView(view)
        setAction()
    }

    private fun setAction() {


    }

    private fun initView(view: View) {
        foodList = DataFake.getFoodData()
        cardList = DataFake.getCardData()
        bigFoodList = DataFake.getBigFoodData()

        val layoutManagerFood = StaggeredGridLayoutManager(1, RecyclerView.VERTICAL)
        val layoutManagerCard = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
        val layoutManagerBigFood = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)

//        foodAdapter = FoodAdapter(foodList, this)
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

        cardAdapter = CardAdapter(cardList, this)

        binding.cardRecyclerview.layoutManager = layoutManagerCard
        binding.cardRecyclerview.adapter = cardAdapter




    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

    }

    override fun onItemClick(index: Int, item: Food) {
        Log.d(TAG, "onItemClick: ")
        viewModel.liveFood.value = (item)
        findNavController().navigate(R.id.action_homeFragment_to_customBottomSheetDialogFragment)

    }

    override fun onItemClick(index: Int, item: Card) {


    }

    override fun onItemClick(index: Int, item: FoodBig) {
        Log.d(TAG, "onItemClick: ")
    }
}