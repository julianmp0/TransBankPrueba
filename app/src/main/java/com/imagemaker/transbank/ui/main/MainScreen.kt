package com.imagemaker.transbank.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.imagemaker.transbank.model.models.Result
import com.imagemaker.transbank.ui.character.Characters

@Composable
fun MainScreen(onClickCharacter: (character: Result) -> Unit) {
    Characters(
        viewModel = hiltViewModel(),
        onClickCharacter = onClickCharacter
    )
}


