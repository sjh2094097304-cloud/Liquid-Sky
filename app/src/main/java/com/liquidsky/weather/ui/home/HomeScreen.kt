package com.liquidsky.weather.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.liquidsky.weather.domain.model.DailyForecast
import com.liquidsky.weather.domain.model.HourlyForecast
import com.liquidsky.weather.domain.model.WeatherInfo
import com.liquidsky.weather.ui.components.*
import com.liquidsky.weather.utils.WeatherIconMapper
import java.util.Calendar

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val locationPermission = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    LaunchedEffect(Unit) {
        if (!locationPermission.status.isGranted) {
            locationPermission.launchPermissionRequest()
        }
        // 先用默认坐标加载，定位成功后会自动刷新
        viewModel.loadWithDefault()
    }

    val isNight = remember {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        hour < 6 || hour >= 18
    }

    val bgColors = remember(uiState.weather?.weatherIconCode) {
        WeatherIconMapper.mapToBackground(
            uiState.weather?.weatherIconCode ?: "100",
            isNight
        )
    }

    WeatherBackground(colors = bgColors) {
        when {
            uiState.isLoading -> LoadingView()
            uiState.error != null -> ErrorView(
                error = uiState.error!!,
                onRetry = { viewModel.loadWithDefault() }
            )
            uiState.weather != null -> WeatherContent(
                weather = uiState.weather!!,
                hourly = uiState.hourly,
                daily = uiState.daily
            )
        }
    }
}

@Composable
private fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.White)
    }
}

@Composable
private fun ErrorView(error: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "加载失败",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = error,
            color = Color(0xCCFFFFFF),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onRetry) {
            Text("重试")
        }
    }
}

@Composable
private fun WeatherContent(
    weather: WeatherInfo,
    hourly: List<HourlyForecast>,
    daily: List<DailyForecast>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 48.dp, bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 城市名
        Text(
            text = weather.cityName,
            color = Color.White,
            fontSize = 36.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))

        // 当前温度（iOS 超大字体）
        Text(
            text = "${weather.temperature}°",
            color = Color.White,
            fontSize = 96.sp,
            fontWeight = FontWeight.Thin,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))

        // 天气描述
        Text(
            text = weather.weatherText,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(4.dp))

        // 最高最低温
        Text(
            text = "最高 ${weather.highTemp}°  最低 ${weather.lowTemp}°",
            color = Color(0xCCFFFFFF),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(32.dp))

        // 逐小时预报
        if (hourly.isNotEmpty()) {
            HourlyForecastSection(hourly)
            Spacer(modifier = Modifier.height(16.dp))
        }

        // 7天预报
        if (daily.isNotEmpty()) {
            DailyForecastSection(daily)
            Spacer(modifier = Modifier.height(16.dp))
        }

        // 详细信息网格
        WeatherDetailGrid(
            humidity = weather.humidity,
            windDir = weather.windDir,
            windScale = weather.windScale,
            pressure = weather.pressure,
            visibility = weather.visibility,
            feelsLike = weather.feelsLike
        )
    }
}
