package com.ngm.applydigitalchallenge.home.data.remote.dto


import com.google.gson.annotations.SerializedName

data class HitDto(
    @SerializedName("objectID") val objectId : String,
    @SerializedName("story_id") val storyId: Int,
    val author: String,
    @SerializedName("created_at")    val createdAt: String,
    @SerializedName("created_at_i")    val createdAtI: Int,
    @SerializedName("story_title")    val storyTitle: String?,
    @SerializedName("story_url")     val storyUrl: String?,
    val title: String?,
    val url: String?
)