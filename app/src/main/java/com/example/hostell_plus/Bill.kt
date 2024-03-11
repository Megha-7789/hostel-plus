package com.example.hostell_plus

import android.os.Parcel
import android.os.Parcelable

data class Bill(
    val billId: Int,
    val amount: Double,
    val dueDate: String,

    // Add other properties
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString() ?: ""
        // Read other properties from parcel
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(billId)
        parcel.writeDouble(amount)
        parcel.writeString(dueDate)
        // Write other properties to parcel
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bill> {
        override fun createFromParcel(parcel: Parcel): Bill {
            return Bill(parcel)
        }

        override fun newArray(size: Int): Array<Bill?> {
            return arrayOfNulls(size)
        }
    }
}


