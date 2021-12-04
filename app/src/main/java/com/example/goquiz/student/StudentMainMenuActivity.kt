package com.example.goquiz.student


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.goquiz.R
import com.example.goquiz.authentication.LoginActivity
import com.example.goquiz.student.uncompleted_fragment.StudentFragmentUncompletedQuiz
import com.example.goquiz.teacher.uncompleted_fragment.StudentUncompletedQuizListFragment
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

//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.viewPagerStudent, fragUnc)
//            commit()
//        }

        supportFragmentManager.beginTransaction()
            .add(R.id.viewPagerStudent, StudentUncompletedQuizListFragment())
            .addToBackStack("")
            .commit()

        findViewById<Button>(R.id.buttonUncompletedStudent).setOnClickListener{
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.viewPagerStudent, fragUnc)
//                commit()
//            }
            supportFragmentManager.beginTransaction()
                .add(R.id.viewPagerStudent, StudentUncompletedQuizListFragment())
                .addToBackStack("")
                .commit()
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
        var enroll : EditText = ETenrollKey
        if (ETenrollKey.text.toString() == ""){
            Toast.makeText(this, "Fill the form correctly!", Toast.LENGTH_SHORT).show()
        }else{
            createQuiz(ETenrollKey.text.toString())
        }

    }

    fun createQuiz(key: String){
        dbref = FirebaseDatabase.getInstance().getReference()
        val addValueEventListener =
            dbref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var exist : Boolean = false
                    for (quiz in snapshot.child("/tmp_quizess").children){
                        if (quiz.key.toString() == key){
                            exist = true
                            break
                        }
                    }

                    if (exist){
                        val dummy = mapOf<String, String>("dummy" to "")
                        dbref.child("/tmp_quizess").child(key).child("/participants").child("${auth.uid}").setValue(dummy)
                        supportFragmentManager.beginTransaction()
                            .add(R.id.viewPagerStudent, StudentUncompletedQuizListFragment())
                            .addToBackStack("")
                            .commit()
                        Toast.makeText(applicationContext, "Successfully joining quiz", Toast.LENGTH_LONG).show()

                    }else{
                        Toast.makeText(applicationContext, "Wrong enrollment key!", Toast.LENGTH_SHORT).show()
                    }
                    ETenrollKey.setText("")
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show()
                }
            })
    }

}