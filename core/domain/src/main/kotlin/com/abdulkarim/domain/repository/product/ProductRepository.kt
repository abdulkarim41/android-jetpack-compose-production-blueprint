package com.abdulkarim.domain.repository.product

import com.abdulkarim.common.base.Result
import com.abdulkarim.entity.product.ProductApiEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProductsApi() : Flow<Result<List<ProductApiEntity>>>

}