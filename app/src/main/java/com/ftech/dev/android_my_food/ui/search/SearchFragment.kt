package com.ftech.dev.android_my_food.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.base.BaseFragment
import com.ftech.dev.android_my_food.data.source.local.SearchEntity
import com.ftech.dev.android_my_food.databinding.FragmentSearchBinding
import com.ftech.dev.android_my_food.utils.observer

class SearchFragment : BaseFragment<FragmentSearchBinding>(),
    RecentSearchAdapter.RecentSearchListener {

        private val TAG = "SearchFragment"
    private val searchViewModel: SearchViewModel by viewModels()
    private var searchAdapter = RecentSearchAdapter()
    var list = listOf<SearchEntity>()

    override fun getLayoutId() = R.layout.fragment_search

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setAction()

    }

    override fun setAction() {
        binding.cardFilterIcon.setOnClickListener {
            val item = binding.searchEdittext.text.toString()
            if(!item.equals("")){
                val searchEntity = SearchEntity(content = item)
                searchViewModel.insert(searchEntity)
            }
        }

        binding.searchEdittext.setOnFocusChangeListener { view, b ->
//            view.setBackgroundResource(R.drawable.border_10_dark_gray_fill)
        }

    }

    override fun initView() {
        observer(searchViewModel.listSearchLiveData2) {
            it?.let { list ->
                searchAdapter.list = list
            }
        }
        binding.rcRecentSearch.adapter = searchAdapter
        searchAdapter.callback = this
    }

    override fun onItemClick(index: Int, item: SearchEntity) {


    }


}
