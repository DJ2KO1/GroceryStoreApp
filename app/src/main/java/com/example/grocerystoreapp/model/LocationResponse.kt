package com.example.grocerystoreapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class LocationResponse(
    val locationData: List<LocationData>,
)

@Parcelize
data class LocationData(
    val address: Address,
    val chain: String,
    val departments: List<Department>,
    val geolocation: GeolocationX,
    val hours: HoursX,
    val locationId: String,
    val name: String,
    val phone: String
) : Parcelable

@Parcelize
data class Address(
    val addressLine1: String,
    val city: String,
    val county: String,
    val state: String,
    val zipCode: String
) : Parcelable

@Parcelize
data class Department(
    val address: AddressX,
    val departmentId: String,
    val geolocation: GeolocationX,
    val hours: Hours,
    val name: String,
    val offsite: Boolean,
    val phone: String
) : Parcelable

@Parcelize
data class GeolocationX(
    val latLng: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable

@Parcelize
data class HoursX(
    val friday: FridayX,
    val gmtOffset: String,
    val monday: MondayX,
    val open24: Boolean,
    val saturday: SaturdayX,
    val sunday: SundayX,
    val thursday: ThursdayX,
    val timezone: String,
    val tuesday: TuesdayX,
    val wednesday: WednesdayX
) : Parcelable

@Parcelize
data class SundayX (
    val close: String,
    val `open`: String,
    val open24: Boolean
) : Parcelable

@Parcelize
data class MondayX (
    val close: String,
    val `open`: String,
    val open24: Boolean
) : Parcelable

@Parcelize
data class TuesdayX (
    val close: String,
    val `open`: String,
    val open24: Boolean
) : Parcelable

@Parcelize
data class WednesdayX (
    val close: String,
    val `open`: String,
    val open24: Boolean
) : Parcelable

@Parcelize
data class ThursdayX (
    val close: String,
    val `open`: String,
    val open24: Boolean
) : Parcelable

@Parcelize
data class FridayX (
    val close: String,
    val `open`: String,
    val open24: Boolean
) : Parcelable

@Parcelize
data class SaturdayX (
    val close: String,
    val `open`: String,
    val open24: Boolean
) : Parcelable

@Parcelize
data class AddressX(
    val addressLine1: String,
    val city: String,
    val state: String,
    val zipCode: String
) : Parcelable

@Parcelize
data class Hours(
    val friday: Friday,
    val monday: Monday,
    val open24: Boolean,
    val saturday: Saturday,
    val sunday: Sunday,
    val thursday: Thursday,
    val tuesday: Tuesday,
    val wednesday: Wednesday
) : Parcelable

@Parcelize
data class Friday(
    val close: String,
    val `open`: String,
    val open24: Boolean
) : Parcelable

@Parcelize
data class Monday(
    val close: String,
    val `open`: String,
    val open24: Boolean
) : Parcelable

@Parcelize
data class Saturday(
    val close: String,
    val `open`: String,
    val open24: Boolean
) : Parcelable

@Parcelize
data class Sunday(
    val close: String,
    val `open`: String,
    val open24: Boolean
) : Parcelable

@Parcelize
data class Thursday(
    val close: String,
    val `open`: String,
    val open24: Boolean
) : Parcelable

@Parcelize
data class Tuesday(
    val close: String,
    val `open`: String,
    val open24: Boolean
) : Parcelable

@Parcelize
data class Wednesday(
    val close: String,
    val `open`: String,
    val open24: Boolean
) : Parcelable
