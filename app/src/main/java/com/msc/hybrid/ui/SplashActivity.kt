package com.msc.hybrid.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import com.msc.hybrid.R
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class SplashActivity : AppCompatActivity() {

    val NEXT_DURATION = 700L

    /**
     * 화면 비율 2 이상 여부
     */
    var isDisplayRatio2 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        loadSplash()

    }

    private fun loadSplash() {
        lifecycleScope.launch(Dispatchers.Default) {
            run splashProcess@{
                runOnUiThread {
                    val displayRation = getDisplayRatio(this@SplashActivity)
                    if (displayRation > 1.9) {
                        isDisplayRatio2 = true
                    }
                    if (isDisplayRatio2) {
                        splash.scaleType = ImageView.ScaleType.CENTER_CROP
                    } else {
                        splash.scaleType = ImageView.ScaleType.FIT_XY
                    }
                    splash.setImageResource(R.drawable.ic_launcher_foreground)
                    goMain(700L)
                }
            }
        }
    }

    private fun goMain(duration: Long = NEXT_DURATION) {
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }, duration)
    }

    fun getDisplayRatio(activity: Activity): Float {
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        display.getRealSize(size) // or getSize(size)
        val width = size.x
        val height = size.y
        val ratio = height.toFloat() / width.toFloat()
        return ratio
    }

    override fun onBackPressed() {
        overridePendingTransition(0, 0)
        finishAffinity()
        exitProcess(0)
    }
}