package com.deevvdd.politicalpreparedness.features.election

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.deevvdd.politicalpreparedness.R
import com.deevvdd.politicalpreparedness.domain.model.response.Election
import com.deevvdd.politicalpreparedness.domain.model.response.SavedElection
import com.deevvdd.politicalpreparedness.domain.model.response.VoterInfoModel
import com.deevvdd.politicalpreparedness.domain.repository.ElectionRepository
import com.deevvdd.politicalpreparedness.domain.state.Result
import com.deevvdd.politicalpreparedness.domain.utils.toElection
import com.deevvdd.politicalpreparedness.domain.utils.toSavedElection
import com.deevvdd.politicalpreparedness.features.base.BaseViewModel
import com.deevvdd.politicalpreparedness.utils.Event
import kotlinx.coroutines.launch

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
class ElectionViewModel(private val repository: ElectionRepository) : BaseViewModel() {


    private val _elections = repository.getElections()
    private val _savedElections = repository.getSavedElections()
    private val _voterInfo = MutableLiveData<VoterInfoModel>()
    private val _openLinkEvent = MutableLiveData<Event<String>>()

    val elections: LiveData<List<Election>> = _elections

    val voterInfo: LiveData<VoterInfoModel>
        get() = _voterInfo
    val openLinkEvent: LiveData<Event<String>>
        get() = _openLinkEvent

    val savedElections = Transformations.map(_savedElections) {
        it.map { item ->
            item.toElection()
        }
    }

    val isEmptyElections = Transformations.map(_elections) {
        it.isNullOrEmpty()
    }

    val isEmptySavedElection = Transformations.map(_savedElections) {
        it.isNullOrEmpty()
    }

    val votingLocationUrl = Transformations.map(_voterInfo) {
        if (!it.state.isNullOrEmpty()) {
            it.state[0].electionAdministrationBody.votingLocationFinderUrl
        } else {
            null
        }
    }
    val ballotInformationUrl = Transformations.map(_voterInfo) {
        if (!it.state.isNullOrEmpty()) {
            it.state[0].electionAdministrationBody.ballotInfoUrl
        } else {
            null
        }
    }

    val correspondingAddress = Transformations.map(_voterInfo) {
        if (!it.state.isNullOrEmpty()) {
            it.state[0].electionAdministrationBody.correspondenceAddress?.toFormattedString()
        } else {
            null
        }
    }

    var election: Election? = null


    val isExitOnSavedElections = Transformations.map(_savedElections) {
        it.findLast { it.id == election?.id.toString() } != null
    }


    fun getElections() {
        viewModelScope.launch {
            when (val result = repository.fetchElections()) {
                is Result.Success -> {
                    loading.postValue(false)
                }
                is Result.Error -> {
                    loading.postValue(false)
                    snackBarMessage.postValue(Event(result.error))
                }
            }
        }
    }

    fun getVoterInfo() {
        val state = election?.ocdDivisionId?.state
        val country = election?.ocdDivisionId?.country
        var address = ""
        if (!state.isNullOrEmpty()) {
            address = "$country - $state"
        }
        loading.postValue(true)
        viewModelScope.launch {
            when (val result = repository.fetchVoterInfo(
                electionId = election?.id.toString(),
                address = address
            )) {
                is Result.Success -> {
                    loading.postValue(false)
                    _voterInfo.postValue(result.data)
                }
                is Result.Error -> {
                    loading.postValue(false)
                    snackBarMessage.postValue(Event(result.error))
                }
            }
        }
    }

    private fun saveElection(item: Election) {
        viewModelScope.launch {
            repository.saveElection(
                item.toSavedElection()
            )
        }
        snackBarInt.postValue(Event(R.string.saved_election))
    }

    fun deleteSavedElection(item: Election) {
        viewModelScope.launch {
            repository.deleteElection(
                item.toSavedElection()
            )
        }
        snackBarInt.postValue(Event(R.string.deleted_election))
    }

    fun toggleFollowElection() {
        election?.let {
            val saved = isExitOnSavedElections.value
            if (saved == true) {
                deleteSavedElection(it)
            } else {
                saveElection(it)
            }
        }
    }

    fun openBrowser(url: String) {
        _openLinkEvent.postValue(Event(url))
    }
}