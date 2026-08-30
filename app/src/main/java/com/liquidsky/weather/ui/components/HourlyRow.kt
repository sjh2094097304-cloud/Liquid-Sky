package com.liquidsky.weather.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.liquidsky.weather.domain.model.HourlyForecast
import com.liquidsky.weather.utils.WeatherIconMapper

/**
 * 逐小时预报区域（横向滚动）
 */
@Composable
fun HourlyForecastSection(hourlyList: List<HourlyForecast>) {
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "逐小时预报",
            color = Color(0xCCFFFFFF),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(hourlyList) { hour ->
                HourlyItem(hour)
            }
        }
    }
}

@Composable
private fun HourlyItem(hour: HourlyForecast) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = hour.time,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Icon(
            imageVector = WeatherIconMapper.mapToIcon(hour.iconCode),
            contentDescription = hour.weatherText,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        if (hour.pop > 0) {
            Text(
                text = "${hour.pop}%",
                color = Color(0xFF81D4FA),
                fontSize = 11.sp
            )
        }
        Text(
            text = "${hour.temp}°",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
