package com.example.testpermisson

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AfterPermissionGranted

enum class Status {
    IDLE, LOADING, LOADED, ERROR
}

data class DataItem(val status: Status, val list: List<String>? = null)

object Manager {

    private val _listLiveData = MutableLiveData<DataItem>()
    val liveLiveData: LiveData<DataItem> get() = _listLiveData

    fun init() {
        Log.d("giangtd", "init: ")

        val status = _listLiveData.value?.status
        if (status == null || status == Status.IDLE || status == Status.ERROR) {
            CoroutineScope(Dispatchers.Main).launch {
                _listLiveData.value = DataItem(Status.LOADING)
                val listData = ArrayList<String>()
                for (i in 0..100) {
                    listData.add(i.toString())
                    _listLiveData.value = DataItem(Status.LOADING, listData)
                    delay(500)
                    Log.d("giangtd", "init: data" + i)
                }

                _listLiveData.value = DataItem(Status.LOADED, listData)
            }
        }
    }
}