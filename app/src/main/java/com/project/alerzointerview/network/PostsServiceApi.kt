package com.project.alerzointerview.network

import com.project.alerzointerview.model.PostResponseModel
import com.project.alerzointerview.model.Posts
import retrofit2.Response
import retrofit2.http.GET

interface PostsServiceApi {
    @GET("/posts")
    suspend fun getPosts(): Response<PostResponseModel>
}