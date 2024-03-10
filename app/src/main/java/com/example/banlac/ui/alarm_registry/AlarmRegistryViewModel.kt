package com.example.banlac.ui.alarm_registry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AlarmRegistryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is extraction registry Fragment"
    }
    val text: LiveData<String> = _text
}