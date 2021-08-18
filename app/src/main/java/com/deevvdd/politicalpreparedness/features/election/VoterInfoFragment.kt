package com.deevvdd.politicalpreparedness.features.election

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.deevvdd.politicalpreparedness.R
import com.deevvdd.politicalpreparedness.databinding.FragmentVoterinfoBinding
import com.deevvdd.politicalpreparedness.features.base.BaseFragment
import org.koin.android.ext.android.inject

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
class VoterInfoFragment : BaseFragment<FragmentVoterinfoBinding>(R.layout.fragment_voterinfo) {

    private val args: VoterInfoFragmentArgs by navArgs()
    override val viewModel: ElectionViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            vm = viewModel
            election = args.election
        }
        viewModel.election = args.election
        viewModel.getVoterInfo()
        viewModel.openLinkEvent.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.election = null
    }
}