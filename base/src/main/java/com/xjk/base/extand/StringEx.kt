package com.xjk.base.extand

import com.xjk.base.utils.DateUtil
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * description:
 *
 * Create by LianXin on 2022/8/17 : 14:14
 *
 * @author Lianxin
 */

/**
 * 时间格式字符串转成long
 */
fun String?.formatLong(format: String = DateUtil.FORMAT_TYPE_NORMAL): Long {
    if (isNullOrBlank()) return 0L
    val sdf = SimpleDateFormat(format, Locale.CHINA)
    return try {
        sdf.parse(this)?.time ?: 0L
    } catch (e: Exception) {
        0L
    }
}

/**
 * 时间格式字符串转成Date
 */
fun String?.formatDate(format: String = DateUtil.FORMAT_TYPE_NORMAL): Date? {
    if (isNullOrBlank()) return null
    val sdf = SimpleDateFormat(format, Locale.CHINA)
    return try {
        sdf.parse(this)
    } catch (e: Exception) {
        null
    }
}