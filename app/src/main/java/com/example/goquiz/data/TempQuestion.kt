package com.example.goquiz.data

import android.os.Parcelable
import com.example.goquiz.data.quiz.Answer
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TempQuestion(
//    var quiz_id: String = "",
    var question : String = "",
    var answer1: String = "",
    var answer2: String = "",
    var answer3: String = "",
    var answer4: String = "",
    var true_answer : String = "answer1"
//    var answers : ArrayList<TempAnswer>
): Parcelable
