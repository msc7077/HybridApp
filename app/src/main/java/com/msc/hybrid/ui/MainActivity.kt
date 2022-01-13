package com.msc.hybrid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.msc.hybrid.R
import com.msc.hybrid.common.BaseWebChromClient
import com.msc.hybrid.common.BaseWebView
import com.msc.hybrid.common.BaseWebViewClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    var mContext: MainActivity? = null

    private var mWebViewMain: WebView? = null

    private var backKeyPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = this

        mWebViewMain = BaseWebView(mContext)

        webview_main.webViewClient = BaseWebViewClient()
        webview_main.webChromeClient = BaseWebChromClient()
        webview_main.loadUrl("https://ksai.kr/")

        var exsettings = webview_main.settings
        with(exsettings) {
            // 웹페이지 자바스크립트 허용 여부
            javaScriptEnabled = true
            // 새창 띄우기 허용 여부
            setSupportMultipleWindows(true)
            // 새창 띄우기(window.open) 허용 여부
            javaScriptCanOpenWindowsAutomatically = true
            // 메타태그 허용 여부
            loadWithOverviewMode = true
            // 화면 사이즈 맞추기 허용 여부
            useWideViewPort = true
            // 로컬 저장소 허용 여부
            domStorageEnabled = true

        }
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 3500) {
            backKeyPressedTime = System.currentTimeMillis()
            Toast.makeText(mContext, "뒤로가기 버튼을 한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
            return
        } else if (System.currentTimeMillis() <= backKeyPressedTime + 3500){
            finish()
            finishAffinity()
            exitProcess(0)
        }
    }
}