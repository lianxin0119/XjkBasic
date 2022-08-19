package com.xjk.base.utils

/**
 * description:
 *
 * Create by LianXin on 2022/8/17 : 14:14
 *
 * @author Lianxin
 */
object DateUtil {

    /**
     * 格式化时间字符串(2022-08-17 14:32:17)
     */
    const val FORMAT_TYPE_NORMAL = """yyyy-MM-dd HH:mm:ss"""

    /**
     * 格式化时间字符串(20220817143217)
     */
    const val FORMAT_TYPE_NORMAL_NO_SPACE = """yyyyMMddHHmmss"""

    /**
     * 格式化时间字符串(2022-08-17 14:32:17 858)
     */
    const val FORMAT_TYPE_NORMAL_WITH_MS = """yyyy-MM-dd HH:mm:ss SSS"""

    /**
     * 格式化时间字符串(2022-08-17)
     */
    const val FORMAT_TYPE_NO_DATE = """yyyy-MM-dd"""

    /**
     * 格式化时间字符串(14:32:17)
     */
    const val FORMAT_TYPE_ONLY_DATE = """HH:mm:ss"""

}