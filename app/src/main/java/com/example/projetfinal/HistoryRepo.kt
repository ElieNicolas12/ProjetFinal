package com.example.projetfinal

import androidx.lifecycle.MutableLiveData
import com.example.projetfinal.Model.TempData

class HistoryRepo {

    private var instance: HistoryRepo? = null

    var dataList = MutableLiveData<ArrayList<TempData>>()

    private fun HistoryRepo() {}

    fun getInstance(): HistoryRepo? {
        if (instance == null) {
            instance = com.example.projetfinal.HistoryRepo()
        }
        return instance
    }
    fun addHistory(tempData: TempData?) {
        if (dataList.value != null) {
            dataList.value!!.add(tempData!!)
            dataList.postValue(dataList.value)
        }
    }
}