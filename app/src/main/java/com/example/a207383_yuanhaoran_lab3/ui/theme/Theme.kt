package com.example.a207383_yuanhaoran_lab3.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// 【Lab 3 考点：Material Theme Builder 同步修改】
// 使用 Color.kt 中新生成的“生态绿”暗黑变量来配置调色板
private val DarkColorScheme = darkColorScheme(
    primary = primaryDark,           // 使用新的主色调 (生态绿)
    onPrimary = onPrimaryDark,       // 主色调上的文字颜色
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,       // 辅助色
    onSecondary = onSecondaryDark,
    tertiary = tertiaryDark,         // 第三色
    onTertiary = onTertiaryDark,
    error = errorDark,               // 错误颜色
    onError = onErrorDark,
    background = backgroundDark,     // App 整体背景
    onBackground = onBackgroundDark, // 背景上的文字颜色
    surface = surfaceDark,           // 卡片、对话框表面颜色
    onSurface = onSurfaceDark,       // 表面上的文字颜色
    surfaceVariant = surfaceVariantDark, // 卡片变体颜色
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark            // 边框线颜色
)

@Composable
fun A207383_YUANHAORAN_Lab3Theme(
    content: @Composable () -> Unit
) {
    // 强制应用我们定义好的生态绿暗黑调色板
    val colorScheme = DarkColorScheme

    // 【高级设置】：同步手机状态栏颜色
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // 将状态栏背景设为我们的 App 背景色
            window.statusBarColor = colorScheme.background.toArgb()
            // 因为是暗黑模式，状态栏图标设为亮色
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    // 调用 Material 3 官方组件，应用新的颜色体系
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}