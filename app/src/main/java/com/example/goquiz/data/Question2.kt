package com.example.goquiz.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question2(
    var teacher_uid : String = "",
    var description : String = "",
    var start_time : String = "",
    var end_time : String = ""
): Parcelable
