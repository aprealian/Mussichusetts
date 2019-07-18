package com.teknokrait.mussichusetts.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by Aprilian Nur Wakhid Daini on 7/18/2019.
 */
class TrackViewModelFactory @Inject constructor(
        private val cryptoCurrencyViewModel: TrackViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrackViewModel::class.java)) {
            return cryptoCurrencyViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}