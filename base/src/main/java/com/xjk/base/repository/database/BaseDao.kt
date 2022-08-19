package com.xjk.base.repository.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * description:
 *
 * Create by LianXin on 2022/8/17 : 11:14
 *
 * @author Lianxin
 */
abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(data: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg data: T): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(data: List<T>): List<Long>

    @Delete
    abstract fun delete(data: T): Int

    @Delete
    abstract fun delete(vararg data: T): Int

    @Delete
    abstract fun delete(data: List<T>): Int

}