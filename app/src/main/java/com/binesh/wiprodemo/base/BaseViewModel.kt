package com.binesh.wiprodemo.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binesh.wiprodemo.helper.ToastState
import com.binesh.wiprodemo.helper.ToastStateEnum

open class BaseViewModel: ViewModel() {

    var toastState = MutableLiveData<ToastState>()

    fun setInfoToast(messageResource: Int) {
        toastState.value = ToastState(ToastStateEnum.INFO, messageResource)
    }

    fun setSuccessToast(messageResource: Int) {
        toastState.value = ToastState(ToastStateEnum.SUCCESS, messageResource)
    }

    fun setFailureToast(messageResource: Int) {
        toastState.value = ToastState(ToastStateEnum.Failure, messageResource)
    }
}