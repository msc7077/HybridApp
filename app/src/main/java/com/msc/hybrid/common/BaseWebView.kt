package com.msc.hybrid.common

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.webkit.CookieManager
import android.webkit.WebView
import androidx.annotation.RequiresApi
import com.msc.hybrid.BuildConfig
import com.msc.hybrid.WebAppInterface

class BaseWebView: WebView {
    constructor(context: Context?): super(context!!)

    var webAppInterface: WebAppInterface? = null

    init {
        if (Build.VERSION.SDK_INT >= 26) {
            setRendererPriorityPolicy(RENDERER_PRIORITY_IMPORTANT, true)
        }

        clearHistory() // 웹뷰 히스토리 초기화
        clearCache(true) // 웹뷰 캐시 초기화

        // 롤리팝 쿠키 설정 및 https > http통신시 쿠키옵션 설정
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance()?.apply {
                setAcceptCookie(true)
                setAcceptThirdPartyCookies(this@BaseWebView, true)
                CookieManager.setAcceptFileSchemeCookies(true)
            }
        }

        var exsettings = settings

        with(exsettings) {
            //자바스크립트 사용
            javaScriptEnabled = true
            //팝업창 띄우도록 설정 (window.open()사용을 위함)
            javaScriptCanOpenWindowsAutomatically = true
            //로컬저장소 허용 여부
            domStorageEnabled = true
            //새창 띄우기 허용 여부
            setSupportMultipleWindows(true)
            //inspect로 디버깅하기 위한 설정
            if (BuildConfig.DEBUG)
                setWebContentsDebuggingEnabled(true)    //chrome://inspect/#devices

        }

    }
}