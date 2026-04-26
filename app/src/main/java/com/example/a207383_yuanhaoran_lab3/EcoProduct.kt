package com.example.a207383_yuanhaoran_lab3.data

// Lab 4: 用于页面间传递的数据类
data class EcoProduct(
    val name: String = "",
    val score: String = "",
    val description: String = ""
)

// Lab 3: 用于主页列表显示的演示数据类
// ✅ 已经修改为能够正确持有图片资源 ID
data class DemoItem(
    val title: String,
    val subtitle: String,
    val iconRes: Int // 图片资源ID
)