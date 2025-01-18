package com.pulsedonor.app.ui.dashboard.profile

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pulsedonor.app.adapters.profile.MyPostsAdapter
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.MyPostsActivityBinding
import com.pulsedonor.app.models.profile.MyPostData
import com.pulsedonor.app.ui.MainViewModel
import javax.inject.Inject

class MyPostsActivity : BaseActivity<MyPostsActivityBinding>(), MyPostsAdapter.Listener {
    override fun inflateBinding(layoutInflater: LayoutInflater): MyPostsActivityBinding =
        MyPostsActivityBinding.inflate(layoutInflater)

    @Inject
    lateinit var appPreferences: AppPreferences
    private lateinit var viewModel: MainViewModel
    lateinit var myPostsAdapter: MyPostsAdapter
    private var myPostsList = ArrayList<MyPostData?>()

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        myPostsAdapter = MyPostsAdapter(this)
        binding.rvPosts.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = myPostsAdapter
        }
    }

    override fun observeViewModel() {

        viewModel.getUserProfileBloodRequests.observe(this) { response ->
            response.data?.let { data ->
                myPostsList.clear()
                myPostsList.addAll(data)
                myPostsAdapter.submitList(myPostsList)
                myPostsAdapter.notifyDataSetChanged()
            }
            if (myPostsList.isEmpty()) {
                binding.tvPostsListEmpty.visibility = View.VISIBLE
            } else {
                binding.tvPostsListEmpty.visibility = View.GONE
            }
        }
    }

    override fun onClicks() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.ivAddPost.setOnClickListener {
            startActivity(Intent(this, AddPostActivity::class.java))
        }
    }

    override fun onPostClicked(item: MyPostData) {
        val intent = Intent(this, PostDetailsActivity::class.java).apply {
            putExtra("id", item.id)
        }
        println("postId ${item.id}")
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUserProfileBloodRequests()
    }
}