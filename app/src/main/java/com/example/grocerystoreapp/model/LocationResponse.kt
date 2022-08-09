package com.example.grocerystoreapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class LocationResponse(
    val list: List<Data>
)

@Parcelize
data class Data(
    val address: Address? = null,
    val chain: String? = null,
    val hours: HoursX? = null,
    val locationId: String? = null,
    val name: String? = null,
    val phone: String? = null
) : Parcelable

@Parcelize
data class Address(
    val addressLine1: String? = null,
    val city: String? = null,
    val county: String? = null,
    val state: String? = null,
    val zipCode: String? = null
) : Parcelable


@Parcelize
data class HoursX(
    val friday: FridayX? = null,
    val gmtOffset: String? = null,
    val monday: MondayX? = null,
    val open24: Boolean? = null,
    val saturday: SaturdayX? = null,
    val sunday: SundayX? = null,
    val thursday: ThursdayX? = null,
    val timezone: String? = null,
    val tuesday: TuesdayX? = null,
    val wednesday: WednesdayX? = null
) : Parcelable

@Parcelize
data class SundayX (
    val close: String? = null,
    val `open`: String? = null,
    val open24: Boolean? = null
) : Parcelable

@Parcelize
data class MondayX (
    val close: String? = null,
    val `open`: String? = null,
    val open24: Boolean? = null
) : Parcelable

@Parcelize
data class TuesdayX (
    val close: String? = null,
    val `open`: String? = null,
    val open24: Boolean? = null
) : Parcelable

@Parcelize
data class WednesdayX (
    val close: String? = null,
    val `open`: String? = null,
    val open24: Boolean? = null
) : Parcelable

@Parcelize
data class ThursdayX (
    val close: String? = null,
    val `open`: String? = null,
    val open24: Boolean? = null
) : Parcelable

@Parcelize
data class FridayX (
    val close: String? = null,
    val `open`: String? = null,
    val open24: Boolean? = null
) : Parcelable

@Parcelize
data class SaturdayX (
    val close: String? = null,
    val `open`: String? = null,
    val open24: Boolean? = null
) : Parcelable
