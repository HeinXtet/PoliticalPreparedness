package com.deevvdd.politicalpreparedness.utils

import androidx.navigation.NavDirections

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
sealed class NavigationCommand {
    /**
     * navigate to a direction
     */
    data class To(val directions: NavDirections) : NavigationCommand()

    /**
     * navigate back to the previous fragment
     */
    object Back : NavigationCommand()

    /**
     * navigate back to a destination in the back stack
     */
    data class BackTo(val destinationId: Int) : NavigationCommand()
}