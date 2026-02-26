package com.abdulkarim.data.mapper.product

import com.abdulkarim.apiresponse.product.ProductApiResponse
import com.abdulkarim.data.mapper.Mapper
import com.abdulkarim.entity.product.ProductApiEntity
import javax.inject.Inject

class ProductApiMapper @Inject constructor() : Mapper<ProductApiResponse, List<ProductApiEntity>> {

    override fun mapFromApiResponse(type: ProductApiResponse): List<ProductApiEntity> {
        return type.products.map { item ->
            ProductApiEntity(
                id = item.id ?: -1,
                title = item.title ?: "",
                description = item.description ?: "",
                category = item.category ?: "",
                warrantyInformation = item.warrantyInformation ?: "",
                shippingInformation = item.shippingInformation ?: "",
                availabilityStatus = item.availabilityStatus ?: "",
                returnPolicy = item.returnPolicy ?: "",
                price = item.price ?: 0.0,
                discountPercentage = item.discountPercentage ?: 0.0,
                rating = item.rating ?: 0.0,
                minimumOrderQuantity = item.minimumOrderQuantity ?: 0,
                stock = item.stock ?: 0,
                brand = item.brand ?: "",
                thumbnail = item.thumbnail ?: "",
                images = item.images ?: emptyList(),
                reviews = item.reviews?.map {
                    ProductApiEntity.Review(
                        rating = it.rating ?: 0,
                        comment = it.comment ?: "",
                        date = it.date ?: "",
                        reviewerName = it.reviewerName ?: "",
                        reviewerEmail = it.reviewerEmail ?: "",
                    )
                } ?: emptyList(),
                meta = ProductApiEntity.Meta(
                    createdAt = item.meta?.createdAt ?: "",
                    updatedAt = item.meta?.updatedAt ?: "",
                    barcode = item.meta?.barcode ?: "",
                    qrCode = item.meta?.qrCode ?: "",
                ),
                tags = item.tags ?: emptyList()
            )
        }
    }
}