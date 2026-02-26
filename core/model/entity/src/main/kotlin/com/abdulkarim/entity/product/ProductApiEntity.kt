package com.abdulkarim.entity.product

data class ProductApiEntity(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val warrantyInformation: String,
    val shippingInformation: String,
    val availabilityStatus: String,
    val returnPolicy: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val minimumOrderQuantity: Int,
    val stock: Int,
    val brand: String,
    val thumbnail: String,
    val images: List<String>,
    val reviews: List<Review>,
    val meta: Meta,
    val tags: List<String>,

    ){
    data class Review(
        val rating: Int,
        val comment: String,
        val date: String,
        val reviewerName: String,
        val reviewerEmail: String,
    )
    data class Meta(
        val createdAt: String,
        val updatedAt: String,
        val barcode: String,
        val qrCode: String,
    )
}