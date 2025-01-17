package com.pulsedonor.app.ui.dashboard.profile

import android.R
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.pulsedonor.app.base.fragment.BaseFragment
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.PostOverviewFragmentBinding
import com.pulsedonor.app.models.datas.BloodTypesData
import com.pulsedonor.app.models.datas.Data
import com.pulsedonor.app.ui.MainViewModel
import javax.inject.Inject

class PostsOverviewFragment(var postId: Int) : BaseFragment<PostOverviewFragmentBinding>() {

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory
    private lateinit var viewModel: MainViewModel
    private var bloodTypesList = ArrayList<BloodTypesData>()
    private var hospitalsList = ArrayList<Data>()
    private var urgencyList = ArrayList<Data>()

    var bloodTypeId = 0
    var hospitalId = 0
    var urgenceTypeId = 0
    var donationDate = ""
    var donationTime = ""
    var bloodTypeValue = ""
    var hospitalValue = ""
    var age = 0
    var quantity = 0.0

    override fun inflateBinding(layoutInflater: LayoutInflater): PostOverviewFragmentBinding =
        PostOverviewFragmentBinding.inflate(layoutInflater)

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getBloodRequestByIdAC(postId)
    }

    override fun observeViewModel() {

        viewModel.getBloodRequestByIdAC.observe(this) { userProfileData ->
            userProfileData.data?.let {
                it.quantity?.let {
                    binding.etQuantity.setText(it.toString())
                    quantity = it
                }
                it.age?.let {
                    binding.etAge.setText(it)
                    age = it
                }

                it.donationDate?.let {
                    binding.etDate.setText(it)
                    donationDate = it
                }
                it.donationTime?.let {
                    binding.etTime.setText(it)
                    donationTime = it
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
                it.hospital?.let {
                    it.hospitalId?.let {
                        hospitalId = it
                    }
                    it.name?.let {
                        binding.actvHospital.setText(it)
                        hospitalValue = it
                    }
                }
                it.urgenceType?.let {
                    it.urgenceTypeId?.let {
                        urgenceTypeId = it
                    }
                    it.type?.let {
                        binding.actvUrgency.setText(it)
                    }
                }
            }
            viewModel.getBloodTypes()
        }

        viewModel.getBloodTypes.observe(this) { response ->
            response.data?.let { data ->
                bloodTypesList.clear()
                bloodTypesList.addAll(data)
            }
            val bloodTypeName = bloodTypesList.map { it.text }
            val genderAdapter = ArrayAdapter(
                requireContext(),
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
                requireContext(),
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
                requireContext(),
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
    }


    override fun onClicks() {

        binding.btnSave.setOnClickListener {
            if (quantity != 0.0) {
                quantity = binding.etQuantity.editableText.toString().toDouble()
            }
            if (age != 0) {
                age = binding.etAge.editableText.toString().toInt()
            }
            viewModel.editBloodRequestByIdAC(
                bloodTypeId,
                quantity,
                urgenceTypeId,
                hospitalId,
                donationDate,
                donationTime,
                age,
                postId
            )
        }
    }

}