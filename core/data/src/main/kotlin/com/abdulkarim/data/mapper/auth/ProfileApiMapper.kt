package com.abdulkarim.data.mapper.auth

import com.abdulkarim.apiresponse.auth.ProfileApiResponse
import com.abdulkarim.data.mapper.Mapper
import com.abdulkarim.entity.auth.ProfileApiEntity
import javax.inject.Inject

class ProfileApiMapper @Inject constructor() : Mapper<ProfileApiResponse, ProfileApiEntity> {

    override fun mapFromApiResponse(type: ProfileApiResponse): ProfileApiEntity {
        return ProfileApiEntity(
            username = type.username ?: "",
            email = type.email ?: "",
            firstName = type.firstName ?: "",
            lastName = type.lastName ?: "",
            gender = type.gender ?: "",
            image = type.image ?: "",
        )
    }
}