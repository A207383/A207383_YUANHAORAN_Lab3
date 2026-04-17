package com.example.a207383_yuanhaoran_lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a207383_yuanhaoran_lab3.ui.theme.A207383_YUANHAORAN_Lab3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A207383_YUANHAORAN_Lab3Theme {
                // 【改名绕过冲突】
                EcoScannerApp_Lab3()
            }
        }
    }
}

@Composable
fun EcoScannerApp_Lab3() { // 【改名绕过冲突】
    var userInput by remember { mutableStateOf("") }
    var displayMessage by remember { mutableStateOf("Ready to search.") }
    var selectedTab by remember { mutableStateOf("Scan") }

    val bgColor = MaterialTheme.colorScheme.background
    val primaryColor = MaterialTheme.colorScheme.primary

    Column(modifier = Modifier.fillMaxSize().background(bgColor)) {
        Column(
            modifier = Modifier.weight(1f).padding(top = 40.dp, start = 16.dp, end = 16.dp)
        ) {
            // 1. 顶部栏
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.eco_leaf), contentDescription = "Logo", modifier = Modifier.size(40.dp))
                Column(modifier = Modifier.padding(start = 12.dp).weight(1f)) {
                    Text(text = "EcoScanner", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    Text(text = "Matric No: A207383", fontSize = 14.sp, color = primaryColor, fontWeight = FontWeight.Bold)
                }
                Image(painter = painterResource(id = R.drawable.user_avatar), contentDescription = "Avatar", contentScale = ContentScale.Crop, modifier = Modifier.size(48.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 2. 扫描区域 Card
            Card(
                modifier = Modifier.fillMaxWidth().height(130.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_scan),
                        contentDescription = "Scanner",
                        modifier = Modifier.size(56.dp),
                        colorFilter = ColorFilter.tint(primaryColor)
                    )
                    Text(text = "Smart Product Scanner", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.padding(top = 8.dp))
                    Text(text = "Tap to scan barcode", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 3. 搜索交互区
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    TextField(
                        value = userInput,
                        onValueChange = { userInput = it },
                        label = { Text("Search product...", color = Color.Gray) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedIndicatorColor = primaryColor,
                            unfocusedIndicatorColor = Color.Gray
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { displayMessage = "Searching database for: $userInput" },
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search",
                            modifier = Modifier.size(18.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Search Product", color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                    Text(text = displayMessage, color = primaryColor, fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.padding(top = 8.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Recent Scans", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.padding(bottom = 8.dp))

            // 4. 最近扫描列表 (使用改名后的函数)
            ExpandableProductCard_Lab3(
                imageRes = R.drawable.sample_item,
                title = "Organic Water",
                subtitle = "Low carbon",
                score = "95/100",
                details = "This water bottle is made from 100% recycled PET plastic."
            )

            Spacer(modifier = Modifier.height(8.dp))

            ExpandableProductCard_Lab3(
                imageRes = R.drawable.recycle_icon,
                title = "Recycled Notebook",
                subtitle = "Post-consumer",
                score = "88/100",
                details = "Notebook made using 80% post-consumer waste."
            )
        }

        // 5. 底部导航栏
        Row(
            modifier = Modifier.fillMaxWidth().background(bgColor).padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 使用改名后的函数
            BottomNavIcon_Lab3("Scan", R.drawable.ic_scan, selectedTab) { selectedTab = "Scan" }
            BottomNavIcon_Lab3("Search", R.drawable.ic_search, selectedTab) { selectedTab = "Search" }
            BottomNavIcon_Lab3("Settings", R.drawable.ic_settings, selectedTab) { selectedTab = "Settings" }
        }
    }
}

// 💡 【改名绕过冲突】
@Composable
fun ExpandableProductCard_Lab3(imageRes: Int, title: String, subtitle: String, score: String, details: String) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            if (expanded) {
                Text(
                    text = "Eco Details: $details",
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color.DarkGray)

            }


            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = imageRes), contentDescription = title, contentScale = ContentScale.Crop, modifier = Modifier.size(40.dp))
                Column(modifier = Modifier.padding(start = 12.dp).weight(1f)) {
                    Text(text = title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                    Text(text = subtitle, fontSize = 12.sp, color = Color.Gray)
                }
                Text(text = score, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }



        }
    }
}

// 💡 【改名绕过冲突】
@Composable
fun BottomNavIcon_Lab3(title: String, iconResId: Int, selectedTab: String, onClick: () -> Unit) {
    val isSelected = selectedTab == title
    val color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = title,
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(color)
        )
        Text(text = title, fontSize = 10.sp, color = color, modifier = Modifier.padding(top = 4.dp), fontWeight = FontWeight.Bold)
    }
}