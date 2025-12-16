package com.example.testapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testapplication.data.WellnessTask
import com.example.testapplication.data.getWellnessTasks
import com.example.testapplication.ui.theme.TestApplicationTheme

class ComposeLabStateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            TestApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    WaterScreen()
                    WellnessScreen()
                }
            }
        }
    }
}

// 상태 호이스팅 안해서 리컴포지션이 반복되어 원하지 않는 동작이 됨.
@Composable
fun WaterCountTest(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by remember { mutableStateOf(0) }
        if (count > 0) {
            var showTask by remember { mutableStateOf(true) }
            if (showTask) {
//                WellnessTaskItem(
//                    onClose = { showTask = false },
//                    taskName = "Have you taken your 15 minute walk today?",
//                    modifier = Modifier.fillMaxWidth(),
//                    checked = false,
//                    onCheckChanged = {},
//                    onClose = {}
//                )
            }

            Text("You've had ${count} glasses.")
        }
        Row(Modifier.padding(top = 8.dp)) {
            Button(
                modifier = modifier.padding(end = 16.dp),
                onClick = {
                    count++
                },
                enabled = count < 10
            ) {
                Text("add one")
            }
            Button(
                modifier = modifier,
                onClick = {
                    count = 0
                },
            ) {
                Text("Clear water count")
            }
        }
    }
}

@Composable
fun WaterCount(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by rememberSaveable { mutableStateOf(0) }
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun StatelessCounter(
    count: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(count, { count++ }, modifier)

}

@Composable
fun WaterScreen(
    modifier: Modifier = Modifier
) {
    WaterCount(modifier)
}

@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

@Composable
fun WellnessTasksList(
    list: List<WellnessTask>,
    onCheckedTask: (WellnessTask, Boolean) -> Unit,
    onCloseTask: (WellnessTask) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list,
            key = { task -> task.id }
        ) { task ->
            WellnessTaskItem(
                taskName = task.label,
                checked = task.checked,
                onCheckedChange = { checked -> onCheckedTask(task, checked) },
                onClose = { onCloseTask(task) }
            )
        }
    }
}

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
    Column {
        StatefulCounter()

        val list = remember { getWellnessTasks().toMutableList() }
        WellnessTasksList(
            list = wellnessViewModel.tasks,
            onCheckedTask = { task, checked ->
                wellnessViewModel.changeTaskChecked(task, checked)
            },
            onCloseTask = { task -> wellnessViewModel.remove(task) }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun TextPreview() {
    TestApplicationTheme {
        WaterCount()
    }
}