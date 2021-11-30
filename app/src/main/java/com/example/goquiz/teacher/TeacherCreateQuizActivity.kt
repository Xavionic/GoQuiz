package com.example.goquiz.teacher

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.goquiz.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.teacher_create_quiz.*

class TeacherCreateQuizActivity : AppCompatActivity(){
    private lateinit var  auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teacher_create_quiz)
        title = "Create Quiz"
        auth = FirebaseAuth.getInstance()
    }

    fun createQuiz(view: View){
        val teacher_uid = auth.uid
        val desc = editTextDescription.text.toString()
        val start_time = editTextStartTime.text.toString()
        val end_time = editTextEndTime.text.toString()
        if (desc == "" || start_time == "" || end_time == ""){
            Toast.makeText(this, "Fill the create quiz form correctly!", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Berhasil membuat Quiz!", Toast.LENGTH_LONG).show()




            val intent= Intent(this, TeacherMainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}