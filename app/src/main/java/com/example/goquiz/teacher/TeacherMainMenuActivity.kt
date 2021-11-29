package com.example.goquiz.teacher


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.goquiz.R
import com.example.goquiz.authentification.LoginActivity
import com.example.goquiz.data.quiz.Quiz
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class TeacherMainMenuActivity : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teacher_activity_main_menu)
        auth = FirebaseAuth.getInstance()
        val fragUnc = TeacherFragmentUncompletedQuiz()
        val fragCom = TeacherFragmentCompletedQuiz()

        title()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.viewPagerTeacher, fragUnc)
            commit()
        }

        findViewById<Button>(R.id.buttonUncompleted).setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.viewPagerTeacher, fragUnc)
                commit()
            }
        }

        findViewById<Button>(R.id.buttonCompleted).setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.viewPagerTeacher, fragCom)
                commit()
            }
        }
    }

    fun title(){
        dbref = FirebaseDatabase.getInstance().getReference("users/${auth.uid.toString()}")
        val addValueEventListener =
            dbref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var name = snapshot.child("name").value.toString()
                    title = "Welcome teacher $name!"

                    var text = findViewById<TextView>(R.id.nameTeacher)
                    text.text = name
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show()
                }
            })
    }

    fun createQuiz(view: View){
        var intent = Intent(applicationContext, TeacherCreateQuizActivity::class.java)
        startActivity(intent)
    }

    fun navigateWithData(quiz: Quiz){
        supportFragmentManager.beginTransaction()
            .replace(R.id.viewPagerTeacher,TeacherDetailQuiz(quiz))
            .addToBackStack("")
            .commit()
    }

    fun signOut(view: View) {
        auth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}