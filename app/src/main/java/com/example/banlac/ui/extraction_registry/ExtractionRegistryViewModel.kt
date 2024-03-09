package com.example.banlac.ui.extraction_registry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExtractionRegistryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is extraction registry Fragment"
    }
    val text: LiveData<String> = _text
}