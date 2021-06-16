package com.project.alerzointerview.network

import com.project.alerzointerview.repository.PostsServiceRepository
import com.project.alerzointerview.repository.PostsServiceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.DefineComponent
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
abstract class NetworkDIModule {


    @Binds
    abstract fun bindPostsServiceApi(postsServiceApiMethodImpl:PostsServiceApiMethodImpl): PostsServiceApiMethod

    @Binds
    abstract fun bindPostsServiceRepository(postsServiceRepositoryImpl: PostsServiceRepositoryImpl):PostsServiceRepository


}