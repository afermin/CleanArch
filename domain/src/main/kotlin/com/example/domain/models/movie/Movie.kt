package com.example.domain.models.movie

import com.example.domain.models.ProductionBaseData


data class Movie(
        val voteCount: Int,
        val video: Boolean,
        val originalTitle: String,
        val adult: Boolean,
        val releaseDate: String
): ProductionBaseData()


