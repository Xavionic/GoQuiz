package com.example.goquiz.data.quiz

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseAnswer(
    var question_id : String?,
    var answer_id : String?
) : Parcelable