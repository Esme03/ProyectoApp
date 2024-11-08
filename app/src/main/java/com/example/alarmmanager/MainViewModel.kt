package com.example.alarmmanager

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alarmmanager.Dao.AlarmDao
import com.example.alarmmanager.model.Alarm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(private val dao: AlarmDao) : ViewModel() {

    val alarms: LiveData<List<Alarm>> = dao.getAll()

    fun insert(alarm: Alarm) {
        viewModelScope.launch {
            dao.insertAlarm(alarm)
        }
    }

    fun update(alarm: Alarm) {
        viewModelScope.launch {
            dao.updateAlarm(alarm)
        }
    }
}