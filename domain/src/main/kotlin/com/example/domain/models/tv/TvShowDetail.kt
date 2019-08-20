package com.example.domain.models.tv

data class TvShowDetail(
    val createdBy: List<CreatedBy>,
    val episodeRunTime: List<Int>,
    val firstAirDate: String,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val inProduction: Boolean,
    val languages: List<String>?,
    val lastAirDate: String,
    val name: String,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompany>,
    val seasons: List<Season>,
    val status: String,
    val type: String,
    val voteAverage: Double,
    val voteCount: Int,
    val videos: List<Video>?
) {

    data class Genre(
        val id: Int,
        val name: String
    )


    data class Season(
        val airDate: String,
        val episodeCount: Int,
        val id: Int,
        val name: String,
        val overview: String,
        val posterPath: String?,
        val seasonNumber: Int
    )


    data class CreatedBy(
        val id: Int,
        val creditId: String,
        val name: String,
        val gender: Int,
        val profilePath: String
    )


    data class ProductionCompany(
        val id: Int,
        val name: String,
        val originCountry: String
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