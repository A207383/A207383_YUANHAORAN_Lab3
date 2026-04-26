package com.example.a207383_yuanhaoran_lab3.ui

import androidx.lifecycle.ViewModel
import com.example.a207383_yuanhaoran_lab3.data.EcoProduct
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EcoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EcoProduct())
    val uiState = _uiState.asStateFlow()

    // 更新数据的方法，供 FormScreen 调用
    fun updateData(newName: String, newScore: String, newDesc: String) {
        _uiState.update { it.copy(name = newName, score = newScore, description = newDesc) }
    }
}