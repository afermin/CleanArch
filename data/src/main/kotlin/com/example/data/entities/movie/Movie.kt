package com.example.data.entities.movie

import androidx.room.Entity
import androidx.room.Index
import androidx.room.TypeConverters
import com.example.data.db.SearchTypeConverters
import com.example.data.entities.ProductionBaseData
import com.google.gson.annotations.SerializedName

@Entity(
    indices = [
        (Index("id"))],
    primaryKeys = ["id"]
)
@TypeConverters(SearchTypeConverters::class)
data class Movie(
    @field:SerializedName("vote_count")
    val vote_count: Int,
    @field:SerializedName("video")
    val video: Boolean,
    @field:SerializedName("original_title")
    val originalTitle: String,
    @field:SerializedName("adult")
    val adult: Boolean,
    @field:SerializedName("release_date")
    val releaseDate: String
) : ProductionBaseData()


