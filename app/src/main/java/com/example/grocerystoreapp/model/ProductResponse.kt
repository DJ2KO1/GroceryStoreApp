package com.example.grocerystoreapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ProductResponse(
    val list: List<ProductData>,
)

@Parcelize
data class ProductData(
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
) : Parcelable

@Parcelize
data class AisleLocation(
    val bayNumber: String,
    val description: String,
    val number: String,
    val numberOfFacings: String,
    val shelfNumber: String,
    val shelfPositionInBay: String,
    val side: String
) : Parcelable

@Parcelize
data class Image(
    val featured: Boolean,
    val perspective: String,
    val sizes: List<Size>
) : Parcelable

@Parcelize
data class ItemInformation(
    val depth: String,
    val height: String,
    val width: String
) : Parcelable

@Parcelize
data class Item(
    val favorite: Boolean,
    val fulfillment: Fulfillment,
    val itemId: String,
    val price: Price,
    val size: String,
    val soldBy: String
) : Parcelable

@Parcelize
data class Temperature(
    val heatSensitive: Boolean,
    val indicator: String
) : Parcelable

@Parcelize
data class Size(
    val size: String,
    val url: String
) : Parcelable

@Parcelize
data class Fulfillment(
    val curbside: Boolean,
    val delivery: Boolean,
    val inStore: Boolean,
    val shipToHome: Boolean
) : Parcelable

@Parcelize
data class Price(
    val promo: Int,
    val regular: Double
) : Parcelable
