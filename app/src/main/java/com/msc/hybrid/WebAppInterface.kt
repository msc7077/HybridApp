package com.msc.hybrid

import android.content.Context
import android.webkit.WebView

class WebAppInterface constructor(
    private var mContext: Context,
    private val mWebview: WebView
) {

    fun setContext(context: Context) {
        mContext = context
    }



}