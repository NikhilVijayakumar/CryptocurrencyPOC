package com.nikhil.cryptocurrency.data.remote.usecase.details

import com.nikhil.cryptocurrency.commons.Resources
import com.nikhil.cryptocurrency.data.remote.dto.coin.details.toCoinDetails
import com.nikhil.cryptocurrency.domain.model.CoinDetails
import com.nikhil.cryptocurrency.domain.repo.CoinRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(
    private val repo: CoinRepo
) {
    operator fun invoke(coinId: String): Flow<Resources<CoinDetails>> = flow {
        try {
            emit(Resources.Loading())
            val details = repo.getCoinDetails(coinId)
            emit(
                Resources.Success(details.toCoinDetails())
            )

        } catch (e: HttpException) {
            emit(Resources.Error(e.localizedMessage ?: "Unexpected error"))

        } catch (e: IOException) {
            emit(Resources.Error(e.localizedMessage ?: "Server error"))
        }
    }
}