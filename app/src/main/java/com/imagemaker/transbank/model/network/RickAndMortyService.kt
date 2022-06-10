package com.imagemaker.transbank.model.network

import com.imagemaker.transbank.model.models.CharacterModel
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface RickAndMortyService {
    @GET("character")
    suspend fun fetchRickAndMortyCharacters(): ApiResponse<CharacterModel>
}