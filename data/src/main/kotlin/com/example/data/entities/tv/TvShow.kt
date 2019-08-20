package com.example.data.entities.tv

import androidx.room.Index
import androidx.room.TypeConverters
import androidx.room.Entity
import com.example.data.db.SearchTypeConverters
import com.example.data.entities.ProductionBaseData
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "tv_show",
    indices = [
        (Index("id"))],
    primaryKeys = ["id"]
)

@TypeConverters(SearchTypeConverters::class)
data class TvShow(
    @field:SerializedName("original_name")
    val originalName: String,
    @field:SerializedName("origin_country")
    val originCountry: List<String>,
    @field:SerializedName("vote_count")
    val voteCount: Int,
    @field:SerializedName("first_air_date")
    val firstAirDate: String
) : ProductionBaseData()