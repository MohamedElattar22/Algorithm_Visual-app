package com.example.algorithm_visual
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algorithm_visual.Algorithms.insertionSort
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AlgorithmViewModel(
    private val insertionSort: insertionSort
):ViewModel() {
  var arr = mutableStateOf(
      intArrayOf(
          50 , 42 , 165 , 400 , 231 , 634, 700, 6 , 4 ,11 ,
      504 , 76 , 88 , 40 , 112 , 124 , 9 , 26 , 1 , 76
      )
  )
    val isPlaying = mutableStateOf( false)
    val onSortingFinish = mutableStateOf(false)
    private var delay = 150L
    private var sortedArrayLevels = mutableListOf<List<Int>>()
    private var pause = false
    private var next = 1
    private var previous = 0


    init {
       viewModelScope.launch {
           insertionSort.sort(
               arr.value.clone()
           ){
               modifiedArray->
               sortedArrayLevels.add(modifiedArray.toMutableList())
           }
       }
    }
    fun onEvent(event: AlgorithmEvents){
        when(event){
            is AlgorithmEvents.PlayPause ->{
                playPauseAlgorithm()
            }
            is AlgorithmEvents.SlowDown ->{
                slowDown()
            }
            is AlgorithmEvents.Next->{
               nextM()

            }
            is AlgorithmEvents.SpeedUp->{
                speedUp()

            }
            is AlgorithmEvents.Previous->{
                prev()

            }
        }
    }

    private fun prev() {
      if(previous>=0){
          arr.value=sortedArrayLevels[previous].toIntArray()
          next--
          previous--
      }
    }

    private fun speedUp() {
       delay -= 50
    }

    private fun nextM() {
       if(next < sortedArrayLevels.size){
           arr.value=sortedArrayLevels[next].toIntArray()
           next++
           previous++
       }
    }

    private fun slowDown() {
        if(delay>=150L){
            delay+=50
        }
    }

    private fun playPauseAlgorithm() {
        if(isPlaying.value)
            pause()
        else
            play()
        isPlaying.value = !isPlaying.value

    }
    private var sortingState = 0
    private fun play() = viewModelScope.launch {
        pause = false
        for (i in sortingState until sortedArrayLevels.size){
            if(!pause){
                delay(delay)
                arr.value = sortedArrayLevels[i].toIntArray()
            }
            else{
                sortingState=i
                next = i+1
                previous = i
                return@launch

            }
        }
        onSortingFinish.value = true
    }

    private fun pause() {
       pause = true
    }


}