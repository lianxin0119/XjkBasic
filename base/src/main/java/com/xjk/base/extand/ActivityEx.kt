package com.xjk.base.extand

import android.app.Activity
import android.widget.FrameLayout

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/15 20:13
 */

/** ContentView */
val Activity.mContentView: FrameLayout
    get() {
        return this.findViewById(android.R.id.content)
    }

/** 屏幕宽度(px) */
val Activity.mScreenWidth: Int
    get() {
        return resources.displayMetrics.widthPixels
    }

/** 屏幕高度(px,包含状态栏高度但不包含底部虚拟按键高度) */
val Activity.mScreenHeight: Int
    get() {
        return resources.displayMetrics.heightPixels
    }