package com.example.domain.models.tv

import com.example.domain.models.ProductionBaseData

data class TvShow(
    val originalName: String,
    val originCountry: List<String>,
    val voteCount: Int,
    val firstAirDate: String
) : ProductionBaseData()