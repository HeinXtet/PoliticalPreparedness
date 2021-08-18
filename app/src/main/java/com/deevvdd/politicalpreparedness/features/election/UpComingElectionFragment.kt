package com.deevvdd.politicalpreparedness.features.election

import android.os.Bundle
import android.view.View
import com.deevvdd.politicalpreparedness.R
import com.deevvdd.politicalpreparedness.databinding.FragmentUpcomingElectionBinding
import com.deevvdd.politicalpreparedness.domain.model.response.Election
import com.deevvdd.politicalpreparedness.domain.model.response.SavedElection
import com.deevvdd.politicalpreparedness.features.base.BaseFragment
import com.deevvdd.politicalpreparedness.utils.Event
import com.deevvdd.politicalpreparedness.utils.NavigationCommand
import org.koin.android.ext.android.inject

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
class UpComingElectionFragment :
    BaseFragment<FragmentUpcomingElectionBinding>(R.layout.fragment_upcoming_election),
    ElectionAdapterCallback {

    override val viewModel: ElectionViewModel by inject()
    private lateinit var electionAdapter: ElectionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        electionAdapter = ElectionAdapter(this)
        binding.apply {
            vm = viewModel
            adapter = electionAdapter
            isSavedElections = false
        }
        viewModel.getElections()
    }

    override fun onItemClick(item: Election) {
        viewModel.navigateCommand.postValue(
            Event(
                NavigationCommand.To(
                    ElectionFragmentDirections.actionElectionFragmentToVoterInfoFragment(
                        item
                    )
                )
            )
        )
    }

    override fun onDelete(item: Election) {

    }
}