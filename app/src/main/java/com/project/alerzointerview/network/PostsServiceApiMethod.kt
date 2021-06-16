package com.project.alerzointerview.network

import com.project.alerzointerview.model.PostResponseModel
import com.project.alerzointerview.model.Posts
import retrofit2.Response

interface PostsServiceApiMethod {
    suspend fun getPosts(): Response<PostResponseModel>
}