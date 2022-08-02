package com.example.grocerystoreapp.model

data class ProductResponse(
    val `data`: List<Data>,
    val meta: Meta
)

data class Data(
    val aisleLocations: List<AisleLocation>,
    val brand: String,
    val categories: List<String>,
    val countryOrigin: String,
    val description: String,
    val images: List<Image>,
    val itemInformation: ItemInformation,
    val items: List<Item>,
    val productId: String,
    val temperature: Temperature,
    val upc: String
)

data class Meta(
    val pagination: Pagination
)

data class AisleLocation(
    val bayNumber: String,
    val description: String,
    val number: String,
    val numberOfFacings: String,
    val shelfNumber: String,
    val shelfPositionInBay: String,
    val side: String
)

data class Image(
    val featured: Boolean,
    val perspective: String,
    val sizes: List<Size>
)

data class ItemInformation(
    val depth: String,
    val height: String,
    val width: String
)

data class Item(
    val favorite: Boolean,
    val fulfillment: Fulfillment,
    val itemId: String,
    val price: Price,
    val size: String,
    val soldBy: String
)

data class Temperature(
    val heatSensitive: Boolean,
    val indicator: String
)

data class Size(
    val size: String,
    val url: String
)

data class Fulfillment(
    val curbside: Boolean,
    val delivery: Boolean,
    val inStore: Boolean,
    val shipToHome: Boolean
)

data class Price(
    val promo: Int,
    val regular: Double
)

data class Pagination(
    val limit: Int,
    val start: Int,
    val total: Int
)