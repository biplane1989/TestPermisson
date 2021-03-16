package com.example.testpermisson

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.hasPermissions

class MainViewModel : ViewModel() {

    fun onClicked(context: Context, dataItem: DataItem?) {
        if (context.hasPermissions(permissionList)) {
            if (dataItem?.status == Status.LOADING) {
                Toast.makeText(context, "Loading ...", Toast.LENGTH_SHORT).show()
            }
        } else {
            (context as MainActivity).request()
        }
    }

}