package com.abdulkarim.data.apiservice

import com.abdulkarim.apiresponse.PostApiResponse
import retrofit2.Response
import retrofit2.http.*

interface PostApiService {

    @GET("posts")
    suspend fun getPostListApi() : Response<List<PostApiResponse>>

}