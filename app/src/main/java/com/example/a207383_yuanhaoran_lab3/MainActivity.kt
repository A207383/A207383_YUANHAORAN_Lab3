package com.example.a207383_yuanhaoran_lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a207383_yuanhaoran_lab3.ui.* // 导入你新建的 ui 包下的所有内容
import com.example.a207383_yuanhaoran_lab3.ui.theme.A207383_YUANHAORAN_Lab3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A207383_YUANHAORAN_Lab3Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val myViewModel: EcoViewModel = viewModel()

                    NavHost(navController = navController, startDestination = "home") {
                        // 主页：找回了 Lab 3 的所有 UI，并使用了具体图片
                        composable("home") {
                            HomeScreen(onStartScan = { navController.navigate("form") })
                        }
                        // 表单页
                        composable("form") {
                            FormScreen(
                                viewModel = myViewModel,
                                onSaveAndNext = { navController.navigate("detail") },
                                onBack = { navController.popBackStack() }
                            )
                        }
                        // 详情页
                        composable("detail") {
                            DetailScreen(
                                viewModel = myViewModel,
                                onBackToHome = {
                                    navController.navigate("home") { popUpTo("home") { inclusive = true } }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}