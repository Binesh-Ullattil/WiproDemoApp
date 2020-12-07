package com.binesh.wiprodemo.enums

class ManageStateList<T> (
    val status: ManageStatusEnum,
    val title:String?=null,
    val dataList:MutableList<T>?=null
){

}