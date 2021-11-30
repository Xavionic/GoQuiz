package com.example.goquiz.data

import android.os.Parcelable
import com.example.goquiz.data.quiz.Answer
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TempQuestion(
    var quiz_id: String = "",
    var question : String = "",
    var answers : ArrayList<TempAnswer>
): Parcelable
