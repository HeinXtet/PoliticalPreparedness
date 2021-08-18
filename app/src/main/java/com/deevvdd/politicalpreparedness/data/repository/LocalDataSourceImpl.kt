package com.deevvdd.politicalpreparedness.data.repository

import androidx.lifecycle.LiveData
import com.deevvdd.politicalpreparedness.data.database.ElectionDao
import com.deevvdd.politicalpreparedness.data.database.SavedElectionDao
import com.deevvdd.politicalpreparedness.domain.model.response.Election
import com.deevvdd.politicalpreparedness.domain.model.response.SavedElection
import com.deevvdd.politicalpreparedness.domain.repository.LocalDataSource

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
class LocalDataSourceImpl(
    private val dao: ElectionDao,
    private val savedElectionDao: SavedElectionDao
) : LocalDataSource {
    override fun getElections(): LiveData<List<Election>> {
        return dao.getAll()
    }

    override suspend fun insertElections(elections: List<Election>) {
        dao.insertElections(elections)
    }

    override suspend fun updateElection(election: Election) {
        dao.updateElection(election)
    }

    override suspend fun deleteElection(election: SavedElection) {
        savedElectionDao.deleteElection(election)
    }

    override suspend fun saveElection(election: SavedElection) {
        savedElectionDao.saveElection(election)
    }

    override fun getSavedElections(): LiveData<List<SavedElection>> {
        return savedElectionDao.getAll()
    }
}