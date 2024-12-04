package com.pulsedonor.app.ui

import androidx.lifecycle.ViewModel
import com.pulsedonor.app.api_service.ApiService
import com.pulsedonor.app.data.AppPreferences
import javax.inject.Inject

class MainViewModel @Inject constructor(val appPreferences: AppPreferences, val apiService: ApiService): ViewModel() {


}