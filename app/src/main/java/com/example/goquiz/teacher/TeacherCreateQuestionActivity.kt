package com.example.goquiz.teacher

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.goquiz.R
import com.example.goquiz.data.TempQuiz
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.teacher_create_question.*
import kotlinx.android.synthetic.main.teacher_create_quiz.*

class TeacherCreateQuestionActivity() : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference
    var radioGroup: RadioGroup? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teacher_create_question)
        title = "Create Question"
        auth = FirebaseAuth.getInstance()
        radioGroup = findViewById(R.id.radioRoles)

    }

    fun addQuestion(view: View){
        if (editTextQuestion.text.isEmpty() || editTextAnswer1.text.isEmpty() || editTextAnswer2.text.isEmpty()|| editTextAnswer3.text.isEmpty()|| editTextAnswer4.text.isEmpty()){
            Toast.makeText(this, "Fill entire form correctly!", Toast.LENGTH_SHORT).show()
        }else{
            val true_answer:String
            if (radioAnswer1.isChecked){
                true_answer = "answer1"
            }else if(radioAnswer2.isChecked){
                true_answer = "answer2"
            }else if(radioAnswer3.isChecked){
                true_answer = "answer3"
            }else{
                true_answer = "answer4"
            }


            val questionData = mapOf<String, String>(
                "question" to editTextQuestion.text.toString(),
                "answer1" to editTextAnswer1.text.toString(),
                "answer2" to editTextAnswer2.text.toString(),
                "answer3" to editTextAnswer3.text.toString(),
                "answer4" to editTextAnswer4.text.toString(),
                "true_answer" to true_answer
            )
            dbref = FirebaseDatabase.getInstance().getReference("/tmp_quizess")

            dbref.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    var buffer : String = "${intent.getStringExtra("ID_KUIS")}0000"
                    if (snapshot.exists()){
                        for (question in snapshot.child(intent.getStringExtra("ID_KUIS").toString()).child("questions").children){
                            buffer = question.key.toString()
                            Log.e("asd", "${buffer}")
                        }
                    }

                    var question_id : Long = buffer.toLong() + 1

                    val quest = dbref.child(intent.getStringExtra("ID_KUIS").toString()).child("questions").child(question_id.toString())
                    quest.setValue(questionData)


                    Toast.makeText(applicationContext, "Successfully adding question!", Toast.LENGTH_LONG).show()
                    var intent2 = Intent(applicationContext, TeacherDetailQuiz()::class.java)
//                    intent2.putExtra("UID_KUIS", kuis.teacher_uid);
                    intent2.putExtra("ID_KUIS", intent.getStringExtra("ID_KUIS").toString())
                    intent2.putExtra("DESKRIPSI_KUIS", intent.getStringExtra("DESKRIPSI_KUIS").toString())
                    intent2.putExtra("START_TIME_KUIS", intent.getStringExtra("START_TIME_KUIS").toString())
                    intent2.putExtra("END_TIME_KUIS", intent.getStringExtra("END_TIME_KUIS").toString())
//                    var intent2 = Intent(applicationContext, TeacherMainMenuActivity::class.java)
                    startActivity(intent2)
                    finish()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show()
                }
            })



        }


    }

}