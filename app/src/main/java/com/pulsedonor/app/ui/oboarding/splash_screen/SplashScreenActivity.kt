package com.pulsedonor.app.ui.oboarding.splash_screen

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.SplashScreenActivityBinding
import com.pulsedonor.app.ui.oboarding.intro.IntroductionActivity
import javax.inject.Inject

class SplashScreenActivity : BaseActivity<SplashScreenActivityBinding>() {
    override fun inflateBinding(layoutInflater: LayoutInflater): SplashScreenActivityBinding =
        SplashScreenActivityBinding.inflate(layoutInflater)

    private val SPLASH_TIME_OUT: Long = 1000

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory

    override fun initViews() {

        Handler(Looper.getMainLooper()).postDelayed({
            var intent: Intent? = null
//            if (appPreferences.token != null) {
//                intent = Intent(this@SplashScreenActivity, DashboardActivity::class.java)
//            } else {
                intent = Intent(this@SplashScreenActivity, IntroductionActivity::class.java)
//            }
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }

    override fun observeViewModel() {}

    override fun onClicks() {}

}