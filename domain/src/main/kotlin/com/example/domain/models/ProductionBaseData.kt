package com.example.domain.models

/**
 * Created by Alexander Fermin (alexfer06@gmail.com) on 2019-08-20.
 */

open class ProductionBaseData(

    var id: Int = 0,
    var name: String = "",
    var genreIds: List<Int>? = null,
    var backdropPath: String? = null,
    var popularity: Double = 0.0,
    var originalLanguage: String = "",
    var voteAverage: Double = 0.0,
    var overview: String = "",
    var posterPath: String? = null

)