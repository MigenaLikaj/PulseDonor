package com.pulsedonor.app.ui.dashboard.profile

import android.R
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.ProfileActivityBinding
import com.pulsedonor.app.models.datas.BloodTypesData
import com.pulsedonor.app.models.datas.CitiesData
import com.pulsedonor.app.ui.MainViewModel
import com.pulsedonor.app.utilities.showToast
import javax.inject.Inject

class ProfileActivity : BaseActivity<ProfileActivityBinding>() {
    override fun inflateBinding(layoutInflater: LayoutInflater): ProfileActivityBinding =
        ProfileActivityBinding.inflate(layoutInflater)

    @Inject
    lateinit var appPreferences: AppPreferences
    private lateinit var viewModel: MainViewModel
    private var bloodTypesList = ArrayList<BloodTypesData>()
    private var citiesList = ArrayList<CitiesData>()
    var lastName = ""
    var firstName = ""
    var email = ""
    var bloodTypeId = -1
    var bloodTypeValue = ""
    var primaryCityId = -1
    var primaryCityname = ""

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        binding.etEmail.setText(appPreferences.email)
        email = appPreferences.email.toString()
        viewModel.getUserProfile()
        viewModel.getCities()
        viewModel.getBloodTypes()
    }

    override fun observeViewModel() {

        viewModel.getUserProfile.observe(this) { userProfileData ->
            userProfileData.data?.let {
                it.firstName?.let {
                    binding.etFirstName.setText(it)
                    firstName = it
                }
                it.lastName?.let {
                    binding.etLastName.setText(it)
                    lastName = it
                }
                it.bloodType?.let {
                    it.bloodTypeId?.let {
                        bloodTypeId = it
                    }
                    it.type?.let {
                        bloodTypeValue = it
                        binding.actvBloodGroup.setText(it)
                    }
                }
                it.primaryCity?.let {
                    it.primaryCityId?.let {
                        primaryCityId = it
                    }
                    it.name?.let {
                        binding.actvCity.setText(it)
                        primaryCityname = it
                    }
                }
            }
        }

        viewModel.dataSavedSuccessfully.observe(this) { saved ->
            saved?.let {
                if (it) {
                    showToast("Data has been updated successfully!")
                }
            }
        }

        viewModel.getBloodTypes.observe(this) { response ->
            response.data?.let { data ->
                bloodTypesList.clear()
                bloodTypesList.addAll(data)
            }
        }

        viewModel.getCities.observe(this) { response ->
            response.data?.let { data ->
                citiesList.clear()
                citiesList.addAll(data)
            }
        }
    }

    override fun onClicks() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val bloodTypeName = bloodTypesList.map { it.text }
        val genderAdapter = ArrayAdapter(
            this,
            R.layout.simple_dropdown_item_1line,
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
                binding.actvBloodGroup.setText(selectedbloodType?.text)
            }

        val citiesName = bloodTypesList.map { it.text }
        val cityAdapter = ArrayAdapter(
            this,
            R.layout.simple_dropdown_item_1line,
            citiesName
        )

        binding.actvCity.setAdapter(cityAdapter)
        binding.actvCity.threshold = 1
        binding.actvCity.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedCityName = parent.getItemAtPosition(position) as String
                val selectedCity =
                    citiesList.find { it.value == selectedCityName }
                primaryCityId = selectedCity?.value?.toInt() ?: -1
                binding.actvCity.setText(selectedCity?.text)
            }

        binding.btnSave.setOnClickListener {
            firstName = binding.etFirstName.editableText.toString().trim()
            lastName = binding.etLastName.editableText.toString().trim()
            email = binding.etEmail.editableText.toString().trim()
            viewModel.editAccount(firstName, lastName, email, bloodTypeId, primaryCityId)
        }
    }
}