package com.deevvdd.politicalpreparedness.features.election

import android.os.Bundle
import android.view.View
import com.deevvdd.politicalpreparedness.R
import com.deevvdd.politicalpreparedness.databinding.FragmentSavedElectionBinding
import com.deevvdd.politicalpreparedness.databinding.FragmentUpcomingElectionBindingImpl
import com.deevvdd.politicalpreparedness.domain.model.response.Election
import com.deevvdd.politicalpreparedness.features.base.BaseFragment
import com.deevvdd.politicalpreparedness.features.base.BaseViewModel
import com.deevvdd.politicalpreparedness.utils.Event
import com.deevvdd.politicalpreparedness.utils.NavigationCommand
import org.koin.android.ext.android.inject
import timber.log.Timber

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
class SavedElectionFragment :
    BaseFragment<FragmentSavedElectionBinding>(R.layout.fragment_saved_election),
    ElectionAdapterCallback {
    override val viewModel: ElectionViewModel by inject()
    private lateinit var electionAdapter: ElectionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        electionAdapter = ElectionAdapter(this, isSavedElection = true)
        binding.apply {
            vm = viewModel
            adapter = electionAdapter
        }
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
        viewModel.deleteSavedElection(item)
    }
}