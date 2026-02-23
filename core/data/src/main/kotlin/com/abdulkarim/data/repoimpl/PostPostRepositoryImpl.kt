package com.abdulkarim.data.repoimpl

import com.abdulkarim.common.base.Result
import com.abdulkarim.data.apiservice.PostApiService
import com.abdulkarim.data.mapper.PostApiMapper
import com.abdulkarim.data.mapper.mapFromApiResponse
import com.abdulkarim.data.wrapper.NetworkBoundResource
import com.abdulkarim.domain.repository.PostRepository
import com.abdulkarim.entity.PostApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostPostRepositoryImpl @Inject constructor(
    private val networkBoundResource: NetworkBoundResource,
    private val postApiService: PostApiService,
    private val postApiMapper: PostApiMapper,

    ) : PostRepository {

    override suspend fun getPostListApi(): Flow<Result<List<PostApiEntity>>> {
        return mapFromApiResponse(
            result = networkBoundResource.fetchData {
                postApiService.getPostListApi()
            },
            mapper = postApiMapper
        )
    }

}