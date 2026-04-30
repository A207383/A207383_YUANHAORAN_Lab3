package com.example.a207383_yuanhaoran_lab3.ui

import androidx.lifecycle.ViewModel
import com.example.a207383_yuanhaoran_lab3.data.EcoProduct
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EcoViewModel : ViewModel() {
    // 存放当前正在填写的单个物品
    private val _uiState = MutableStateFlow(EcoProduct())
    val uiState = _uiState.asStateFlow()

    // 【Project 1 核心要求】：存放所有添加过的物品列表 [cite: 34, 36]
    private val _historyList = MutableStateFlow<List<EcoProduct>>(emptyList())
    val historyList = _historyList.asStateFlow()

    // 更新当前数据并将新物品添加到历史列表
    fun updateData(newName: String, newScore: String, newDesc: String) {
        val newItem = EcoProduct(name = newName, score = newScore, description = newDesc)
        _uiState.update { newItem }

        // 核心功能：添加项目到列表
        _historyList.update { currentList -> currentList + newItem }
    }
}