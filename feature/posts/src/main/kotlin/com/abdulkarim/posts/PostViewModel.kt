package com.abdulkarim.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulkarim.domain.apiusecase.FetchPostsApiUseCase
import com.abdulkarim.common.base.Result
import com.abdulkarim.entity.PostApiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val fetchPostsApiUseCase: FetchPostsApiUseCase
) : ViewModel() {

    private val _postState = MutableStateFlow<Result<List<PostApiEntity>>>(Result.Loading(true))
    val postState = _postState.asStateFlow()

    init { fetchPosts() }

    fun fetchPosts() {
        viewModelScope.launch {
            fetchPostsApiUseCase.execute().collect { result ->
                _postState.value = result
            }
        }
    }
}