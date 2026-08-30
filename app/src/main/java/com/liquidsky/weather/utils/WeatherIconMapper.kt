package com.liquidsky.weather.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterDrama
import androidx.compose.material.icons.filled.Grain
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.WbCloudy
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 和风天气图标 Code 映射
 *
 * QWeather 图标代码：
 * https://dev.qweather.com/docs/resource/icons/
 *
 * 当前阶段使用 Material Icons。
 * 后续 Liquid Sky 将替换为自己的天气动态图标系统。
 */
object WeatherIconMapper {

    /**
     * 根据和风天气 weather code 获取 Material Icon。
     */
    fun mapToIcon(code: String): ImageVector {

        return when (code) {

            // ====================================================
            // 晴
            // ====================================================

            "100" -> Icons.Filled.WbSunny

            // ====================================================
            // 多云 / 少云
            // ====================================================

            "101",
            "102",
            "103" -> Icons.Filled.WbCloudy

            // ====================================================
            // 阴
            // ====================================================

            "104" -> Icons.Filled.Cloud

            // ====================================================
            // 雨
            // ====================================================

            "300",
            "301",
            "302",
            "303",
            "304",
            "305",
            "306",
            "307",
            "308",
            "309",
            "310",
            "311",
            "312",
            "313",
            "314",
            "315",
            "316",
            "317",
            "318",
            "350",
            "351",
            "399" -> Icons.Filled.Grain

            // ====================================================
            // 雪
            // ====================================================

            "400",
            "401",
            "402",
            "403",
            "404",
            "405",
            "406",
            "407",
            "408",
            "409",
            "410",
            "456",
            "457",
            "499" -> Icons.Filled.AcUnit

            // ====================================================
            // 雾 / 霾
            //
            // Material Icons 没有 Foggy，
            // 使用 FilterDrama 作为当前阶段替代图标。
            // 后续会替换成 Liquid Sky 自定义天气图标。
            // ====================================================

            "500",
            "501",
            "502",
            "503",
            "504",
            "505",
            "506",
            "507",
            "508",
            "509",
            "510",
            "511",
            "512",
            "513",
            "514",
            "515",
            "599" -> Icons.Filled.FilterDrama

            // ====================================================
            // 沙尘
            // ====================================================

            "600",
            "601",
            "602" -> Icons.Filled.FilterDrama

            // ====================================================
            // 默认
            // ====================================================

            else -> Icons.Filled.WbSunny
        }
    }

    /**
     * 根据天气 Code 和昼夜状态
     * 返回 Liquid Sky 当前阶段使用的背景渐变。
     */
    fun mapToBackground(
        code: String,
        isNight: Boolean
    ): List<Color> {

        return when (code) {

            // 晴天
            "100" -> {
                if (isNight) {
                    SunnyNight
                } else {
                    SunnyDay
                }
            }

            // 多云 / 少云 / 阴
            "101",
            "102",
            "103",
            "104" -> {
                if (isNight) {
                    CloudyNight
                } else {
                    CloudyDay
                }
            }

            // 雨
            in "300".."399" -> Rainy

            // 雪
            in "400".."499" -> Snowy

            // 雾 / 霾
            in "500".."599" -> FoggyBg

            // 沙尘
            in "600".."699" -> Dusty

            // 默认
            else -> {
                if (isNight) {
                    SunnyNight
                } else {
                    SunnyDay
                }
            }
        }
    }

    // ============================================================
    // 天气背景
    // ============================================================

    /**
     * 晴天
     */
    private val SunnyDay = listOf(
        Color(0xFF4A90D9),
        Color(0xFF87CEEB)
    )

    /**
     * 晴天夜间
     */
    private val SunnyNight = listOf(
        Color(0xFF0F0C29),
        Color(0xFF302B63)
    )

    /**
     * 多云白天
     */
    private val CloudyDay = listOf(
        Color(0xFF5C6BC0),
        Color(0xFF7E9CDB)
    )

    /**
     * 多云夜间
     */
    private val CloudyNight = listOf(
        Color(0xFF2C3E50),
        Color(0xFF34495E)
    )

    /**
     * 雨天
     */
    private val Rainy = listOf(
        Color(0xFF37474F),
        Color(0xFF546E7A)
    )

    /**
     * 雪天
     */
    private val Snowy = listOf(
        Color(0xFF90A4AE),
        Color(0xFFCFD8DC)
    )

    /**
     * 雾 / 霾
     */
    private val FoggyBg = listOf(
        Color(0xFF455A64),
        Color(0xFF607D8B)
    )

    /**
     * 沙尘
     */
    private val Dusty = listOf(
        Color(0xFF795548),
        Color(0xFFBCAAA4)
    )
}