package com.xjk.base.extand

import com.xjk.base.utils.DateUtil
import java.text.SimpleDateFormat
import java.util.*

/**
 * description:
 *
 * Create by LianXin on 2022/8/17 : 14:24
 *
 * @author Lianxin
 */

/**
 * 是否是0或者null
 */
fun Long?.isNullOrZero(): Boolean {
    return this == null || this == 0L
}

/**
 * 格式化时间字符
 */
@JvmOverloads
fun Long?.formatDate(format: String = DateUtil.FORMAT_TYPE_NORMAL): String {
    if (this == null || this == 0L) return ""
    val sdf = SimpleDateFormat(format, Locale.CHINA)
    return sdf.format(Date(this))
}

/**
 * 格式化时间字符
 */
fun Long?.formatDateWithMs(): String {
    return this.formatDate (DateUtil.FORMAT_TYPE_NORMAL_WITH_MS)
}