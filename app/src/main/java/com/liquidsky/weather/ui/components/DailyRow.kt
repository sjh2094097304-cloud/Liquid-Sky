package com.liquidsky.weather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.liquidsky.weather.domain.model.DailyForecast
import com.liquidsky.weather.utils.WeatherIconMapper

/**
 * 7日预报区域
 */
@Composable
fun DailyForecastSection(dailyList: List<DailyForecast>) {
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "7日预报",
            color = Color(0xCCFFFFFF),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            userScrollEnabled = false
        ) {
            items(dailyList) { day ->
                DailyItem(day)
                if (day != dailyList.last()) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.5.dp)
                            .background(Color(0x22FFFFFF))
                    )
                }
            }
        }
    }
}

@Composable
private fun DailyItem(day: DailyForecast) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 星期
        Text(
            text = day.weekDay,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.width(50.dp)
        )
        // 日期
        Text(
            text = day.date,
            color = Color(0xAAFFFFFF),
            fontSize = 13.sp,
            modifier = Modifier.width(45.dp)
        )
        // 白天图标
        Icon(
            imageVector = WeatherIconMapper.mapToIcon(day.iconDayCode),
            contentDescription = day.textDay,
            tint = Color.White,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        // 最低温
        Text(
            text = "${day.tempMin}°",
            color = Color(0xAAFFFFFF),
            fontSize = 16.sp,
            modifier = Modifier.width(40.dp)
        )
        // 温度条
        Box(
            modifier = Modifier
                .weight(1f)
                .height(4.dp)
                .background(
                    Color(0x33FFFFFF),
                    shape = CircleShape
                )
        )
        // 最高温
        Text(
            text = "${day.tempMax}°",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .width(40.dp)
                .wrapContentWidth(Alignment.End)
        )
    }
}
