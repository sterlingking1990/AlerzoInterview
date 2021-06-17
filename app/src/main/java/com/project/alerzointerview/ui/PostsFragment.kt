package com.project.alerzointerview.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.alerzointerview.R
import com.project.alerzointerview.adapter.PostAdapter
import com.project.alerzointerview.util.Resource
import com.project.alerzointerview.util.SessionManager
import com.project.alerzointerview.viewmodel.ViewModelGetPosts
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_posts.*

@AndroidEntryPoint
class PostsFragment : Fragment() {


    private val postsViewModel:ViewModelGetPosts by viewModels()
    lateinit var rvPostsLayoutManager: LinearLayoutManager
    lateinit var postsAdapter:PostAdapter
    var sessionManager:SessionManager?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager(requireContext())
        val firstName = sessionManager?.getFirstName().toString()[0].toUpperCase() + sessionManager?.getFirstName().toString().substring(1,sessionManager?.getFirstName().toString().length)
        tvUserName.text = resources.getString(R.string.username,firstName,"👋")
        rvPostsLayoutManager = LinearLayoutManager(requireContext())
        rvPostsLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvPosts.layoutManager=rvPostsLayoutManager
        rvPosts.setHasFixedSize(true)


        postsAdapter = PostAdapter()

        postsViewModel.checkNetwork()
        setUpObserver()

    }

    fun setUpObserver(){

        postsViewModel.networkResponseObservable.observe(viewLifecycleOwner, { networkObserver ->
            pgLoadingPost.visibility=View.GONE
            if(networkObserver){
                tvNoNetwork.visibility=View.GONE
                postsViewModel.getPosts()
            }
            else{
                tvNoNetwork.visibility=View.VISIBLE
                postsViewModel.checkNetwork()
            }
        })

        postsViewModel.postsResponseObservable.observe(viewLifecycleOwner,{ it2 ->

            when(it2.status){
                Resource.Status.LOADING -> {
                    pgLoadingPost.visibility=View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    pgLoadingPost.visibility=View.GONE
                    Toast.makeText(requireContext(),"Unable to fetch post",Toast.LENGTH_SHORT).show()
                }
                Resource.Status.SUCCESS->{
                    pgLoadingPost.visibility=View.GONE
                    it2.data?.let {
                        postsAdapter.setPosts(it)
                        rvPosts.adapter= postsAdapter
                    }
                }

            }
        })
    }

}