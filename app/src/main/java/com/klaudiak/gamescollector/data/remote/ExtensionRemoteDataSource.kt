package com.klaudiak.gamescollector.data.remote

import javax.inject.Inject

class AppRemoteDataSource @Inject constructor(
    private val networkService: NetworkService
) : BaseDataSource() {
/*
    suspend fun getGamesList(username: String,stats: String,subtype: String) =
        getResult { networkService.getUserGames(
            username = username,
            stats = stats,
            subtype = subtype) }

    suspend fun getExtensionsList(username: String, subtype: String) =
        getResult { networkService.getUserExtensions(username = username, subtype = subtype) }

    suspend fun getUser(username: String) = getResult { networkService.getUser(username= username) }

 */
}