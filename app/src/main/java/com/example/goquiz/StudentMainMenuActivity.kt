package com.example.goquiz


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goquiz.data.Quiz
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.rv_completed_quiz.*
import java.util.*
import kotlin.collections.ArrayList

class StudentMainMenuActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_main_menu)
        auth = FirebaseAuth.getInstance()
        title = "Welcome Student ${auth.currentUser}"

        var quizTitle: Array<String> = arrayOf("UTS 1", "UTS 2")
        var teacherName: Array<String> = arrayOf("Wiwid", "AAN")
        var quizDuration: Array<String> = arrayOf("60 Min", "90 Min")
        var quizDateTime: Array<String> = arrayOf("2021-12-15 19:00", "2021-11-29 10:00")
        var status: Array<String> = arrayOf("Opened", "Closed")

        val list = ArrayList<Quiz>()
        val quiz1 = Quiz(quizTitle[0], teacherName[0], quizDuration[0], quizDateTime[0], status[0])
        list.add(quiz1)
        val quiz2 = Quiz(quizTitle[1], teacherName[1], quizDuration[1], quizDateTime[1], status[1])
        list.add(quiz2)

        val rvListQuiz: RecyclerView = findViewById(R.id.listQuiz)
        val studentMainMenuAdapter = StudentMainMenuAdapter(list)
        rvListQuiz.adapter = studentMainMenuAdapter
        rvListQuiz.layoutManager = LinearLayoutManager(this)

    }

        fun signOut(view: View) {
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

}