package com.pulsedonor.app.ui.dashboard.hall_of_fame

import android.graphics.Color
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.pulsedonor.app.adapters.hall_of_fame.DonorsAdapter
import com.pulsedonor.app.base.fragment.BaseFragment
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.HallOfFameFragmentBinding
import com.pulsedonor.app.models.hall_of_fame.DonorsResponse
import com.pulsedonor.app.ui.MainViewModel
import javax.inject.Inject

class HallOfFameFragment : BaseFragment<HallOfFameFragmentBinding>() {

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory
    private lateinit var viewModel: MainViewModel
    lateinit var donorsAdapter: DonorsAdapter
    lateinit var topDonorsAdapter: DonorsAdapter
    private var donorsList = ArrayList<DonorsResponse?>()
    private var topDonorsList = ArrayList<DonorsResponse?>()

    override fun inflateBinding(layoutInflater: LayoutInflater): HallOfFameFragmentBinding =
        HallOfFameFragmentBinding.inflate(layoutInflater)

    override fun observeViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun initViews() {
        // Initialize RecyclerView for donors and top donors
        setupDonorsRecyclerView()

        // Populate charts with dummy data
        setupLineChart()
        setupPieChart()
    }

    private fun setupDonorsRecyclerView() {
        donorsAdapter = DonorsAdapter()
        binding.rvDonors.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = donorsAdapter
        }

        donorsList = arrayListOf(
            DonorsResponse("Alexa Bob", "https://randomuser.me/api/portraits/men/1.jpg", 0),
            DonorsResponse("Leo Brbe", "https://randomuser.me/api/portraits/men/3.jpg", 0),
            DonorsResponse("Siri Jcobs", "https://randomuser.me/api/portraits/women/4.jpg", 0),
        )
        donorsAdapter.submitList(donorsList)

        topDonorsAdapter = DonorsAdapter()
        binding.rvTopDonors.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = topDonorsAdapter
        }

        topDonorsList = arrayListOf(
            DonorsResponse("Arber Mirena", "https://randomuser.me/api/portraits/men/1.jpg", 1),
            DonorsResponse("Blend Hasani", "https://randomuser.me/api/portraits/men/3.jpg", 2),
            DonorsResponse("Migena Likaj", "https://randomuser.me/api/portraits/women/4.jpg", 3),
        )
        topDonorsAdapter.submitList(topDonorsList)
    }

    private fun setupLineChart() {
        val entries = mutableListOf<Entry>()
        val labels = listOf("Jan", "Feb", "Mar", "Apr", "May")

        for (i in labels.indices) {
            entries.add(Entry(i.toFloat(), (10..50).random().toFloat()))
        }

        val lineDataSet = LineDataSet(entries, "Donations Over Time")
        lineDataSet.color = Color.GREEN
        lineDataSet.circleRadius = 5f
        lineDataSet.setCircleColor(Color.RED)
        lineDataSet.lineWidth = 2f

        val lineData = LineData(lineDataSet)

        binding.lineChartDonations.apply {
            data = lineData
            description.text = "Monthly Donations"
            setTouchEnabled(true)
            setPinchZoom(true)
            invalidate()
        }
    }

    private fun setupPieChart() {
        val entries = listOf(
            PieEntry(40f, "A"),
            PieEntry(30f, "B"),
            PieEntry(20f, "O"),
            PieEntry(10f, "AB")
        )

        val pieDataSet = PieDataSet(entries, "Blood Group Distribution")
        pieDataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        pieDataSet.valueTextColor = Color.BLACK
        pieDataSet.valueTextSize = 12f

        val pieData = PieData(pieDataSet)

        binding.pieChartBloodGroups.apply {
            data = pieData
            description.text = "Blood Group %"
            isDrawHoleEnabled = true
            setEntryLabelColor(Color.BLACK)
            setUsePercentValues(true)
            legend.orientation = Legend.LegendOrientation.HORIZONTAL
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            invalidate()
        }
    }

    override fun onClicks() {
    }
}
