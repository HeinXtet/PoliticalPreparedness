package com.deevvdd.politicalpreparedness.features.representative

import android.annotation.SuppressLint
import android.location.Location
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.deevvdd.politicalpreparedness.R
import com.deevvdd.politicalpreparedness.domain.model.Address
import com.deevvdd.politicalpreparedness.domain.model.response.RepresentativeModel
import com.deevvdd.politicalpreparedness.domain.repository.RepresentativeRepository
import com.deevvdd.politicalpreparedness.domain.state.Result
import com.deevvdd.politicalpreparedness.features.base.BaseViewModel
import com.deevvdd.politicalpreparedness.utils.Event
import com.deevvdd.politicalpreparedness.utils.geoCodeLocation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by heinhtet deevvdd@gmail.com on 18,August,2021
 */
class RepresentativeViewModel(private val repository: RepresentativeRepository) : BaseViewModel() {
    val addressLine1 = MutableLiveData<String>()
    val addressLine2 = MutableLiveData<String>()
    val city = MutableLiveData<String>()
    var state = MutableLiveData<String>()
    val zipCode = MutableLiveData<String>()
    val address =
        MutableLiveData(Address(line1 = "", line2 = "", city = "", state = "", zip = ""))

    private val _representatives = MutableLiveData<RepresentativeModel>()

    var representatives = Transformations.map(_representatives) { representatives ->
        representatives.offices.flatMap { office -> office.getRepresentatives(officials = representatives.officials) }
    }


    private lateinit var fusedLocationClient: FusedLocationProviderClient


    fun initLocation(activity: FragmentActivity) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
    }


    fun updateAddress1(text: String) {
        val updatedAddress = address.value
        address.postValue(updatedAddress?.copy(line1 = text))
    }

    fun updateAddress2(text: String) {
        val updatedAddress = address.value
        address.postValue(updatedAddress?.copy(line2 = text))
    }

    fun updateCity(text: String) {
        val updatedAddress = address.value
        address.postValue(updatedAddress?.copy(city = text))
    }

    fun updateZip(text: String) {
        val updatedAddress = address.value
        address.postValue(updatedAddress?.copy(zip = text))
    }


    fun updateState(text: String) {
        state.postValue(text)
        val updatedAddress = address.value
        address.postValue(updatedAddress?.copy(state = text))
    }


    @SuppressLint("MissingPermission")
    fun requestLastKnownLocation(activity: FragmentActivity) {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                Timber.d("last known location $location")
                location?.let {
                    val userAddress = geoCodeLocation(activity, location)
                    address.postValue(userAddress)
                    addressLine1.postValue(userAddress.line1.orEmpty())
                    addressLine2.postValue(userAddress.line2.orEmpty())
                    city.postValue(userAddress.city)
                    zipCode.postValue(userAddress.zip)
                    state.postValue(userAddress.state)
                }
            }.addOnFailureListener {
                Timber.d("last known location failure $it")
                snackBarInt.postValue(Event(R.string.text_fail_to_get_location))
            }
    }

    fun getRepresentative() {
        viewModelScope.launch {
            address.value?.let {
                loading.postValue(true)
                Timber.d("formatted address ${it.toFormattedString()}")
                when (val result =
                    repository.fetchRepresentative(address = it.toFormattedString())) {
                    is Result.Success -> {
                        loading.postValue(false)
                        _representatives.postValue(result.data)
                    }
                    is Result.Error -> {
                        loading.postValue(false)
                        snackBarMessage.postValue(Event(result.error))
                    }
                }
            }
        }
    }
}