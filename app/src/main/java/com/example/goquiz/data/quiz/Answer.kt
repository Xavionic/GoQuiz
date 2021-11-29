package com.example.goquiz.data.quiz

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answer(
    var answer: String
) : Parcelable
