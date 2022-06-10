package com.imagemaker.transbank.model.repository

import androidx.annotation.WorkerThread
import com.imagemaker.transbank.model.network.RickAndMortyService
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val rickAndMortyService: RickAndMortyService
) {

    @WorkerThread
    fun loadRickAndMortyCharacters(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        rickAndMortyService.fetchRickAndMortyCharacters()
            .suspendOnSuccess {
                emit(data)
            }
            .onFailure {
                onError(message())
            }
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

}