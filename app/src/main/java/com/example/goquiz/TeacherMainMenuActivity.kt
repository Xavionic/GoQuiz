package com.example.goquiz


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.teacher_activity_main_menu.*


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
            replace(R.id.viewPager, fragUnc)
            commit()
        }

        findViewById<Button>(R.id.buttonNewQuiz).setOnClickListener{
            Toast.makeText(this, "Fitur buat quiz belum tersedia", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.buttonUncompleted).setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.viewPager, fragUnc)
                commit()
            }
        }

        findViewById<Button>(R.id.buttonCompleted).setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.viewPager, fragCom)
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

    fun signOut(view: View) {
        auth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}