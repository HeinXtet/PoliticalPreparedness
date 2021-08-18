package com.deevvdd.politicalpreparedness.features.election

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.deevvdd.politicalpreparedness.R

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
class ElectionPagerAdapter(
    private val context: Context,
    fragmentManager: FragmentManager
) :
    FragmentStatePagerAdapter(fragmentManager) {


    override fun getCount(): Int {
        return 2
    }


    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> context.getString(R.string.upcoming_elections)
            1 -> context.getString(R.string.saved_elections)
            else -> ""
        }
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> UpComingElectionFragment()
            1 -> SavedElectionFragment()
            else -> UpComingElectionFragment()
        }
    }
}