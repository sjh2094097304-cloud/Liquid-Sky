package com.liquidsky.weather.data.di

import com.liquidsky.weather.BuildConfig
import com.liquidsky.weather.data.api.QWeatherApi
import com.liquidsky.weather.data.repository.WeatherRepositoryImpl
import com.liquidsky.weather.domain.repository.WeatherRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // 和风天气 API Host
    // 免费开发版: https://devapi.qweather.com/
    // 商业版: 使用控制台分配的专属域名
    private const val BASE_URL = "https://devapi.qweather.com/"

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideQWeatherApi(retrofit: Retrofit): QWeatherApi =
        retrofit.create(QWeatherApi::class.java)

    @Provides
    @Singleton
    fun provideApiKey(): String = BuildConfig.QWEATHER_API_KEY

    @Provides
    @Singleton
    fun provideWeatherRepository(
        api: QWeatherApi,
        apiKey: String
    ): WeatherRepository = WeatherRepositoryImpl(api, apiKey)
}
