package com.nikhil.cryptocurrency.data.remote.usecase.coin

import com.nikhil.cryptocurrency.commons.Resources
import com.nikhil.cryptocurrency.data.remote.dto.coin.list.toCoin
import com.nikhil.cryptocurrency.domain.model.Coin
import com.nikhil.cryptocurrency.domain.repo.CoinRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repo: CoinRepo
) {
    operator fun invoke(): Flow<Resources<List<Coin>>> = flow {
        try {
            emit(Resources.Loading())
            val coin = repo.getCoinList()
            emit(
                Resources.Success(
                    coin.map {
                        it.toCoin()
                    })
            )

        } catch (e: HttpException) {
            emit(Resources.Error(e.localizedMessage ?: "Unexpected error"))

        } catch (e: IOException) {
            emit(Resources.Error(e.localizedMessage ?: "Server error"))
        }
    }
}