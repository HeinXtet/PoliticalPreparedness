package com.deevvdd.politicalpreparedness.di

import com.deevvdd.politicalpreparedness.data.api.ApiService
import com.deevvdd.politicalpreparedness.features.election.ElectionViewModel
import com.deevvdd.politicalpreparedness.features.launch.LaunchViewModel
import com.deevvdd.politicalpreparedness.features.representative.RepresentativeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */

val viewModelModule = module {
    viewModel { LaunchViewModel() }
    viewModel { ElectionViewModel(get()) }
    single { RepresentativeViewModel(get()) }
}