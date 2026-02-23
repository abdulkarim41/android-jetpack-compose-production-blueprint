package com.abdulkarim.data.mapper

import com.abdulkarim.apiresponse.PostApiResponse
import com.abdulkarim.entity.PostApiEntity
import javax.inject.Inject

class PostApiMapper @Inject constructor() : Mapper<List<PostApiResponse>, List<PostApiEntity>> {

    override fun mapFromApiResponse(type: List<PostApiResponse>): List<PostApiEntity> {
        return type.map { item ->
            PostApiEntity(
                id = item.id ?: "",
                userId = item.userId ?: "",
                title = item.title ?: "",
                body = item.body ?: ""
            )
        }
    }
}