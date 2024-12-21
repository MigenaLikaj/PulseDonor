package com.pulsedonor.app.ui.oboarding.forgot_password

import android.content.Intent
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.ForgotPasswordActivityBinding
import com.pulsedonor.app.ui.oboarding.signin.SignInActivity
import com.pulsedonor.app.utilities.hideKeyboard
import javax.inject.Inject

class ForgotPasswordActivity : BaseActivity<ForgotPasswordActivityBinding>() {

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory
    var finalCode: String = ""

    override fun inflateBinding(layoutInflater: LayoutInflater): ForgotPasswordActivityBinding =
        ForgotPasswordActivityBinding.inflate(layoutInflater)

    override fun initViews() {

    }

    override fun observeViewModel() {


            }


    override fun onClicks() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.ivBack1.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.ivBack2.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnSubmit.setOnClickListener {
            binding.clEmailContentHolder.visibility = View.GONE
            binding.clVerification.visibility = View.VISIBLE
        }

        binding.btnSendCode.setOnClickListener {
            binding.clVerification.visibility = View.GONE
            binding.clNewPassword.visibility = View.VISIBLE
        }

        binding.btnUpdatePassword.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.pinView.requestFocus()
        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length == 6) {
                    finalCode = s.toString()
                    println("finalCode $finalCode")
                    hideKeyboard()
                }
            }

            override fun afterTextChanged(s: Editable) {
            }
        }
        binding.pinView.addTextChangedListener(textWatcher)
    }

}