package com.msc.hybrid.common

import android.util.Log
import android.webkit.WebChromeClient

open class BaseWebChromClient: WebChromeClient() {

    companion object {
        private const val TAG = "BaseWebChromClient"
    }

    init {
        Log.d(TAG, "init")
    }

}