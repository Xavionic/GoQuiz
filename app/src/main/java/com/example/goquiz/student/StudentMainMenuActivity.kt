package com.example.goquiz.student


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.goquiz.R
import com.example.goquiz.authentication.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.student_activity_main_menu.*

class StudentMainMenuActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_activity_main_menu)
        auth = FirebaseAuth.getInstance()
        val fragUnc = StudentFragmentUncompletedQuiz()
        val fragCom = StudentFragmentCompletedQuiz()

        title()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.viewPagerStudent, fragUnc)
            commit()
        }

        findViewById<Button>(R.id.btn_enroll).setOnClickListener{
            Toast.makeText(this, "Quiz belum tersedia", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.buttonUncompletedStudent).setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.viewPagerStudent, fragUnc)
                commit()
            }
        }

        findViewById<Button>(R.id.buttonCompletedStudent).setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.viewPagerStudent, fragCom)
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
                    title = "Welcome student $name!"

                    var text = findViewById<TextView>(R.id.nameStudent)
                    text.text = name
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show()
                }
            })
    }

    fun signOut(view: View) {
        auth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun enroll(view: View) {
//        ETenrollKey.text = "Enter Quiz e-Key Here"
        ETenrollKey.setText("")
        Toast.makeText(this, "Maaf fitur belum tersedia", Toast.LENGTH_SHORT).show()
    }
}