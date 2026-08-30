# LiquidSky 天气

一款仿照 iOS 天气风格的 Android 天气应用，使用 Jetpack Compose 构建，基于和风天气 API 提供数据。

## 功能特性

- 实时天气展示（温度、体感、天气状况）
- 24 小时逐小时预报（横向滚动）
- 7 天天气预报
- 详细气象数据（湿度、风向、气压、能见度、体感温度）
- iOS 风格毛玻璃卡片 UI
- 动态渐变背景（根据天气状况和昼夜自动切换）
- 基于位置的自动定位
- 浅色/深色主题适配

## 技术栈

| 模块 | 技术 |
|------|------|
| 语言 | Kotlin |
| UI | Jetpack Compose (Material 3) |
| 架构 | MVVM + Clean Architecture |
| 依赖注入 | Hilt |
| 网络 | Retrofit + OkHttp + Moshi |
| 异步 | Kotlin Coroutines + Flow |
| 定位 | Google Play Services Location |
| 图片加载 | Coil |
| 最低 SDK | API 26 (Android 8.0) |
| 目标 SDK | API 35 |

## 项目结构

```
app/src/main/java/com/liquidsky/weather/
├── WeatherApp.kt              # Application 入口
├── MainActivity.kt            # 主 Activity
├── data/
│   ├── api/QWeatherApi.kt     # 和风天气 API 接口
│   ├── model/WeatherDto.kt    # 网络响应数据模型
│   ├── repository/            # 仓库实现
│   └── di/NetworkModule.kt    # 网络依赖注入
├── domain/
│   ├── model/                 # 领域模型
│   └── repository/            # 仓库接口
├── ui/
│   ├── theme/                 # 主题、颜色、字体
│   ├── components/            # 可复用 UI 组件
│   └── home/                  # 主界面
└── utils/
    └── WeatherIconMapper.kt   # 天气图标与背景映射
```

## 快速开始

### 1. 环境要求

- Android Studio Hedgehog (2023.1.1) 或更高版本
- JDK 17
- Android SDK 35

### 2. 获取和风天气 API Key

1. 访问 [和风天气控制台](https://console.qweather.com/)
2. 注册账号并完成个人实名认证（免费开发版 16700 次/天）
3. 创建项目，添加 API KEY 凭据
4. 开通免费开发版订阅

### 3. 配置 API Key

打开 `app/build.gradle.kts`，找到以下行，替换为你的 API Key：

```kotlin
buildConfigField("String", "QWEATHER_API_KEY", "\"YOUR_QWEATHER_API_KEY\"")
```

> **安全提示**：生产环境建议将 API Key 放在后端服务中，客户端通过后端代理请求，避免 Key 被逆向提取。

### 4. 构建运行

1. 用 Android Studio 打开项目
2. 等待 Gradle Sync 完成
3. 连接 Android 设备或启动模拟器
4. 点击 Run 按钮

## 天气图标映射

应用使用 Material Icons 内置图标映射和风天气图标代码：

| 天气状况 | 和风 Code | 图标 |
|----------|-----------|------|
| 晴 | 100 | WbSunny |
| 多云/少云 | 101-103 | WbCloudy |
| 阴 | 104 | Cloud |
| 雨 | 300-399 | Grain |
| 雪 | 400-499 | AcUnit |
| 雾/霾 | 500-599 | Foggy |
| 沙尘 | 600-602 | FilterDrama |

完整图标代码表参考 [和风天气图标文档](https://dev.qweather.com/docs/resource/icons/)。

## 背景渐变

应用根据天气状况和当前时间自动切换背景渐变色：

- 晴天白天：蓝色渐变
- 晴天夜间：深蓝紫色渐变
- 多云白天：蓝紫色渐变
- 多云夜间：深灰蓝渐变
- 雨天：深灰渐变
- 雪天：浅灰蓝渐变
- 雾天：灰蓝渐变

## 后续扩展方向

- [ ] 多城市管理与切换
- [ ] 下拉刷新
- [ ] Lottie 天气动画背景
- [ ] 灾害预警推送
- [ ] 空气质量 (AQI) 展示
- [ ] 生活指数（穿衣、紫外线、运动）
- [ ] 城市搜索
- [ ] 桌面小组件 (Glance)
- [ ] 通知栏天气
- [ ] 真实定位集成（当前使用默认坐标兜底）

## 贡献

欢迎提交 Issue 和 Pull Request！

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 许可证

本项目仅供学习交流使用。

## 致谢

- [和风天气](https://www.qweather.com/) - 提供天气数据 API
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - 现代 Android UI 工具包
- [Hilt](https://dagger.dev/hilt/) - 依赖注入框架
- [Retrofit](https://square.github.io/retrofit/) - 网络请求库
