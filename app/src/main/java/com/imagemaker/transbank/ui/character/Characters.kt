package com.imagemaker.transbank.ui.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.imagemaker.transbank.model.models.CharacterModel
import com.imagemaker.transbank.tools.extensions.visible
import com.imagemaker.transbank.ui.theme.background
import com.imagemaker.transbank.viewmodel.MainViewModel

@Composable
fun Characters(viewModel: MainViewModel) {
    val characters: CharacterModel? by viewModel.characterList.collectAsState(initial = null)
    val isLoading: Boolean by viewModel.isLoading
    ConstraintLayout {
        val (progress, body) = createRefs()
        HomeCharacters(
            modifier = Modifier.background(background).constrainAs(body) {
                top.linkTo(parent.top)
            }.fillMaxHeight(),
            characters = characters)
        CircularProgressIndicator(
            modifier = Modifier
                .constrainAs(progress) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .visible(isLoading)
        )
    }

}