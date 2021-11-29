package com.example.goquiz.teacher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.goquiz.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
class TeacherCreateQuizActivity : AppCompatActivity(){
    private lateinit var  auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teacher_create_quiz)
        auth = FirebaseAuth.getInstance()
    }
}