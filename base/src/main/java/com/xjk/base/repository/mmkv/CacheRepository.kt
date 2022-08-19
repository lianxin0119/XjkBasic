package com.xjk.base.repository.mmkv

import com.tencent.mmkv.MMKV

/**
 * description : 数据缓存仓库,持久化存储。替代SharedPreferences。
 *
 * Create by LianXin on 2022/8/13 10:18
 */
object CacheRepository {

    /**
     * 获取公用存储仓库
     */
    fun getComMmkv(): MMKV {
        return MMKV.defaultMMKV()
    }

    /**
     * 获取一个仅仅针对用户的存储仓库
     * @param userId 唯一标识
     */
    fun getUserMmkv(userId: String?): MMKV {
        if (userId.isNullOrBlank()) {
            return getComMmkv()
        }
        return MMKV.mmkvWithID(userId)
    }

}