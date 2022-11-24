package com.nikhil.cryptocurrency.domain.model

import com.nikhil.cryptocurrency.data.remote.dto.coin.details.*


data class CoinDetails(
    val description: String,
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val tags: List<String>,
    val team: List<Team>,

    )