package com.project.alerzointerview.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.alerzointerview.model.PostResponseModel
import com.project.alerzointerview.model.Posts
import com.project.alerzointerview.repository.PostsServiceRepository
import com.project.alerzointerview.util.NetworkHelper
import com.project.alerzointerview.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ViewModelGetPosts @Inject constructor(private val postsServiceRepository: PostsServiceRepository, private val networkHelper: NetworkHelper):ViewModel() {

    private var _getPostsResponse = MutableLiveData<Resource<PostResponseModel>>()
    val postsResponseObservable:LiveData<Resource<PostResponseModel>>
        get() = _getPostsResponse

    private var _networkResponse = MutableLiveData<Boolean>()
    val networkResponseObservable:LiveData<Boolean>
        get() = _networkResponse

    fun getPosts(){
            viewModelScope.launch {
                _getPostsResponse.postValue(Resource.loading())
                try{
                    val postsServiceCallResponse = postsServiceRepository.getPosts()
                    if(postsServiceCallResponse.isSuccessful){
//                        Log.d("Response", postsServiceCallResponse.)
                        _getPostsResponse.postValue(Resource(Resource.Status.SUCCESS,postsServiceCallResponse.body(),null))
                    }
                    else{
                        _getPostsResponse.postValue(Resource.error("Unable to fetch posts"))
                    }

                }
                catch (e:Exception){
                    _getPostsResponse.postValue(Resource.error("Error --> ${e.message}"))
                }
            }

    }

    fun checkNetwork(){
        if(networkHelper.isNetworkConnected()){
            _networkResponse.postValue(true)
        }
        else{
            _networkResponse.postValue(false)
        }
    }
}