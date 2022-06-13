package com.imagemaker.transbank.model.network

import com.imagemaker.transbank.model.models.CharacterModel
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyService {
    @GET("character")
    suspend fun fetchRickAndMortyCharacters(
        @Query("page") pageNumber: Int
    ): ApiResponse<CharacterModel>
}