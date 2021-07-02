package com.ftech.dev.android_my_food.ui.search

import android.annotation.SuppressLint
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ftech.dev.android_my_food.FoodDetailViewModel
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.data.model.Food
import com.ftech.dev.android_my_food.data.source.local.SearchEntity
import com.ftech.dev.android_my_food.databinding.FragmentSearchBinding
import com.ftech.dev.android_my_food.ui.home.FoodAdapterHor
import com.ftech.dev.android_my_food.ui.home.FoodAdapterVer
import com.ftech.dev.android_my_food.utils.hideKeyboard
import com.ftech.dev.android_my_food.utils.observer
import java.util.*


class SearchFragment : BaseFragment<FragmentSearchBinding>(),
    RecentSearchAdapter.RecentSearchListener,
    FoodAdapterVer.FoodListener {

    private val TAG = "SearchFragment"
    private val searchViewModel: SearchViewModel by viewModels()
    private val foodsViewModel: FoodDetailViewModel by activityViewModels()
    private var foodAdapter = FoodAdapterVer()
    private var searchAdapter = RecentSearchAdapter()
    private var handler = Handler()

    var list = listOf<SearchEntity>()
    var foodsTemp = mutableListOf<Food>()

    override fun isCanBackPress() = true

    override fun getLayoutId() = R.layout.fragment_search

    override fun initBinding() {
        binding.searchViewModel = searchViewModel
    }

    override fun setAction() {

        binding.edtSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                if (!searchViewModel.keySearch.value.toString().equals("")) {
                    val searchEntity =
                        searchViewModel.keySearch.value?.let { SearchEntity(content = it) }
                    searchEntity?.let { searchViewModel.insert(searchEntity = it) }
                }
                return@OnEditorActionListener true
            }
            false
        })

        binding.edtSearch.setOnFocusChangeListener { view, b ->
        }

        binding.ivBack.setOnClickListener {
            onBackPress()
        }
        binding.ivClear.setOnClickListener {
            searchViewModel.keySearch.value = ""
        }
    }

    override fun initView() {
        foodAdapter.list = mutableListOf()
        foodAdapter.callBack = this
        binding.rcFood.adapter = foodAdapter

        observer(searchViewModel.listSearchLiveData2) {
            it?.let { list ->
                searchAdapter.list = list
            }
        }
        observer(searchViewModel.keySearch) {
            performSearch(it!!)
            Log.d(TAG, "initView: $it")
        }

        binding.rcRecentSearch.adapter = searchAdapter
        searchAdapter.callback = this

        binding.edtSearch.setOnFocusChangeListener { view, b ->

        }

    }

    @SuppressLint("DefaultLocale")
    private fun performSearch(keySearch: String) {
        foodsTemp = mutableListOf()
        if (keySearch != "") {
            for (d in foodsViewModel.foodsLiveData.value!!) {
                if (d.name.lowercase(Locale.getDefault())
                        .contains(keySearch.lowercase(Locale.getDefault()))
                ) {
                    foodsTemp.add(d)
                    foodAdapter.list = foodsTemp
                }
            }
            binding.amount = foodsTemp.size
        } else {
            binding.amount = 0
            foodAdapter.list = foodsTemp
        }
    }

    override fun onItemClick(item: SearchEntity) {
        searchViewModel.keySearch.value = item.content
        binding.view.visibility = View.VISIBLE
        handler.postDelayed(Runnable {
            binding.view.visibility = View.GONE
        }, 500)
    }

    override fun onItemClick(index: Int, item: Food) {
        foodsViewModel.liveFood.value = (item)
        foodsViewModel.amount.value = 1
        foodsViewModel.total.value = item.getPriceToInt()
        findNavController().navigate(R.id.action_searchFragment_to_detailFoodBottomSheet)
    }

}
