package com.pulsedonor.app.ui.oboarding.signup

import android.content.Intent
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.SignUpActivityBinding
import com.pulsedonor.app.models.auth.GenderData
import com.pulsedonor.app.models.datas.BloodTypesData
import com.pulsedonor.app.ui.MainViewModel
import com.pulsedonor.app.ui.oboarding.signin.SignInActivity
import com.pulsedonor.app.utilities.openActivity
import javax.inject.Inject

class SignUpActivity : BaseActivity<SignUpActivityBinding>() {

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory
    private lateinit var viewModel: MainViewModel
    private var bloodTypesList = ArrayList<BloodTypesData>()
    private var gendersList = arrayListOf(
        GenderData(1, "Female"),
        GenderData(2, "Male"),
        GenderData(3, "Other")
    )
    var genderId = 0
    var bloodTypeId = 0
    var firstName = ""
    var lastName = ""
    var email = ""
    var password = ""


    override fun inflateBinding(layoutInflater: LayoutInflater): SignUpActivityBinding =
        SignUpActivityBinding.inflate(layoutInflater)

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getBloodTypes()
    }

    override fun observeViewModel() {

        viewModel.successSignUp.observe(this) {
            it.data?.let {
                Toast.makeText(this, "Regjistrimi u krye me sukses!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SignInActivity::class.java))
            }
        }

        viewModel.getBloodTypes.observe(this) { response ->
            response.data?.let { data ->
                bloodTypesList.clear()
                bloodTypesList.addAll(data)

                val bloodTypeName = bloodTypesList.map { it.text }
                val genderAdapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_dropdown_item_1line,
                    bloodTypeName
                )

                binding.actvBloodGroup.setAdapter(genderAdapter)
                binding.actvBloodGroup.threshold = 1
                binding.actvBloodGroup.onItemClickListener =
                    AdapterView.OnItemClickListener { parent, view, position, id ->
                        val selectedbloodTypeName = parent.getItemAtPosition(position) as String
                        val selectedbloodType =
                            bloodTypesList.find { it.value == selectedbloodTypeName }
                        bloodTypeId = selectedbloodType?.value?.toInt() ?: -1
                    }
            }
        }
    }

    override fun onClicks() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.tvSignIn.setOnClickListener {
            openActivity(this, SignInActivity())
        }

        lastName = binding.etLastName.editableText.toString()
        firstName = binding.etFirstName.editableText.toString()
        email = binding.etEmail.editableText.toString().trim()
        password = binding.etPassword.editableText.toString()

        val genderNames = gendersList.map { it.name }
        val genderAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            genderNames
        )

        binding.actvGenderId.setAdapter(genderAdapter)
        binding.actvGenderId.threshold = 1
        binding.actvGenderId.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedGenderName = parent.getItemAtPosition(position) as String
                val selectedGender = gendersList.find { it.name == selectedGenderName }
                genderId = selectedGender?.id!!
            }

        binding.btnSignUp.setOnClickListener {
            val userName = "$firstName $lastName"
            viewModel.signUp(userName, firstName, lastName, password, email, genderId, bloodTypeId)
        }
    }

}