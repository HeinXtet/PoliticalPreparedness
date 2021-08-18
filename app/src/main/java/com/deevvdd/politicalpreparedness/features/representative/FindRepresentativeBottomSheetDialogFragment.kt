package com.deevvdd.politicalpreparedness.features.representative

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.deevvdd.politicalpreparedness.MainActivity
import com.deevvdd.politicalpreparedness.R
import com.deevvdd.politicalpreparedness.databinding.BottomSheetFindRepresentativeDialogBinding
import com.deevvdd.politicalpreparedness.utils.geoCodeLocation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * Created by heinhtet deevvdd@gmail.com on 18,August,2021
 */
class FindRepresentativeBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetFindRepresentativeDialogBinding
    private val viewModel: RepresentativeViewModel by inject()
    private var states = arrayOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.bottom_sheet_find_representative_dialog,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        viewModel.initLocation(requireActivity())
        initObserver()
        initView()
    }

    private fun initObserver() {
        viewModel.state.observe(viewLifecycleOwner, {
            val autoCompleteTextView = (binding.tfState.editText as AutoCompleteTextView)
            val position = states.indexOf(it)
            if (position > -1) {
                with(autoCompleteTextView) {
                    setText(
                        adapter.getItem(position).toString(), false
                    )
                }
            }
        })
    }

    private fun initView() {
        states = resources.getStringArray(R.array.states)
        with(binding) {
            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.item_auto_complete,
                states
            )
            (tfState.editText as AutoCompleteTextView).setAdapter(adapter)
            (tfState.editText as AutoCompleteTextView).setOnItemClickListener { parent, view, position, id ->
                viewModel.updateState(states[position])
            }

            edAddressLine1.addTextChangedListener { viewModel.updateAddress1(it.toString()) }
            edAddressLine2.addTextChangedListener { viewModel.updateAddress2(it.toString()) }
            edCity.addTextChangedListener { viewModel.updateCity(it.toString()) }
            edZipCode.addTextChangedListener { viewModel.updateZip(it.toString()) }

            btnUseLocation.setOnClickListener {
                (requireActivity() as MainActivity).checkForegroundLocationPermission(
                    onGranted = { viewModel.requestLastKnownLocation(requireActivity()) },
                    onDenied = {})
            }
            btnSearchRepresentative.setOnClickListener {
                viewModel.getRepresentative()
                dismiss()
            }
        }
    }


    companion object {
        const val TAG = "FindRepresentativeBottomSheetDialogFragment"
    }
}