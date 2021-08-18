package com.deevvdd.politicalpreparedness.features.launch

import com.deevvdd.politicalpreparedness.features.base.BaseViewModel
import com.deevvdd.politicalpreparedness.utils.Event
import com.deevvdd.politicalpreparedness.utils.NavigationCommand

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
class LaunchViewModel : BaseViewModel() {

    fun goToElection() {
        navigateCommand.postValue(
            Event(NavigationCommand.To(LaunchFragmentDirections.actionElectionCategoryFragmentToElectionFragment()))
        )
    }

    fun goToFindMyRepresentative() {
        navigateCommand.postValue(
            Event(NavigationCommand.To(LaunchFragmentDirections.actionElectionCategoryFragmentToRepresentativeFragment()))
        )
    }
}