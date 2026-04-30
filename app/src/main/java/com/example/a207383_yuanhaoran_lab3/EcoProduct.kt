package com.example.a207383_yuanhaoran_lab3.data

// 用于页面间传递的数据类
data class EcoProduct(
    val name: String = "",
    val score: String = "",
    val description: String = ""
)

// 用于主页列表显示的演示数据类
data class DemoItem(
    val title: String,
    val subtitle: String,
    val iconRes: Int
)