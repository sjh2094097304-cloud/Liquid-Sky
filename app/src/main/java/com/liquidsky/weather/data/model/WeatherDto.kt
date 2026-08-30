package com.liquidsky.weather.data.model

import com.squareup.moshi.JsonClass

// ========== 实时天气 ==========

@JsonClass(generateAdapter = true)
data class NowWeatherResponse(
    val code: String,
    val now: NowWeatherDto,
    val updateTime: String
)

@JsonClass(generateAdapter = true)
data class NowWeatherDto(
    val obsTime: String,
    val temp: String,
    val feelsLike: String,
    val icon: String,
    val text: String,
    val wind360: String,
    val windDir: String,
    val windScale: String,
    val windSpeed: String,
    val humidity: String,
    val precip: String,
    val pressure: String,
    val vis: String,
    val cloud: String,
    val dew: String
)

// ========== 逐小时预报 ==========

@JsonClass(generateAdapter = true)
data class HourlyResponse(
    val code: String,
    val hourly: List<HourlyDto>,
    val updateTime: String
)

@JsonClass(generateAdapter = true)
data class HourlyDto(
    val fxTime: String,
    val temp: String,
    val icon: String,
    val text: String,
    val wind360: String,
    val windDir: String,
    val windScale: String,
    val windSpeed: String,
    val humidity: String,
    val pop: String,
    val precip: String,
    val pressure: String,
    val cloud: String,
    val dew: String
)

// ========== 7天预报 ==========

@JsonClass(generateAdapter = true)
data class DailyResponse(
    val code: String,
    val daily: List<DailyDto>,
    val updateTime: String
)

@JsonClass(generateAdapter = true)
data class DailyDto(
    val fxDate: String,
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String,
    val moonPhase: String,
    val moonPhaseIcon: String,
    val tempMax: String,
    val tempMin: String,
    val iconDay: String,
    val textDay: String,
    val iconNight: String,
    val textNight: String,
    val wind360Day: String,
    val windDirDay: String,
    val windScaleDay: String,
    val windSpeedDay: String,
    val wind360Night: String,
    val windDirNight: String,
    val windScaleNight: String,
    val windSpeedNight: String,
    val humidity: String,
    val precip: String,
    val uvIndex: String,
    val pressure: String,
    val vis: String,
    val cloud: String,
    val dew: String
)

// ========== 城市查询 ==========

@JsonClass(generateAdapter = true)
data class CityLookupResponse(
    val code: String,
    val location: List<CityDto>
)

@JsonClass(generateAdapter = true)
data class CityDto(
    val name: String,
    val id: String,
    val lat: String,
    val lon: String,
    val adm2: String,
    val adm1: String,
    val country: String,
    val tz: String,
    val utcOffset: String,
    val isDst: String,
    val type: String,
    val rank: String,
    val fxLink: String
)
