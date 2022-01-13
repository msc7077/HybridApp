package com.msc.hybrid

import androidx.multidex.MultiDexApplication
import com.msc.hybrid.ui.BaseActivity
import java.lang.ref.WeakReference

class HybridApp: MultiDexApplication() {

    companion object {
        lateinit var instance: HybridApp
        private set
    }

    /**
     * 현재 활성 상태인 액티비티.
     */
    private var mCurrentActivity: WeakReference<BaseActivity>? = null

    /**
     * 현재 액티비티를 가져온다.
     */
    fun getCurrentActivity(): BaseActivity? {
        return mCurrentActivity?.get()
    }

    /**
     * 현재 액티비티 WeakReference 저장한다.
     */
    fun setCurrentActivity(mCurrentActivity: BaseActivity) {
        this.mCurrentActivity = WeakReference(mCurrentActivity)
    }


}