package com.nikhil.cryptocurrency.data.remote.usecase.details

import com.nikhil.cryptocurrency.data.remote.dto.coin.details.toCoinDetails
import com.nikhil.cryptocurrency.domain.model.CoinDetails
import com.nikhil.cryptocurrency.domain.repo.CoinRepo
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(
    private val repo: CoinRepo
) {
    suspend operator fun invoke(coinId: String): CoinDetails =
        repo.getCoinDetails(coinId).toCoinDetails()
}