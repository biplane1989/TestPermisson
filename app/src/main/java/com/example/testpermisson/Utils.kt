package com.example.testpermisson

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import pub.devrel.easypermissions.requestPermission


const val PERMISSION_RC = 1
val permissionList = listOf(android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.READ_EXTERNAL_STORAGE)

fun Fragment.request() {

    this.requestPermission(PERMISSION_RC, *permissionList.toTypedArray())
}

fun Activity.request() {
    val permissionList = listOf(android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.READ_EXTERNAL_STORAGE)
    requestPermission(PERMISSION_RC, *permissionList.toTypedArray())
}