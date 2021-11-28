package com.example.goquiz.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Quiz(
    var quizTitle: String,
    var teacherName: String,
    var quizDuration: String,
    var quizDateTime: String,
    var status: String
) :Parcelable
