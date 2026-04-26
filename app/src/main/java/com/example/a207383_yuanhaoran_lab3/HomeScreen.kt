package com.example.a207383_yuanhaoran_lab3.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a207383_yuanhaoran_lab3.R
import com.example.a207383_yuanhaoran_lab3.data.DemoItem

@Composable
fun HomeScreen(onStartScan: () -> Unit) {
    // 🌟 这里是修改的核心部分：将原先重复的扫描标志 R.drawable.ic_scan 替换为你上传的具体图片。
    // 请确保 eco_leaf, recycle_icon, sample_item 跟你 res/drawable 里面的文件名一模一样！
    val productList = listOf(
        DemoItem("Organic Cotton", "Carbon Footprint: Low", R.drawable.eco_leaf),
        DemoItem("Plastic Bottle", "Carbon Footprint: High", R.drawable.recycle_icon),
        DemoItem("Solar Panel", "Carbon Footprint: Negative", R.drawable.sample_item)
    )

    Scaffold(
        // Lab 3 & 4 结合：底部功能导航栏
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceVariant) {
                NavigationBarItem(icon = { Icon(Icons.Default.Home, "Home") }, label = { Text("Home") }, selected = true, onClick = {})
                // 点击 Scan 按钮跳转到表单页
                NavigationBarItem(icon = { Icon(Icons.Default.Add, "Scan") }, label = { Text("Scan") }, selected = false, onClick = onStartScan)
                NavigationBarItem(icon = { Icon(Icons.Default.Person, "Profile") }, label = { Text("Profile") }, selected = false, onClick = {})
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
                // 1. 【找回】：用户头像区域 (完全还原 Lab 3 模样)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        // 🌟 使用了你上传的圆形用户头像 R.drawable.user_avatar
                        modifier = Modifier.size(45.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.user_avatar),
                            contentDescription = "User Avatar",
                            modifier = Modifier.size(40.dp).clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Hello,", fontSize = 12.sp, color = Color.Gray)
                        Text("Yuan Haoran", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                // 2. 【找回】：核心大扫描图标框 (还原 Lab 3 的标志性 UI)
                // 这里我保留了你最满意的那个大图标设计（大的圆角矩形框里有 ic_scan.xml 矢量图）。
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .size(130.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_scan),
                            contentDescription = "Scan Logo",
                            modifier = Modifier.size(90.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("EcoScanner", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.primary)
                    Text("Sustainable Living Starts Here", fontSize = 13.sp, color = Color.Gray)
                }

                Spacer(modifier = Modifier.height(32.dp))
                Text("Recent Items", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            // 3. 【商品列表区域】：Lab 3 的 LazyColumn 和 ExpandableCard
            items(productList) { item ->
                ExpandableProductCard(item)
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
fun ExpandableProductCard(item: DemoItem) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.fillMaxWidth().animateContentSize().clickable { expanded = !expanded },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // 🌟 这里我使用了 ContentScale.Crop 加上了圆角裁剪。
                // 这样无论你上传的具体图片比例是什么，都会被完美裁剪成圆角正方形，非常美观。
                Image(
                    painter = painterResource(id = item.iconRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(50.dp).clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(item.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(item.subtitle, fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                }
                Icon(if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown, null)
            }
            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                Text("Detailed analysis shows this product meets 90% of eco-friendly standards. Scan to see full report.", fontSize = 13.sp, lineHeight = 18.sp)
            }
        }
    }
}