package com.deevvdd.politicalpreparedness.features.launch

import android.os.Bundle
import android.view.View
import com.deevvdd.politicalpreparedness.R
import com.deevvdd.politicalpreparedness.databinding.FragmentElectionCategoryBinding
import com.deevvdd.politicalpreparedness.features.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by heinhtet deevvdd@gmail.com on 16,August,2021
 */

class LaunchFragment :
    BaseFragment<FragmentElectionCategoryBinding>(R.layout.fragment_election_category) {
    override val viewModel: LaunchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            vm = viewModel
        }
    }
}