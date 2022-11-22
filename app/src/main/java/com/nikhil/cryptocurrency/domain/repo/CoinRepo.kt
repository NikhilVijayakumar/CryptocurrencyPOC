package com.nikhil.cryptocurrency.domain.repo

import com.nikhil.cryptocurrency.data.remote.dto.coin.details.CoinDetailsDto
import com.nikhil.cryptocurrency.data.remote.dto.coin.list.CoinDto

interface CoinRepo {
    suspend fun getCoinList(): List<CoinDto>
    suspend fun getCoinDetails(id: String): CoinDetailsDto
}