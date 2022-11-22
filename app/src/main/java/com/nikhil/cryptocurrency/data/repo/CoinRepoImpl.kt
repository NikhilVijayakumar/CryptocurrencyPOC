package com.nikhil.cryptocurrency.data.repo

import com.nikhil.cryptocurrency.data.remote.CoinPaparikaApi
import com.nikhil.cryptocurrency.data.remote.dto.coin.details.CoinDetailsDto
import com.nikhil.cryptocurrency.data.remote.dto.coin.list.CoinDto
import com.nikhil.cryptocurrency.domain.repo.CoinRepo
import javax.inject.Inject

class CoinRepoImpl @Inject constructor(val api: CoinPaparikaApi) : CoinRepo{
    override suspend fun getCoinList(): List<CoinDto>  = api.getCoinList()

    override suspend fun getCoinDetails(id: String): CoinDetailsDto = api.getCoinDetails(id)

}