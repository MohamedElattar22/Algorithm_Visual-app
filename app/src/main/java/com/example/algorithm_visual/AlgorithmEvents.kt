package com.example.algorithm_visual

sealed class AlgorithmEvents{
    object SlowDown : AlgorithmEvents()
    object PlayPause : AlgorithmEvents()
    object SpeedUp :AlgorithmEvents()
    object Previous : AlgorithmEvents()
    object Next : AlgorithmEvents()
}
