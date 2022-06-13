package com.imagemaker.transbank.model.repository

import com.imagemaker.transbank.model.network.RickAndMortyService
import com.imagemaker.transbank.model.persistence.daos.CharacterDao
import com.skydoves.sandwich.getOrNull
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val rickAndMortyService: RickAndMortyService,
    private val characterDao: CharacterDao
) {

    suspend fun loadRickAndMortyCharacters(
        page: Int
    ) = rickAndMortyService.fetchRickAndMortyCharacters(page).getOrNull()

}