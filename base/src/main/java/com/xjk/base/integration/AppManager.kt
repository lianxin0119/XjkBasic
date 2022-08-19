package com.xjk.base.integration

import android.app.Activity
import android.app.Application
import java.util.*

/**
 * description : 暂时没有使用到的场景。
 *
 * Create by LianXin on 2022/8/14 17:08
 */
class AppManager constructor(var application: Application?) {

    /** 管理所有activity */
    private val mActivityList: MutableList<Activity>

    /** 当前在前台的activity */
    private var mCurrentActivity: Activity? = null

    init {
        mActivityList = LinkedList()
    }

    /**
     * 添加Activity到集合
     */
    internal fun addActivity(activity: Activity) {
        synchronized(AppManager::class.java) {
            if (!mActivityList.contains(activity)) {
                mActivityList.add(activity)
            }
        }
    }

    /**
     * 删除集合里的指定activity
     */
    internal fun removeActivity(activity: Activity) {
        synchronized(AppManager::class.java) {
            if (mActivityList.contains(activity)) {
                mActivityList.remove(activity)
            }
        }
    }

    /**
     * 将在前台的activity保存
     */
    internal fun setCurrentActivity(currentActivity: Activity?) {
        this.mCurrentActivity = currentActivity
    }

    /**
     * 获取在前台的Activity
     */
    fun getCurrentActivity(): Activity? {
        return mCurrentActivity
    }

    /**
     * 获取所有的activity
     */
    fun getActivityList(): List<Activity> {
        return mActivityList
    }

}