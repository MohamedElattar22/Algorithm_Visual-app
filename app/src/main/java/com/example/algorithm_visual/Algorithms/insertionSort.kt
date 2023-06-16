package com.example.algorithm_visual.Algorithms

class insertionSort {
    suspend fun sort(
        arr:IntArray,
        onSwap:(IntArray)->Unit
    )
    {
       for (i in 1 until arr.size){
           var j =i-1
           val key =arr[i]
           while (j>=0 && key<arr[j]){
               arr[j+1]=arr[j]
               onSwap(arr)
               j--
           }
           arr[j+1]=key
           onSwap(arr)
       }
    }
}