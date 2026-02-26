package com.abdulkarim.data.mapper.auth

import com.abdulkarim.apiresponse.auth.LoginApiResponse
import com.abdulkarim.data.mapper.Mapper
import com.abdulkarim.entity.auth.LoginApiEntity
import javax.inject.Inject

class LoginApiMapper @Inject constructor() : Mapper<LoginApiResponse, LoginApiEntity> {

    override fun mapFromApiResponse(type: LoginApiResponse): LoginApiEntity {
        return LoginApiEntity(
            accessToken = type.accessToken ?: "",
            refreshToken = type.refreshToken ?: "",
        )
    }
}