package com.imagemaker.transbank.ui.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.imagemaker.transbank.model.models.Result
import com.imagemaker.transbank.ui.theme.shimmerHighLight
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun HomeCharacters(
    modifier: Modifier,
    charactersPagingData: LazyPagingItems<Result>,
    onClickCharacter: (character: Result) -> Unit
) {

    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colors.background)
    ) {
        items(charactersPagingData){ character ->
            character?.let {
                CharacterItem(
                    character = it,
                    onClickCharacter = onClickCharacter)
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterItem(character: Result, onClickCharacter: (character: Result) -> Unit?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        onClick = {
            onClickCharacter(character)
        }
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            CoilImage(
                imageModel = character.image,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                contentScale = ContentScale.Crop,
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = shimmerHighLight,
                    dropOff = 0.65f
                ),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Column {

                Text(
                    text = character.name, style = TextStyle(
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = when (character.status) {
                        "Alive" -> "Esta vivo"
                        "Dead" -> "Esta muerto"
                        else -> "Desconocido"
                    },
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Normal
                    ),
                    color = when (character.status) {
                        "Alive" -> Color(74, 148, 0, 255)
                        "Dead" -> Color.Red
                        else -> Color.Blue
                    }
                )
                Text(
                    text = "Especie: ${character.species}",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Normal
                    )
                )
            }

        }
    }

}

@Preview
@Composable
fun CharacterItemPreview() {
    CharacterItem(
        Result(
            created = "2017-11-04T18:48:46.250Z",
            episode = emptyList(),
            gender = "Male",
            id = 1,
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            location = null,
            name = "Rick Sanchez",
            origin = null,
            species = "Human",
            status = "Alive",
            type = "",
            url = "https://rickandmortyapi.com/api/character/1"
        )
    ) {

    }
}

