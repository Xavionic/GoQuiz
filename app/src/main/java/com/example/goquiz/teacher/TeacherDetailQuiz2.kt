package com.example.goquiz.teacher

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.goquiz.data.TempQuiz
import com.example.goquiz.R
import com.example.goquiz.data.TempQuestion
import com.example.goquiz.teacher.uncompleted_fragment.TeacherUncompletedQuizListFragment


class TeacherDetailQuiz2() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teacher_uncompleted_fragment_detail)

        val desc = findViewById<TextView>(R.id.tvQuizDescription)
        val start = findViewById<TextView>(R.id.tvQuizStartTime)
        val end = findViewById<TextView>(R.id.tvQuizEndTime)

//        supportFragmentManager.beginTransaction()
//            .add(R.id.viewPagerQuestion, TeacherQuestionListFragment())
//            .addToBackStack("")
//            .commit()

        title = "Detail of ${intent.getStringExtra("DESKRIPSI_KUIS")}"
        desc.text = intent.getStringExtra("DESKRIPSI_KUIS")
        start.text = "Start ${intent.getStringExtra("START_TIME_KUIS")}"
        end.text = "End ${intent.getStringExtra("END_TIME_KUIS")}"


    }

}