package com.example.banlac.ui.alarm_edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AlarmEditViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is extraction edit Fragment"
    }
    val text: LiveData<String> = _text
}