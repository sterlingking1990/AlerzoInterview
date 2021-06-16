package com.project.alerzointerview.repository

import com.project.alerzointerview.model.PostResponseModel
import com.project.alerzointerview.model.Posts
import com.project.alerzointerview.network.PostsServiceApiMethod
import retrofit2.Response
import javax.inject.Inject

class PostsServiceRepositoryImpl @Inject constructor(private val postsServiceApiMethod: PostsServiceApiMethod): PostsServiceRepository {

    override suspend fun getPosts(): Response<PostResponseModel> = postsServiceApiMethod.getPosts()

}