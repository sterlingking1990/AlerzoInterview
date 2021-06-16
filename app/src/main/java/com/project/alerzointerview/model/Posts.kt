package com.project.alerzointerview.model

import com.google.gson.annotations.SerializedName


data class Posts(
    @SerializedName("userId") val userId:Int,

    @SerializedName("Id") val Id:Int,

    @SerializedName("Title") val Title:String,

    @SerializedName("Body") val Body: String,

    )