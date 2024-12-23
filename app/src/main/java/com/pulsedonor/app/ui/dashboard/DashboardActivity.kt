package com.pulsedonor.app.ui.dashboard

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.pulsedonor.app.R
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.DashboardActivityBinding
import com.pulsedonor.app.ui.MainViewModel
import com.pulsedonor.app.ui.dashboard.hall_of_fame.HallOfFameFragment
import com.pulsedonor.app.ui.dashboard.home.HomeFragment
import com.pulsedonor.app.ui.dashboard.map.MapFragment
import com.pulsedonor.app.ui.dashboard.profile.ProfileFragment
import javax.inject.Inject

enum class ActiveFragment {
    HomeFragment, HallOfFameFragment, MapFragment, ProfileFragment
}

class DashboardActivity : BaseActivity<DashboardActivityBinding>() {
    override fun inflateBinding(layoutInflater: LayoutInflater): DashboardActivityBinding =
        DashboardActivityBinding.inflate(layoutInflater)

    @Inject
    lateinit var appPreferences: AppPreferences
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.rlMainFragmentHolder, HomeFragment())
        }

        supportFragmentManager.addOnBackStackChangedListener {
            val fr: Fragment? = supportFragmentManager.findFragmentById(R.id.rlMainFragmentHolder)
            if (fr != null) {
                when (fr.javaClass.simpleName) {
                    "HomeFragment" -> {
                        customizeUIOnClick(ActiveFragment.HomeFragment)
                    }

                    "HallOfFameFragment" -> {
                        customizeUIOnClick(ActiveFragment.HallOfFameFragment)
                    }

                    "MapFragment" -> {
                        customizeUIOnClick(ActiveFragment.MapFragment)
                    }

                    "ProfileFragment" -> {
                        customizeUIOnClick(ActiveFragment.ProfileFragment)
                    }
                }
            }
        }
    }


    override fun observeViewModel() {}

    override fun onClicks() {

        binding.llHome.setOnClickListener {
            switchFragment(
                HomeFragment(),
                "home",
                ActiveFragment.HomeFragment
            )
        }

        binding.llHallOfFame.setOnClickListener {
            switchFragment(
                HallOfFameFragment(),
                "hallOfFame",
                ActiveFragment.HallOfFameFragment
            )
        }

        binding.llMap.setOnClickListener {
            switchFragment(
                MapFragment(),
                "map",
                ActiveFragment.MapFragment
            )
        }

        binding.llProfile.setOnClickListener {
            switchFragment(
                ProfileFragment(),
                "profile",
                ActiveFragment.ProfileFragment
            )
        }
    }

    fun customizeUIOnClick(activeFragment: ActiveFragment) {
        binding.ivHome.setImageResource(R.drawable.home_off_icon)
        binding.ivMap.setImageResource(R.drawable.map_off_icon)
        binding.ivProfile.setImageResource(R.drawable.profile_off_icon)
        binding.ivHallOfFame.setImageResource(R.drawable.hall_of_fame_off_icon)

        when (activeFragment) {
            ActiveFragment.HomeFragment -> {
                binding.ivHome.setImageResource(R.drawable.home_on_icon)
            }

            ActiveFragment.HallOfFameFragment -> {
                binding.ivHallOfFame.setImageResource(R.drawable.hall_of_fame_on_icon)
            }

            ActiveFragment.MapFragment -> {
                binding.ivMap.setImageResource(R.drawable.map_on_icon)
            }

            ActiveFragment.ProfileFragment -> {
                binding.ivProfile.setImageResource(R.drawable.profile_on_icon)
            }
        }
    }

    private fun switchFragment(fragment: Fragment, tag: String, activeFragment: ActiveFragment) {
        val currentFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.rlMainFragmentHolder)
        if (currentFragment != null && currentFragment.javaClass.simpleName != fragment.javaClass.simpleName) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.rlMainFragmentHolder, fragment)
                addToBackStack(tag)
            }
            customizeUIOnClick(activeFragment)
        }
    }
}