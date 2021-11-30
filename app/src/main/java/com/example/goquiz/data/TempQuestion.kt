package com.example.goquiz.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TempQuestion(
    var teacher_uid : String = "",
    var description : String = "",
    var start_time : String = "",
    var end_time : String = ""
): Parcelable
