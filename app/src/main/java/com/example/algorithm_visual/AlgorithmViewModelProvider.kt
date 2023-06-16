package com.example.algorithm_visual
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.algorithm_visual.Algorithms.insertionSort

class AlgorithmViewModelProvider (
    private var insertionSort: insertionSort
        ):ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return AlgorithmViewModel(insertionSort) as T
    }
}