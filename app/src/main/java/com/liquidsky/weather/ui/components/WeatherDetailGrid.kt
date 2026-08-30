package com.liquidsky.weather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Compress
import androidx.compose.material.icons.filled.DeviceThermostat
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 天气详细信息网格
 *
 * 显示：
 * - 湿度
 * - 风向 / 风力
 * - 气压
 * - 能见度
 * - 体感温度
 * - 紫外线
 */
@Composable
fun WeatherDetailGrid(
    humidity: Int,
    windDir: String,
    windScale: String,
    pressure: Int,
    visibility: Int,
    feelsLike: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // 第一行：湿度 / 风向
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            DetailCard(
                icon = Icons.Filled.WaterDrop,
                title = "湿度",
                value = "$humidity%",
                modifier = Modifier.weight(1f)
            )

            DetailCard(
                icon = Icons.Filled.Air,
                title = "风向",
                value = "$windDir $windScale级",
                modifier = Modifier.weight(1f)
            )
        }

        // 第二行：气压 / 能见度
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            DetailCard(
                icon = Icons.Filled.Compress,
                title = "气压",
                value = "$pressure hPa",
                modifier = Modifier.weight(1f)
            )

            DetailCard(
                icon = Icons.Filled.Visibility,
                title = "能见度",
                value = "$visibility km",
                modifier = Modifier.weight(1f)
            )
        }

        // 第三行：体感温度 / 紫外线
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            DetailCard(
                icon = Icons.Filled.DeviceThermostat,
                title = "体感温度",
                value = "$feelsLike°",
                modifier = Modifier.weight(1f)
            )

            DetailCard(
                icon = Icons.Filled.WbSunny,
                title = "紫外线",
                value = "中等",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun DetailCard(
    icon: ImageVector,
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    GlassCard(
        modifier = modifier
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color(0xCCFFFFFF),
                modifier = Modifier.size(16.dp)
            )

            Spacer(
                modifier = Modifier.width(6.dp)
            )

            Text(
                text = title,
                color = Color(0xCCFFFFFF),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = value,
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}