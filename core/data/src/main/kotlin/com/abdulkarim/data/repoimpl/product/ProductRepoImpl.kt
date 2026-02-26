package com.abdulkarim.data.repoimpl.product

import com.abdulkarim.common.base.Result
import com.abdulkarim.data.apiservice.product.ProductApiService
import com.abdulkarim.data.mapper.product.ProductApiMapper
import com.abdulkarim.data.mapper.mapFromApiResponse
import com.abdulkarim.data.wrapper.NetworkBoundResource
import com.abdulkarim.domain.repository.product.ProductRepository
import com.abdulkarim.entity.product.ProductApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepoImpl @Inject constructor(
    private val networkBoundResource: NetworkBoundResource,
    private val productApiService: ProductApiService,
    private val productApiMapper: ProductApiMapper,

    ) : ProductRepository {

    override suspend fun getProductsApi(): Flow<Result<List<ProductApiEntity>>> {
        return mapFromApiResponse(
            result = networkBoundResource.fetchData {
                productApiService.getProductsApi()
            },
            mapper = productApiMapper
        )
    }

}