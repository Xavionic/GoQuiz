package com.example.goquiz.data.quiz

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(
    var student_uid : String?,
    var response_answer : ArrayList<ResponseAnswer>
) : Parcelable
