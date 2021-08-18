package com.deevvdd.politicalpreparedness.features.representative

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.deevvdd.politicalpreparedness.R
import com.deevvdd.politicalpreparedness.databinding.FragmentRepresentativeBinding
import com.deevvdd.politicalpreparedness.features.base.BaseFragment
import com.deevvdd.politicalpreparedness.features.base.BaseViewModel
import org.koin.android.ext.android.inject
import timber.log.Timber

/**
 * Created by heinhtet deevvdd@gmail.com on 18,August,2021
 */
class RepresentativeFragment :
    BaseFragment<FragmentRepresentativeBinding>(R.layout.fragment_representative) {
    override val viewModel: RepresentativeViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        showSearchRepresentative()
        binding.apply {
            vm = viewModel
            adapter = RepresentativeAdapter()
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun initView() {
        with(binding) {
            btnRepresentatives.setOnClickListener { showSearchRepresentative() }
        }
    }

    private fun showSearchRepresentative() {
        val searchRepresentativeDialog = FindRepresentativeBottomSheetDialogFragment()
        searchRepresentativeDialog.show(
            childFragmentManager,
            FindRepresentativeBottomSheetDialogFragment.TAG
        )
    }
}