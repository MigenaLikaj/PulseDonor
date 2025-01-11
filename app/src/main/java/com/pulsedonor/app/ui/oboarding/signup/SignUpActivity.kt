package com.pulsedonor.app.ui.oboarding.signup

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import com.pulsedonor.app.R
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.SignUpActivityBinding
import com.pulsedonor.app.ui.oboarding.signin.SignInActivity
import com.pulsedonor.app.utilities.openActivity
import javax.inject.Inject

class SignUpActivity : BaseActivity<SignUpActivityBinding>() {

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory
    var phoneNumberValid = false

    override fun inflateBinding(layoutInflater: LayoutInflater): SignUpActivityBinding =
        SignUpActivityBinding.inflate(layoutInflater)

    override fun initViews() {
    }

    override fun observeViewModel() {
    }

    override fun onClicks() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.tvSignIn.setOnClickListener {
            openActivity(this, SignInActivity())
        }

        binding.ccp.registerCarrierNumberEditText(binding.etPhoneNumber)

        binding.ccp.setPhoneNumberValidityChangeListener {
            phoneNumberValid = if (it) {
                binding.ivValidateIcon.setImageResource(R.drawable.small_tick_icon)
                true
            } else {
                binding.ivValidateIcon.setImageResource(R.drawable.small_x_icon)
                false
            }
        }

        binding.ccp.setOnCountryChangeListener {
            binding.etPhoneNumber.setText("")
            binding.etPhoneNumber.isEnabled = true
        }


        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {
                    binding.ivValidateIcon.visibility = View.VISIBLE
                } else {
                    binding.ivValidateIcon.visibility = View.GONE
                }
            }
        }
        binding.etPhoneNumber.addTextChangedListener(textWatcher)

        binding.etPhoneNumber.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.tvLabel.visibility = View.VISIBLE
                binding.etPhoneNumber.hint = ""
            } else {
                if (binding.etPhoneNumber.editableText.isNullOrBlank()) {
                    binding.tvLabel.visibility = View.INVISIBLE
                    binding.etPhoneNumber.hint = this.getString(R.string.phone_number)
                } else {
                    binding.tvLabel.visibility = View.VISIBLE
                }
            }
        }
    }

}