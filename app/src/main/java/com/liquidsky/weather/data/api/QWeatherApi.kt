package com.liquidsky.weather.data.api

import com.liquidsky.weather.data.model.*
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 和风天气 API 接口定义
 * 文档: https://dev.qweather.com/docs/api/
 */
interface QWeatherApi {

    /** 实时天气 */
    @GET("v7/weather/now")
    suspend fun getNowWeather(
        @Query("location") location: String,
        @Query("key") key: String
    ): NowWeatherResponse

    /** 逐小时预报（24小时） */
    @GET("v7/weather/24h")
    suspend fun getHourlyForecast(
        @Query("location") location: String,
        @Query("key") key: String
    ): HourlyResponse

    /** 7天预报 */
    @GET("v7/weather/7d")
    suspend fun getDailyForecast(
        @Query("location") location: String,
        @Query("key") key: String
    ): DailyResponse

    /** 城市搜索（经纬度反查城市名） */
    @GET("v2/city/lookup")
    suspend fun lookupCity(
        @Query("location") location: String,
        @Query("key") key: String
    ): CityLookupResponse
}
