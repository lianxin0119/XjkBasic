package com.xjk.base.integration

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.xjk.base.ui.activity.BaseActivity

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/18 21:12
 */
class ActivityLifecycle constructor(
    private val appManager: AppManager
) : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        appManager.addActivity(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {
        if (activity is BaseActivity<out ViewBinding>) {
            appManager.setCurrentActivity(activity)
        }
    }

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivityDestroyed(activity: Activity) {
        appManager.removeActivity(activity)
    }

}