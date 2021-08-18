package com.deevvdd.politicalpreparedness.utils

import android.content.Context
import android.location.Geocoder
import android.location.Location
import com.deevvdd.politicalpreparedness.domain.model.Address
import java.util.*

/**
 * Created by heinhtet deevvdd@gmail.com on 10,August,2021
 */
 fun geoCodeLocation(context: Context, location: Location): Address {
    val geocode = Geocoder(context, Locale.getDefault())
    return geocode.getFromLocation(location.latitude, location.longitude, 1)
        .map { address ->
            Address(
                address.thoroughfare,
                address.subThoroughfare,
                address.locality,
                address.adminArea,
                address.postalCode
            )
        }
        .first()
}