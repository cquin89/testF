package com.losingtimeapps.fababellatest.presentation.model

import android.os.Parcel
import android.os.Parcelable
import com.losingtimeapps.fababellatest.domain.entity.Indicator

class IndicatorModel(
    val code: String,
    val name: String,
    val measuredUnit: String,
    val date: String,
    val value: Double
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(code)
        parcel.writeString(name)
        parcel.writeString(measuredUnit)
        parcel.writeString(date)
        parcel.writeDouble(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IndicatorModel> {
        override fun createFromParcel(parcel: Parcel): IndicatorModel {
            return IndicatorModel(parcel)
        }

        override fun newArray(size: Int): Array<IndicatorModel?> {
            return arrayOfNulls(size)
        }
    }

}