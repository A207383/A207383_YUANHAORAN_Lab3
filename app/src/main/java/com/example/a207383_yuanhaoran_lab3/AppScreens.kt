package com.example.a207383_yuanhaoran_lab3.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormScreen(viewModel: EcoViewModel, onSaveAndNext: () -> Unit, onBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var score by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(24.dp).statusBarsPadding()) {
        TextButton(onClick = onBack) { Text("← Back to Home", fontWeight = FontWeight.Bold) }
        Text("Scan New Item", fontSize = 28.sp, fontWeight = FontWeight.Black)
        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Product Name") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = score, onValueChange = { score = it }, label = { Text("Eco Score (0-100)") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = desc, onValueChange = { desc = it }, label = { Text("Material Details") }, modifier = Modifier.fillMaxWidth(), minLines = 3, shape = RoundedCornerShape(12.dp))

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { viewModel.updateData(name, score, desc); onSaveAndNext() },
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("ANALYZE NOW", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@Composable
fun DetailScreen(viewModel: EcoViewModel, onBackToHome: () -> Unit) {
    val data by viewModel.uiState.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(modifier = Modifier.padding(30.dp)) {
                Text("Result", fontWeight = FontWeight.Black, fontSize = 26.sp, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(20.dp))
                Text("Product: ${data.name.ifEmpty { "N/A" }}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("Eco Score: ${data.score.ifEmpty { "0" }}/100", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF388E3C))
                Spacer(modifier = Modifier.height(12.dp))
                Text(data.description.ifEmpty { "No description provided." }, fontSize = 15.sp, lineHeight = 22.sp)
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = onBackToHome, modifier = Modifier.fillMaxWidth().height(60.dp), shape = RoundedCornerShape(16.dp)) {
            Text("BACK TO DASHBOARD")
        }
    }
}