package com.nikhil.cryptocurrency.data.remote

import com.nikhil.cryptocurrency.data.remote.dto.coin.details.CoinDetailsDto
import com.nikhil.cryptocurrency.data.remote.dto.coin.list.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaparikaApi {

    @GET("/v1/coins")
    suspend fun getCoinList():List<CoinDto>

    @GET("/v1/coins/{id}")
    suspend fun getCoinDetails(@Path("id")id:String):CoinDetailsDto
}