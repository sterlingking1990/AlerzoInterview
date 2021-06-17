package com.project.alerzointerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.alerzointerview.R
import com.project.alerzointerview.model.PostResponseModel

import com.project.alerzointerview.model.Posts
import java.util.ArrayList

class PostAdapter:RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private var postList:PostResponseModel = PostResponseModel()

    fun setPosts(postList: PostResponseModel) {
        this.postList=postList
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedTemplate =
            LayoutInflater.from(parent.context).inflate(R.layout.single_post_item, parent, false)
        return ViewHolder(inflatedTemplate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            val tvPostId = findViewById<TextView>(R.id.tvPostId)
            val tvPostTitle = findViewById<TextView>(R.id.tvPostTitle)
            val tvPostBody = findViewById<TextView>(R.id.tvPostBody)

            tvPostBody.text = postList[position].body
            tvPostId.text = postList[position].id.toString()
            tvPostTitle.text = postList[position].title
        }
    }

    override fun getItemCount(): Int {
        return postList.size;
    }
}