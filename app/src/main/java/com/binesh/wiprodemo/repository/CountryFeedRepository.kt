package com.binesh.wiprodemo.repository

import androidx.lifecycle.MutableLiveData
import com.binesh.wiprodemo.apis.APIs
import com.binesh.wiprodemo.apis.ApiService
import com.binesh.wiprodemo.enums.ManageStateList
import com.binesh.wiprodemo.enums.ManageStatusEnum
import com.binesh.wiprodemo.helper.NetworkStatusHelper
import com.binesh.wiprodemo.model.Row
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CountryFeedRepository(var apiService: ApiService) {

    fun loadFeeds(): MutableLiveData<ManageStateList<Row>>
    {

        val manageState: MutableLiveData<ManageStateList<Row>> = MutableLiveData()

        if(NetworkStatusHelper.isNetworkAvailable()){


            manageState.value= ManageStateList(ManageStatusEnum.LOADING)
            apiService.createService(APIs::class.java)
                .countryFeed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({response->
                    manageState.value= ManageStateList(ManageStatusEnum.LOADING_DISMISS)
                    if (!response.rows.isNullOrEmpty()) {
                        manageState.value = ManageStateList(status = ManageStatusEnum.SUCCESS,dataList = response.rows,title = response.title)
                    }else{
                        manageState.value= ManageStateList(status = ManageStatusEnum.NO_DATA_FOUND)
                    }

                }, {
                    manageState.value= ManageStateList(status = ManageStatusEnum.FAILED)
                })

        }else{
            manageState.value= ManageStateList(status = ManageStatusEnum.NO_INTERNET_CONNECTION)
        }

        return manageState
    }
}