package com.nikhil.cryptocurrency.data.remote.usecase.coin

import com.nikhil.cryptocurrency.data.remote.dto.coin.list.toCoin
import com.nikhil.cryptocurrency.domain.model.Coin
import com.nikhil.cryptocurrency.domain.repo.CoinRepo
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repo: CoinRepo
) {
    suspend operator fun invoke(): List<Coin> {
        return repo.getCoinList().map { it.toCoin() }
    }
}



