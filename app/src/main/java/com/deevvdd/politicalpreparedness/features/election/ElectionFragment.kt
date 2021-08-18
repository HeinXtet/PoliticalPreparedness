package com.deevvdd.politicalpreparedness.features.election

import android.os.Bundle
import android.view.View
import com.deevvdd.politicalpreparedness.R
import com.deevvdd.politicalpreparedness.databinding.FragmentElectionBinding
import com.deevvdd.politicalpreparedness.features.base.BaseFragment
import com.deevvdd.politicalpreparedness.features.base.BaseViewModel
import com.deevvdd.politicalpreparedness.features.launch.LaunchViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
class ElectionFragment :
    BaseFragment<FragmentElectionBinding>(R.layout.fragment_election) {
    override val viewModel: ElectionViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getElections()
        viewModel.elections.observe(viewLifecycleOwner, {
            Timber.d("Elections $it")
        })
        binding.apply {
            viewPager.adapter = ElectionPagerAdapter(requireActivity(), childFragmentManager)
            tabLayout.setupWithViewPager(binding.viewPager)
        }
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Handle tab select
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })
    }
}