package com.project.alerzointerview.network

import com.project.alerzointerview.model.PostResponseModel
import com.project.alerzointerview.model.Posts
import retrofit2.Response
import javax.inject.Inject

class PostsServiceApiMethodImpl @Inject constructor(private val postsServiceApi: PostsServiceApi):PostsServiceApiMethod {


    override suspend fun getPosts(): Response<PostResponseModel> = postsServiceApi.getPosts()
}