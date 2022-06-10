package com.imagemaker.transbank.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.imagemaker.transbank.model.models.CharacterModel
import com.imagemaker.transbank.model.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    characterRepository: CharacterRepository
): ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    val characterList:Flow<CharacterModel> = characterRepository.loadRickAndMortyCharacters(
        onStart = {
            _isLoading.value = true
        },
        onCompletion = {
            _isLoading.value = false
        },
        onError = {
            Timber.e(it)
        }
    )
}