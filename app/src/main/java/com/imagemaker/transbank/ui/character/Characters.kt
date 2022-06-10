package com.imagemaker.transbank.ui.character

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.imagemaker.transbank.model.models.CharacterModel
import com.imagemaker.transbank.viewmodel.MainViewModel

@Composable
fun Characters(viewModel: MainViewModel) {
    val characters: CharacterModel? by viewModel.characterList.collectAsState(initial = null)

}