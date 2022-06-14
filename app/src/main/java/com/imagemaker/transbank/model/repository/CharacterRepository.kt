package com.imagemaker.transbank.model.repository

import androidx.paging.PagingData
import com.google.gson.Gson
import com.imagemaker.transbank.model.models.CharacterModel
import com.imagemaker.transbank.model.models.Result
import com.imagemaker.transbank.model.network.RickAndMortyService
import com.imagemaker.transbank.model.persistence.daos.CharacterDao
import com.imagemaker.transbank.model.persistence.entities.CharacterEntity
import com.skydoves.sandwich.getOrNull
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val rickAndMortyService: RickAndMortyService,
    private val characterDao: CharacterDao
) {

    suspend fun loadRickAndMortyCharacters(
        page: Int
    ): CharacterModel?{

        val remoteCharaters = rickAndMortyService.fetchRickAndMortyCharacters(page).getOrNull()

        remoteCharaters?.let {
            val listJsonCharacter = mutableListOf<CharacterEntity>()
            it.results.forEach { result ->

                val jsonStringCharacter:String = Gson().toJson(result)
                listJsonCharacter.add(CharacterEntity(
                    result.id,
                    jsonStringCharacter
                ))
            }
            characterDao.insertAll(listJsonCharacter)
        }

        return remoteCharaters
    }

    fun loadLocalCharacters(): PagingData<Result> {
        val localCharacters = characterDao.getAll()
        val listCharacters = mutableListOf<Result>()
        localCharacters.forEach {
            val tempResult: Result = Gson().fromJson(it.jsonCharacter, Result::class.java)
            listCharacters.add(tempResult)
        }
        return PagingData.from(listCharacters)
    }

}