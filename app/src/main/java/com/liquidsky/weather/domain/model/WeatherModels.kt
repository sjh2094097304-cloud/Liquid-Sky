package com.liquidsky.weather.domain.model

/**
 * 实时天气信息（领域模型）
 */
data class WeatherInfo(
    val cityName: String,
    val temperature: Int,
    val feelsLike: Int,
    val weatherText: String,
    val weatherIconCode: String,
    val humidity: Int,
    val windDir: String,
    val windScale: String,
    val pressure: Int,
    val visibility: Int,
    val updateTime: String,
    val highTemp: Int,
    val lowTemp: Int
)

/**
 * 逐小时预报
 */
data class HourlyForecast(
    val time: String,
    val temp: Int,
    val iconCode: String,
    val weatherText: String,
    val pop: Int
)

/**
 * 每日预报
 */
data class DailyForecast(
    val date: String,
    val weekDay: String,
    val iconDayCode: String,
    val textDay: String,
    val iconNightCode: String,
    val textNight: String,
    val tempMax: Int,
    val tempMin: Int,
    val humidity: Int,
    val windDir: String,
    val windScale: String
)
