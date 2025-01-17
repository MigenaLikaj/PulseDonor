package com.pulsedonor.app.ui.dashboard.map

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.pulsedonor.app.R
import com.pulsedonor.app.base.fragment.BaseFragment
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.BsdMapBinding
import com.pulsedonor.app.databinding.MapFragmentBinding
import com.pulsedonor.app.models.Bloodpoints
import com.pulsedonor.app.ui.MainViewModel
import java.util.Locale
import javax.inject.Inject

class MapFragment : BaseFragment<MapFragmentBinding>(), OnMapReadyCallback {

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var map: GoogleMap
    private var pinlist = ArrayList<Bloodpoints?>()
    private val TAG = MapFragment::class.java.simpleName
    private val REQUEST_LOCATION_PERMISSION = 1
    lateinit var bsdMap: BottomSheetDialog
    private var long = 0.0
    private var lat = 0.0

    override fun inflateBinding(layoutInflater: LayoutInflater): MapFragmentBinding =
        MapFragmentBinding.inflate(layoutInflater)

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewModel.getBloodDonationPoints()
    }

    override fun observeViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.getBloodDonationPoints.observe(this) {
            it.data?.let { points ->
                pinlist.clear()
                pinlist.addAll(points)
            }
            updateMapMarkers()
        }
    }

    private fun updateMapMarkers() {
        if (::map.isInitialized) {
            map.clear()
            pinlist.forEach { bloodpoint ->
                bloodpoint?.let {
                    val location = LatLng(it.latitude!!.toDouble(), it.longitude!!.toDouble())
                    map.addMarker(
                        MarkerOptions()
                            .position(location)
                            .title(it.addesss)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    )
                }
            }

            if (pinlist.isNotEmpty()) {
                val firstLocation = LatLng(
                    pinlist[0]?.latitude?.toDouble() ?: 0.0,
                    pinlist[0]?.longitude?.toDouble() ?: 0.0
                )
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, 15f))
            }
        }
    }

    override fun onClicks() {}

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.mapType = GoogleMap.MAP_TYPE_NORMAL

        updateMapMarkers()
        setMapLongClick(map)
        setPoiClick(map)
        setMapStyle(map)
        enableMyLocation()
    }

    private fun setMapLongClick(map: GoogleMap) {
        map.setOnMapLongClickListener { latLng ->
            val snippet = String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                latLng.latitude,
                latLng.longitude
            )
            map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(getString(R.string.dropped_pin))
                    .snippet(snippet)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )
        }
    }

    private fun setPoiClick(map: GoogleMap) {
        map.setOnMarkerClickListener { marker ->
            bsdMap()
            false
        }
    }

    private fun setMapStyle(map: GoogleMap) {
        try {
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    requireContext(),
                    R.raw.map_style
                )
            )
            if (!success) {
                Log.e(TAG, "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e(TAG, "Can't find style. Error: ", e)
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            map.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
            }
        }
    }

    private fun bsdMap() {
        bsdMap = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val inflater = LayoutInflater.from(requireContext())
        val bindingBsd: BsdMapBinding = BsdMapBinding.inflate(inflater)
        bsdMap.setContentView(bindingBsd.root)
        bsdMap.setOnShowListener {
            val bottom_sheet = bsdMap.findViewById<ConstraintLayout>(R.id.clBsdPin)
            BottomSheetBehavior.from(bottom_sheet!!).setState(BottomSheetBehavior.STATE_EXPANDED)
        }

        bindingBsd.clBsdPin.setOnClickListener {
            hideSoftKeyboardBottomSheet(it)
        }

        bindingBsd.btnOpenWithGoogleMaps.setOnClickListener {
            val uri = "https://www.google.com.tw/maps/place/$lat,$long"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
            bsdMap.dismiss()
        }
        bsdMap.show()
    }

    private fun hideSoftKeyboardBottomSheet(view: View) {
        (requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(view.windowToken, 0)
    }
}
