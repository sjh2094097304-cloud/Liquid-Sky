package com.liquidsky.weather.data.repository

import com.liquidsky.weather.data.api.QWeatherApi
import com.liquidsky.weather.domain.model.*
import com.liquidsky.weather.domain.repository.WeatherRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: QWeatherApi,
    private val apiKey: String
) : WeatherRepository {

    override suspend fun getWeatherInfo(lon: Double, lat: Double): Result<WeatherInfo> {
        return try {
            val location = "$lon,$lat"
            val nowResp = api.getNowWeather(location, apiKey)
            val dailyResp = api.getDailyForecast(location, apiKey)
            val cityResp = api.lookupCity(location, apiKey)

            if (nowResp.code != "200") {
                return Result.failure(Exception("API错误码: ${nowResp.code}"))
            }

            val cityName = cityResp.location.firstOrNull()?.name ?: "未知城市"
            val today = dailyResp.daily.firstOrNull()

            Result.success(
                WeatherInfo(
                    cityName = cityName,
                    temperature = nowResp.now.temp.toIntOrNull() ?: 0,
                    feelsLike = nowResp.now.feelsLike.toIntOrNull() ?: 0,
                    weatherText = nowResp.now.text,
                    weatherIconCode = nowResp.now.icon,
                    humidity = nowResp.now.humidity.toIntOrNull() ?: 0,
                    windDir = nowResp.now.windDir,
                    windScale = nowResp.now.windScale,
                    pressure = nowResp.now.pressure.toIntOrNull() ?: 0,
                    visibility = nowResp.now.vis.toIntOrNull() ?: 0,
                    updateTime = nowResp.updateTime,
                    highTemp = today?.tempMax?.toIntOrNull() ?: 0,
                    lowTemp = today?.tempMin?.toIntOrNull() ?: 0
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getHourlyForecast(lon: Double, lat: Double): Result<List<HourlyForecast>> {
        return try {
            val resp = api.getHourlyForecast("$lon,$lat", apiKey)
            if (resp.code != "200") return Result.failure(Exception("API错误码: ${resp.code}"))

            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mmXXX", Locale.CHINA)
            val outSdf = SimpleDateFormat("HH:mm", Locale.CHINA)

            Result.success(
                resp.hourly.map { dto ->
                    val time = try {
                        outSdf.format(sdf.parse(dto.fxTime)!!)
                    } catch (_: Exception) {
                        dto.fxTime
                    }
                    HourlyForecast(
                        time = time,
                        temp = dto.temp.toIntOrNull() ?: 0,
                        iconCode = dto.icon,
                        weatherText = dto.text,
                        pop = dto.pop.toIntOrNull() ?: 0
                    )
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getDailyForecast(lon: Double, lat: Double): Result<List<DailyForecast>> {
        return try {
            val resp = api.getDailyForecast("$lon,$lat", apiKey)
            if (resp.code != "200") return Result.failure(Exception("API错误码: ${resp.code}"))

            val weekDays = arrayOf("周日", "周一", "周二", "周三", "周四", "周五", "周六")
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)

            Result.success(
                resp.daily.mapIndexed { index, dto ->
                    val cal = Calendar.getInstance()
                    val date = try {
                        sdf.parse(dto.fxDate)!!
                    } catch (_: Exception) {
                        Date()
                    }
                    cal.time = date
                    val weekDay = if (index == 0) "今天" else weekDays[cal.get(Calendar.DAY_OF_WEEK) - 1]

                    DailyForecast(
                        date = dto.fxDate.substring(5),
                        weekDay = weekDay,
                        iconDayCode = dto.iconDay,
                        textDay = dto.textDay,
                        iconNightCode = dto.iconNight,
                        textNight = dto.textNight,
                        tempMax = dto.tempMax.toIntOrNull() ?: 0,
                        tempMin = dto.tempMin.toIntOrNull() ?: 0,
                        humidity = dto.humidity.toIntOrNull() ?: 0,
                        windDir = dto.windDirDay,
                        windScale = dto.windScaleDay
                    )
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
