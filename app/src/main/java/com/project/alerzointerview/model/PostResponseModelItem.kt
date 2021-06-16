package com.project.alerzointerview.model


import com.google.gson.annotations.SerializedName

data class PostResponseModelItem(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)