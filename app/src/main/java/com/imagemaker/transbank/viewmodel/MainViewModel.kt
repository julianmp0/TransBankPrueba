package com.imagemaker.transbank.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.imagemaker.transbank.model.models.Result
import com.imagemaker.transbank.model.pagination.CharacterPaginationSource
import com.imagemaker.transbank.model.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
): ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    fun getCharactersPagination(): Flow<PagingData<Result>> {
        return Pager(PagingConfig(pageSize = 20)) {
            CharacterPaginationSource(characterRepository)
        }.flow.cachedIn(viewModelScope)
    }
}