package com.deevvdd.politicalpreparedness.features.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deevvdd.politicalpreparedness.utils.Event
import com.deevvdd.politicalpreparedness.utils.NavigationCommand

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
abstract class BaseViewModel : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val navigateCommand = MutableLiveData<Event<NavigationCommand>>()
    val snackBarInt = MutableLiveData<Event<Int>>()
    val snackBarMessage = MutableLiveData<Event<String>>()
}