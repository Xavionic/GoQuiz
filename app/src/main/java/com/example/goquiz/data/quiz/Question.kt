package com.example.goquiz.data.quiz

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(
    var question: String?,
    var answers: ArrayList<Answer>,
    var correct_answer: String = "answer_id"
) : Parcelable
