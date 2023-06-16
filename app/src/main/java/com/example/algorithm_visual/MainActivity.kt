package com.example.algorithm_visual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.modifierElementOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.algorithm_visual.Algorithms.insertionSort
import com.example.algorithm_visual.Component.VisualizerSection
import com.example.algorithm_visual.Component.visBottomBar
import com.example.algorithm_visual.ui.theme.Algorithm_VisualTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    private val viewModel : AlgorithmViewModel by lazy{
        val viewModelProviderFactory = AlgorithmViewModelProvider(insertionSort())
        ViewModelProvider(this,viewModelProviderFactory)[AlgorithmViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Algorithm_VisualTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                        contentAlignment = Alignment.BottomCenter
                ) {
                    Column {
                        VisualizerSection(
                            arr = viewModel.arr.value,
                            modifier = Modifier.fillMaxWidth()


                        )
                        val isPlaying = viewModel.isPlaying.value
                        val isFinished = viewModel.onSortingFinish.value
                        visBottomBar(

                            playPauseClick = {viewModel.onEvent(AlgorithmEvents.PlayPause)},
                            slowDownClick = {viewModel.onEvent(AlgorithmEvents.SlowDown)},
                            speedUpClick = {viewModel.onEvent(AlgorithmEvents.SpeedUp)},
                            previousClick = {viewModel.onEvent(AlgorithmEvents.Previous)},
                            nextClick = {viewModel.onEvent(AlgorithmEvents.Next)},
                            modifier = Modifier.fillMaxWidth()
                                .height(75.dp),
                                    isPlaying = if(isFinished) !isFinished else isPlaying






                        )
                    }

                }

            }
        }
    }
}

