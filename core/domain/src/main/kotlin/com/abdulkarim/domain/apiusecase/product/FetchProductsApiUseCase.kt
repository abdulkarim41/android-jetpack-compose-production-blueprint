package com.abdulkarim.domain.apiusecase.product

import com.abdulkarim.common.base.Result
import com.abdulkarim.domain.repository.product.ProductRepository
import com.abdulkarim.domain.usecase.ApiUseCaseNonParams
import com.abdulkarim.entity.product.ProductApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchProductsApiUseCase @Inject constructor(
    private val productRepository: ProductRepository
): ApiUseCaseNonParams<List<ProductApiEntity>> {

    override suspend fun execute(): Flow<Result<List<ProductApiEntity>>> {
        return productRepository.getProductsApi()
    }

}