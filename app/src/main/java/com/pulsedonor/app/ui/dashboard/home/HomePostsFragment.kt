package com.pulsedonor.app.ui.dashboard.home

import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pulsedonor.app.adapters.home.PostsInHomeAdapter
import com.pulsedonor.app.base.fragment.BaseFragment
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.HomePostsFragmentBinding
import com.pulsedonor.app.models.home.BloodRequestsData
import com.pulsedonor.app.ui.MainViewModel
import javax.inject.Inject

class HomePostsFragment : BaseFragment<HomePostsFragmentBinding>(), PostsInHomeAdapter.Listener {

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory
    private lateinit var viewModel: MainViewModel
    lateinit var postsInHomeAdapter: PostsInHomeAdapter
    private var postsList = ArrayList<BloodRequestsData?>()


    override fun inflateBinding(layoutInflater: LayoutInflater): HomePostsFragmentBinding =
        HomePostsFragmentBinding.inflate(layoutInflater)


    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        postsInHomeAdapter = PostsInHomeAdapter(this)
        binding.rvPosts.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = postsInHomeAdapter
        }
        viewModel.getPostsInhome()
    }

    override fun observeViewModel() {

        viewModel.getPostsInhome.observe(this) {
            it.data?.let {
                postsList.clear()
                postsList.addAll(it)
                postsInHomeAdapter.submitList(postsList)
            }
        }

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