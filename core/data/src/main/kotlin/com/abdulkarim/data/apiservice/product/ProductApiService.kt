package com.abdulkarim.data.apiservice.product

import com.abdulkarim.apiresponse.product.ProductApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {

    @GET("products")
    suspend fun getProductsApi() : Response<ProductApiResponse>

}