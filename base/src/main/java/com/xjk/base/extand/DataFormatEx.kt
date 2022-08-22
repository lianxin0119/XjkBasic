package com.xjk.base.extand

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * description:
 *
 * Create by LianXin on 2022/8/22 : 19:59
 *
 * @author Lianxin
 */

/**
 * 格式化时间字符
 */
@JvmOverloads
fun Long?.formatDate(format: String = FORMAT_TYPE_NORMAL): String {
    if (this == null || this == 0L) return ""
    val sdf = SimpleDateFormat(format, Locale.CHINA)
    return sdf.format(Date(this))
}

/**
 * 格式化时间字符
 */
fun Long?.formatDateWithMs(): String {
    return this.formatDate(FORMAT_TYPE_NORMAL_WITH_MS)
}

/**
 * 时间格式字符串转成long
 */
fun String?.formatLong(format: String = FORMAT_TYPE_NORMAL): Long {
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
fun String?.formatDate(format: String = FORMAT_TYPE_NORMAL): Date? {
    if (isNullOrBlank()) return null
    val sdf = SimpleDateFormat(format, Locale.CHINA)
    return try {
        sdf.parse(this)
    } catch (e: Exception) {
        null
    }
}

/** 格式化时间字符串(2022-08-17 14:32:17) */
const val FORMAT_TYPE_NORMAL = """yyyy-MM-dd HH:mm:ss"""

/** 格式化时间字符串(20220817143217) */
const val FORMAT_TYPE_NORMAL_NO_SPACE = """yyyyMMddHHmmss"""

/** 格式化时间字符串(2022-08-17 14:32:17 858) */
const val FORMAT_TYPE_NORMAL_WITH_MS = """yyyy-MM-dd HH:mm:ss SSS"""

/** 格式化时间字符串(2022-08-17) */
const val FORMAT_TYPE_NO_DATE = """yyyy-MM-dd"""

/** 格式化时间字符串(14:32:17) */
const val FORMAT_TYPE_ONLY_DATE = """HH:mm:ss"""