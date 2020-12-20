package com.binesh.wiprodemo.viewModel

import androidx.lifecycle.LiveData
import com.binesh.wiprodemo.base.BaseViewModel
import com.binesh.wiprodemo.enums.ManageStateList
import com.binesh.wiprodemo.model.Row
import com.binesh.wiprodemo.repository.CountryFeedRepository

class FeedViewModel(var repository: CountryFeedRepository):BaseViewModel() {

    //View model class for providing data to view

    fun getFeeds(): LiveData<ManageStateList<Row>> {
        return repository.loadFeeds()
    }
}