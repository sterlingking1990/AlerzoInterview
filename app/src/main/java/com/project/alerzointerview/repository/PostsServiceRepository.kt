package com.project.alerzointerview.repository

import com.project.alerzointerview.model.PostResponseModel
import com.project.alerzointerview.model.Posts
import retrofit2.Response

interface PostsServiceRepository {
    suspend fun getPosts(): Response<PostResponseModel>
}