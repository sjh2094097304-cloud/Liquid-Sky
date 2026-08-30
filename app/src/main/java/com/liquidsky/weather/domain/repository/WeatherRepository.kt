package com.liquidsky.weather.domain.repository

import com.liquidsky.weather.domain.model.*

interface WeatherRepository {
    suspend fun getWeatherInfo(lon: Double, lat: Double): Result<WeatherInfo>
    suspend fun getHourlyForecast(lon: Double, lat: Double): Result<List<HourlyForecast>>
    suspend fun getDailyForecast(lon: Double, lat: Double): Result<List<DailyForecast>>
}
