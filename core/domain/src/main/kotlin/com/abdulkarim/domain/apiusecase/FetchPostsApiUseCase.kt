package com.abdulkarim.domain.apiusecase

import com.abdulkarim.common.base.Result
import com.abdulkarim.domain.repository.PostRepository
import com.abdulkarim.domain.usecase.ApiUseCaseNonParams
import com.abdulkarim.entity.PostApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPostsApiUseCase @Inject constructor(
    private val postRepository: PostRepository
): ApiUseCaseNonParams<List<PostApiEntity>> {

    override suspend fun execute(): Flow<Result<List<PostApiEntity>>> {
        return postRepository.getPostListApi()
    }

}

