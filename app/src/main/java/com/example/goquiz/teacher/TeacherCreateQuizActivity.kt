package com.example.goquiz.teacher

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.goquiz.R
import com.example.goquiz.data.TempQuestion
import com.example.goquiz.student.StudentMainMenuActivity
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
        val teacher_uid = auth.uid.toString()
        val desc = editTextDescription.text
        val start_time = editTextStartTime.text
        val end_time = editTextEndTime.text

        val quizData = mapOf<String, String>(
            "description" to desc.toString(),
            "end_time" to end_time.toString(),
            "start_time" to start_time.toString(),
            "teacher_uid" to teacher_uid
        )


        if (desc.toString() == "" || start_time.toString() == "" || end_time.toString() == ""){
            Toast.makeText(this, "Fill the create quiz form correctly!", Toast.LENGTH_SHORT).show()
        }else{

            dbref = FirebaseDatabase.getInstance().getReference("/tmp_quizess")

            dbref.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var buffer = "10000000"

                    for (quiz in snapshot.children){
                        buffer = quiz.key.toString()
                    }

                    var quiz_id : Int = buffer.toInt() + 1

                    val quiz = dbref.child(quiz_id.toString())
                    quiz.setValue(quizData)

                    Toast.makeText(applicationContext, "Successfully create quiz!", Toast.LENGTH_LONG).show()
                    val intent= Intent(applicationContext, TeacherMainMenuActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show()
                }
            })



        }
    }
}