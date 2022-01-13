package com.msc.hybrid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.msc.hybrid.HybridApp
import com.msc.hybrid.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        HybridApp.instance.setCurrentActivity(this@BaseActivity)

    }
}