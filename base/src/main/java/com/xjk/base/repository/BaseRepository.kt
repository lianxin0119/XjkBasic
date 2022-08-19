package com.xjk.base.repository

/**
 * description : Repository基类.
 *
 * Create by LianXin on 2022/8/16 19:22
 */

open class BaseRepository<T : IRemoteDataSource, R : ILocalDataSource>(
    val remoteDataSource: T,
    val localDataSource: R
) : IRepository

open class BaseRepositoryLocal<T : ILocalDataSource>(
    val localDataSource: T
) : IRepository

open class BaseRepositoryRemote<T : IRemoteDataSource>(
    val remoteDataSource: T
) : IRepository

open class BaseRepositoryNothing() : IRepository