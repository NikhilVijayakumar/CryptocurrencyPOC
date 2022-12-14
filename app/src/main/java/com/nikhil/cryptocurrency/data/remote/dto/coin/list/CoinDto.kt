package com.nikhil.cryptocurrency.data.remote.dto.coin.list

import com.google.gson.annotations.SerializedName
import com.nikhil.cryptocurrency.domain.model.Coin

data class CoinDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoin(): Coin = Coin(
    id = id,
    isActive = isActive,
    name= name,
    rank = rank,
    symbol = symbol
)

fun List<CoinDto>.toCoinList():List<Coin> {
     val coinList: MutableList<Coin> = mutableListOf()
    this.forEach {
        coinList.add(it.toCoin())
    }
    return coinList
}