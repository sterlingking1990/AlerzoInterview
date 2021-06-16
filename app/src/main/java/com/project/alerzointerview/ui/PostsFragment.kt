package com.project.alerzointerview.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.project.alerzointerview.R
import com.project.alerzointerview.viewmodel.ViewModelGetPosts
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment() {
    private val postsViewModel:ViewModelGetPosts by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postsViewModel.getPosts()
        setUpObserver()

    }

    fun setUpObserver(){
        postsViewModel.postsResponseObservable.observe(viewLifecycleOwner,{ it2 ->
            it2.data?.let {

            }
            Log.d("Response",it2.data.toString())
        })
    }

}