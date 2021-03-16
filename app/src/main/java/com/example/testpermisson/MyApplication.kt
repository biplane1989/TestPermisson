package com.example.testpermisson

import android.app.Activity
import android.app.Application
import pub.devrel.easypermissions.hasPermissions


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (this.hasPermissions(permissionList)) {
            Manager.init()
        }

    }
}