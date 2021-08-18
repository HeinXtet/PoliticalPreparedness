package com.deevvdd.politicalpreparedness.domain.repository

import com.deevvdd.politicalpreparedness.domain.model.response.RepresentativeModel
import com.deevvdd.politicalpreparedness.domain.state.Result

/**
 * Created by heinhtet deevvdd@gmail.com on 18,August,2021
 */
interface RepresentativeRepository {
   suspend fun fetchRepresentative(address:String) : Result<RepresentativeModel>
}