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

        observer(searchViewModel.listSearchLiveData) {
            it?.let { list ->
                searchAdapter.list = list
            }
        }

        binding.rcRecentSearch.adapter = searchAdapter
        searchAdapter.callback = this

        binding.cardFilterIcon.setOnClickListener {
            val item = binding.searchEdittext.text.toString()
            val searchEntity = SearchEntity(content = item)
            searchViewModel.insert(searchEntity)
        }
    }

    override fun onItemClick(index: Int, item: SearchEntity) {

    }


}
