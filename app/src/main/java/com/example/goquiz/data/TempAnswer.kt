package com.example.goquiz.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TempAnswer(
    var answer : String = "",
    var correct : Boolean = false
): Parcelable
