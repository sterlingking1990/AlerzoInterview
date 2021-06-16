package com.project.alerzointerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.project.alerzointerview.ui.PostsFragment
import com.project.alerzointerview.viewmodel.ViewModelGetPosts
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = PostsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fr_cardInfo, fragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
}