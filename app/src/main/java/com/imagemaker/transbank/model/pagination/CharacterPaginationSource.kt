package com.imagemaker.transbank.model.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imagemaker.transbank.model.models.Result
import com.imagemaker.transbank.model.repository.CharacterRepository

class CharacterPaginationSource(private val characterRepository: CharacterRepository): PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val page = params.key ?: 1
            val photoResponse = characterRepository.loadRickAndMortyCharacters(page)
            LoadResult.Page(
                data = photoResponse!!.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

}
