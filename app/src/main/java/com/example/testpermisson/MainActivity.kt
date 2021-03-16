package com.example.testpermisson

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.hasPermissions
import pub.devrel.easypermissions.onRequestPermissionsResulted
import pub.devrel.easypermissions.requestPermission


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var textView: TextView
    lateinit var textViewsize: TextView
    lateinit var process: ProgressBar

    val listObserver = Observer<DataItem> {
        if (it.status != Status.LOADED) {
            process.visibility = View.VISIBLE

        } else {
            process.visibility = View.INVISIBLE
            Toast.makeText(this, "Loaded ", Toast.LENGTH_SHORT).show()
        }
        textViewsize.text = it.list?.size.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.tv)
        process = findViewById(R.id.pr)
        textViewsize = findViewById(R.id.tv_size)

        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]

        textView.setOnClickListener {
            requestPermission(PERMISSION_RC, permissionList)

        }

        Manager.liveLiveData.observe(this, listObserver)
    }

    @AfterPermissionGranted(PERMISSION_RC)
    fun initData() {
        Manager.init()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        onRequestPermissionsResulted(requestCode, permissions, grantResults)
    }
}