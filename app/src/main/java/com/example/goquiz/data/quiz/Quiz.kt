package com.example.goquiz.data.quiz

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Quiz(
    var teacher_uid: String?,
    var description: String?,
    var start_time: String = "yyyy/MM/dd HH:mm:ss",
    var end_time: String = "yyyy/MM/dd HH:mm:ss",
    var questions: ArrayList<Question>,
    var responses: ArrayList<Response>
) :Parcelable
