package com.pulsedonor.app.ui.dashboard.profile

import android.R
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.AddPostActivityBinding
import com.pulsedonor.app.models.datas.BloodTypesData
import com.pulsedonor.app.models.datas.Data
import com.pulsedonor.app.ui.MainViewModel
import com.pulsedonor.app.utilities.showToast
import javax.inject.Inject

class AddPostActivity : BaseActivity<AddPostActivityBinding>() {
    override fun inflateBinding(layoutInflater: LayoutInflater): AddPostActivityBinding =
        AddPostActivityBinding.inflate(layoutInflater)

    @Inject
    lateinit var appPreferences: AppPreferences
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory
    private var bloodTypesList = ArrayList<BloodTypesData>()
    private var hospitalsList = ArrayList<Data>()
    private var urgencyList = ArrayList<Data>()
    var bloodTypeId = 0
    var hospitalId = 0
    var urgenceTypeId = 0
    var donationDate = "2025-01-17"
    var donationTime = "11:10:00"
    var age = 0
    var quantity = 0.0

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getBloodTypes()
    }

    override fun observeViewModel() {

        viewModel.getBloodTypes.observe(this) { response ->
            response.data?.let { data ->
                bloodTypesList.clear()
                bloodTypesList.addAll(data)
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

                    val selectedbloodType = bloodTypesList.find {
                        it.text?.trim().equals(selectedbloodTypeName.trim(), ignoreCase = true)
                    }

                    val bloodTypeId =
                        selectedbloodType?.value?.toIntOrNull() ?: -1 // Safe conversion to Int
                    val bloodTypeText = selectedbloodType?.text.orEmpty()

                    binding.actvBloodGroup.setText(bloodTypeText)
                    this.bloodTypeId = bloodTypeId

                    println("bloodTypeText $bloodTypeText")
                    println("bloodTypeId $bloodTypeId")
                }


            viewModel.getHospitals()
        }

        viewModel.getHospitals.observe(this) { response ->
            response.data?.let { data ->
                hospitalsList.clear()
                hospitalsList.addAll(data)
            }

            val hospitalName = hospitalsList.map { it.text }
            val hospitalAdapter = ArrayAdapter(
                this,
                R.layout.simple_dropdown_item_1line,
                hospitalName
            )

            binding.actvHospital.setAdapter(hospitalAdapter)
            binding.actvHospital.threshold = 1
            binding.actvHospital.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    val selectedHospitalName = parent.getItemAtPosition(position) as String
                    val selectedHospital = hospitalsList.find {
                        it.text?.trim().equals(selectedHospitalName.trim(), ignoreCase = true)
                    }
                    val hospitalId =
                        selectedHospital?.value?.toIntOrNull() ?: -1 // Safe conversion to Int
                    val hospitalTypeText = selectedHospital?.text.orEmpty()

                    binding.actvHospital.setText(hospitalTypeText)
                    this.hospitalId = hospitalId

                    println("hospitalTypeText $hospitalTypeText")
                    println("hospitalId $hospitalId")
                }

            viewModel.getUrgencetypes()

        }
        viewModel.getUrgencetypes.observe(this) { response ->
            response.data?.let { data ->
                urgencyList.clear()
                urgencyList.addAll(data)
            }

            val urgencyName = urgencyList.map { it.text }
            val urgencAdapter = ArrayAdapter(
                this,
                R.layout.simple_dropdown_item_1line,
                urgencyName
            )

            binding.actvUrgency.setAdapter(urgencAdapter)
            binding.actvUrgency.threshold = 1
            binding.actvUrgency.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->

                    val selectedUrgencyName = parent.getItemAtPosition(position) as String
                    val selectedUrgency = urgencyList.find {
                        it.text?.trim().equals(selectedUrgencyName.trim(), ignoreCase = true)
                    }
                    val urgenceTypeId =
                        selectedUrgency?.value?.toIntOrNull() ?: -1 // Safe conversion to Int
                    val bloodTypeText = selectedUrgency?.text.orEmpty()

                    binding.actvUrgency.setText(bloodTypeText)
                    this.urgenceTypeId = urgenceTypeId

                    println("bloodTypeText $bloodTypeText")
                    println("urgenceTypeId $urgenceTypeId")
                }
        }

        viewModel.postAddedSuccessfully.observe(this) { data ->
            if (data == true) {
                showToast("Sukses")
            }
        }

    }

    override fun onClicks() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnSave.setOnClickListener {
            val quantity = binding.etQuantity.editableText.toString().toDouble()
            val age = binding.etAge.editableText.toString().toInt()
            val firstname = binding.etFirstName.editableText.toString()
            val lastName = binding.etLastName.editableText.toString()

            viewModel.addPost(
                bloodTypeId,
                quantity,
                urgenceTypeId,
                hospitalId,
                donationDate,
                donationTime,
                firstname,
                lastName, age
            )
        }
    }
}