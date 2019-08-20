package com.example.domain.models.movie

data class MovieDetail(
    val adult: Boolean,
    val backdropPath: String?,
    val belongsToCollection: BelongsToCollection?,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String?,
    val id: Int,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int?,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val videos: List<Video>?
) {

    data class SpokenLanguage(
        val iso6391: String,
        val name: String
    )

    data class ProductionCompany(
        val id: Int,
        val logoPath: String,
        val name: String,
        val originCountry: String
    )

    data class BelongsToCollection(
        val id: Int,
        val name: String,
        val posterPath: String,
        val backdropPath: String
    )

    data class Genre(
        val id: Int,
        val name: String
    )

    data class ProductionCountry(
        val iso31661: String,
        val name: String
    )

    data class Video(
        val id: String,
        val iso6391: String,
        val iso31661: String,
        val key: String,
        val name: String,
        val site: String,
        val size: Int,
        val type: String
    )
}