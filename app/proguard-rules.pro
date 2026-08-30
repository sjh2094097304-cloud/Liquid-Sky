# Add project specific ProGuard rules here.
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

# Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }

# Retrofit
-keepattributes Signature
-keepattributes Exceptions
-keep class com.liquidsky.weather.data.model.** { *; }

# Moshi
-keepclasseswithmembers class * {
    @com.squareup.moshi.* <fields>;
}
-keepclassmembers class * {
    @com.squareup.moshi.FromJson *;
    @com.squareup.moshi.ToJson *;
}

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**
