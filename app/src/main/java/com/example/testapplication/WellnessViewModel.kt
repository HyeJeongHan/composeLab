package com.example.testapplication

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.testapplication.data.WellnessTask
import com.example.testapplication.data.getWellnessTasks

class WellnessViewModel: ViewModel() {
    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks: List<WellnessTask>
        get() = _tasks

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) =
        _tasks.find { it.id == item.id }?.let { task ->
            task.checked = checked
        }

    fun remove(item: WellnessTask) {
        _tasks.remove(item)

    }
}