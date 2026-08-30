package com.liquidsky.weather.ui.home

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.*
import com.liquidsky.weather.domain.model.*
import com.liquidsky.weather.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val weather: WeatherInfo? = null,
    val hourly: List<HourlyForecast> = emptyList(),
    val daily: List<DailyForecast> = emptyList(),
    val location: Pair<Double, Double>? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    // 默认北京坐标，定位失败时兜底
    private val defaultLocation = 116.40 to 39.90

    fun loadWeather(lon: Double, lat: Double) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            val weatherResult = repository.getWeatherInfo(lon, lat)
            val hourlyResult = repository.getHourlyForecast(lon, lat)
            val dailyResult = repository.getDailyForecast(lon, lat)

            _uiState.value = HomeUiState(
                isLoading = false,
                weather = weatherResult.getOrNull(),
                hourly = hourlyResult.getOrDefault(emptyList()),
                daily = dailyResult.getOrDefault(emptyList()),
                location = lon to lat,
                error = if (weatherResult.isFailure) {
                    weatherResult.exceptionOrNull()?.message ?: "加载失败"
                } else null
            )
        }
    }

    fun loadWithDefault() {
        loadWeather(defaultLocation.first, defaultLocation.second)
    }

    /**
     * 请求真实定位
     */
    @SuppressLint("MissingPermission")
    fun requestLocation(client: FusedLocationProviderClient) {
        client.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                loadWeather(location.longitude, location.latitude)
            } else {
                loadWithDefault()
            }
        }.addOnFailureListener {
            loadWithDefault()
        }
    }
}
