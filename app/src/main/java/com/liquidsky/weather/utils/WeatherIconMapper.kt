package com.liquidsky.weather.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 和风天气图标 code 映射
 * 图标代码表: https://dev.qweather.com/docs/resource/icons/
 */
object WeatherIconMapper {

    fun mapToIcon(code: String): ImageVector {
        return when (code) {
            // 晴
            "100" -> Icons.Filled.WbSunny
            // 多云 / 少云
            "101", "102", "103" -> Icons.Filled.WbCloudy
            // 阴
            "104" -> Icons.Filled.Cloud
            // 雨
            "300", "301", "302", "303", "304", "305", "306", "307", "308", "309",
            "310", "311", "312", "313", "314", "315", "316", "317", "318", "350",
            "351", "399" -> Icons.Filled.Grain
            // 雪
            "400", "401", "402", "403", "404", "405", "406", "407", "408", "409",
            "410", "456", "457", "499" -> Icons.Filled.AcUnit
            // 雾 / 霾
            "500", "501", "502", "503", "504", "505", "506", "507", "508", "509",
            "510", "511", "512", "513", "514", "515", "599" -> Icons.Filled.Foggy
            // 沙尘
            "600", "601", "602" -> Icons.Filled.FilterDrama
            else -> Icons.Filled.WbSunny
        }
    }

    /**
     * 根据天气 code 和是否夜间选择背景渐变色
     */
    fun mapToBackground(code: String, isNight: Boolean): List<Color> {
        return when (code) {
            "100" -> if (isNight) SunnyNight else SunnyDay
            "101", "102", "103", "104" -> if (isNight) CloudyNight else CloudyDay
            in "300".."399" -> Rainy
            in "400".."499" -> Snowy
            in "500".."599" -> FoggyBg
            else -> if (isNight) SunnyNight else SunnyDay
        }
    }

    // 背景渐变色
    private val SunnyDay = listOf(Color(0xFF4A90D9), Color(0xFF87CEEB))
    private val SunnyNight = listOf(Color(0xFF0F0C29), Color(0xFF302B63))
    private val CloudyDay = listOf(Color(0xFF5C6BC0), Color(0xFF7E9CDB))
    private val CloudyNight = listOf(Color(0xFF2C3E50), Color(0xFF34495E))
    private val Rainy = listOf(Color(0xFF37474F), Color(0xFF546E7A))
    private val Snowy = listOf(Color(0xFF90A4AE), Color(0xFFCFD8DC))
    private val FoggyBg = listOf(Color(0xFF455A64), Color(0xFF607D8B))
}
