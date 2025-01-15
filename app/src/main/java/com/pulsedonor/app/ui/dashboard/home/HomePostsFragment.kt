package com.pulsedonor.app.ui.dashboard.home

import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.pulsedonor.app.adapters.home.PostsInHomeAdapter
import com.pulsedonor.app.base.fragment.BaseFragment
import com.pulsedonor.app.databinding.HomePostsFragmentBinding
import com.pulsedonor.app.models.home.HomePostsResponse

class HomePostsFragment : BaseFragment<HomePostsFragmentBinding>(), PostsInHomeAdapter.Listener {

    lateinit var postsInHomeAdapter: PostsInHomeAdapter
    private var postsList = ArrayList<HomePostsResponse?>()

    override fun inflateBinding(layoutInflater: LayoutInflater): HomePostsFragmentBinding =
        HomePostsFragmentBinding.inflate(layoutInflater)

    override fun observeViewModel() {
    }

    override fun initViews() {

        postsInHomeAdapter = PostsInHomeAdapter(this)
        binding.rvPosts.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = postsInHomeAdapter
        }

        postsList = arrayListOf(
            HomePostsResponse(
                posterName = "John Doe",
                posterImage = "https://randomuser.me/api/portraits/men/1.jpg",
                bloodGroup = "A+",
                quantity = "2 units",
                urgency = "High",
                hospital = "City General Hospital",
                dateTime = "2025-01-14T10:30:00",
                bloodRecipientAge = 35,
                bloodRecipientName = "Jane Smith"
            ),
            HomePostsResponse(
                posterName = "Emma Watson",
                posterImage = "https://randomuser.me/api/portraits/men/2.jpg",
                bloodGroup = "O-",
                quantity = "1 unit",
                urgency = "Medium",
                hospital = "Sunrise Medical Center",
                dateTime = "2025-01-14T12:00:00",
                bloodRecipientAge = 42,
                bloodRecipientName = "Robert Brown"
            ),
            HomePostsResponse(
                posterName = "Chris Evans",
                posterImage = "https://randomuser.me/api/portraits/men/3.jpg",
                bloodGroup = "B+",
                quantity = "3 units",
                urgency = "Critical",
                hospital = "Green Valley Hospital",
                dateTime = "2025-01-14T15:45:00",
                bloodRecipientAge = 28,
                bloodRecipientName = "Lisa White"
            ),
            HomePostsResponse(
                posterName = "Sophia Johnson",
                posterImage = "https://randomuser.me/api/portraits/women/4.jpg",
                bloodGroup = "AB-",
                quantity = "2 units",
                urgency = "Low",
                hospital = "Downtown Healthcare",
                dateTime = "2025-01-14T18:00:00",
                bloodRecipientAge = 50,
                bloodRecipientName = "Tom Davis"
            ),
            HomePostsResponse(
                posterName = "Liam Smith",
                posterImage = "https://randomuser.me/api/portraits/women/4.jpg",
                bloodGroup = "O+",
                quantity = "4 units",
                urgency = "High",
                hospital = "Saint Mary Hospital",
                dateTime = "2025-01-14T20:30:00",
                bloodRecipientAge = 19,
                bloodRecipientName = "Anna Lee"
            )
        )

        postsInHomeAdapter.submitList(postsList)
    }

    override fun onClicks() {
    }

    override fun onApplyClicked() {
        val alert = AlertDialog.Builder(requireContext()).setItems(
            arrayOf("A jeni i/e sigurt qe deshironi te aplikoni?")
        ) { _, which -> }
        alert.setCancelable(true).setPositiveButton("Po") { dialog, _ ->
            Toast.makeText(context, "Aplikimi u krye me sukses!", Toast.LENGTH_SHORT).show()
        }.setNegativeButton("Jo") { dialog, _ ->
            dialog.dismiss()
        }
        alert.setOnCancelListener { it.dismiss() }
        alert.create()
        alert.show()
    }

}