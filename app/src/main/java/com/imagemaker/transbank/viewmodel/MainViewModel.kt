package com.imagemaker.transbank.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val application: Application,
): ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    fun getCharactersPagination(): Flow<PagingData<Result>> {
        return if (isOnline(application)){
            Pager(PagingConfig(pageSize = 20)) {
                CharacterPaginationSource(characterRepository)
            }.flow.cachedIn(viewModelScope)
        }else{
            val charactersLocal = characterRepository.loadLocalCharacters()
            flowOf(charactersLocal)
        }
    }

    private fun isOnline(context: Context): Boolean {
        var result = false // Returns connection type. 0: none; 1: mobile data; 2: wifi
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (cm != null) {
                val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        result = true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                        result = true
                    }
                }
            }
        } else {
            if (cm != null) {
                val activeNetwork = cm.activeNetworkInfo
                if (activeNetwork != null) {
                    // connected to the internet
                    when (activeNetwork.type) {
                        ConnectivityManager.TYPE_WIFI -> {
                            result = true
                        }
                        ConnectivityManager.TYPE_MOBILE -> {
                            result = true
                        }
                        ConnectivityManager.TYPE_VPN -> {
                            result = true
                        }
                    }
                }
            }
        }
        return result
    }
}