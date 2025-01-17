package com.pulsedonor.app.ui.dashboard.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerPosts(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    var postId: Int
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> PostsOverviewFragment(postId)
            1 -> PostsApplicationsFragment(postId)
            else -> throw IllegalStateException("Invalid adapter position")
        }
}