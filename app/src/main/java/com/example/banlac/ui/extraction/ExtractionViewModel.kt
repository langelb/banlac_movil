package com.example.banlac.ui.extraction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExtractionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is extraction Fragment"
    }
    val text: LiveData<String> = _text
}