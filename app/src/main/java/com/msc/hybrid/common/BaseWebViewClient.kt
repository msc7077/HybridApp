package com.msc.hybrid.common

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.msc.hybrid.HybridApp

open class BaseWebViewClient: WebViewClient() {

    companion object {
        private const val TAG = "BaseWebViewClient"
    }

    init {
        Log.d(TAG, "init")
    }

//    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//        var uri = Uri.parse(url)
//        return handleUri(view, uri)
//    }
//
//    fun handleUri(view: WebView?, uri: Uri): Boolean {
//
//        if (uri == null) return false
//
//        val url = uri.toString()
//        val host = uri.host
//        val scheme = uri.scheme
//
//        val activity = HybridApp.instance.getCurrentActivity() ?: return false
//
//        return when(scheme) {
//            "mailto" ->
//                try {
//                    val intent = Intent(Intent.ACTION_SENDTO, uri)
//                    activity.startActivity(intent)
//                }
//        }
//    }
}