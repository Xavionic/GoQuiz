package com.example.goquiz.teacher


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.goquiz.R
import com.example.goquiz.authentication.LoginActivity
import com.example.goquiz.data.TempQuiz
import com.example.goquiz.teacher.uncompleted_fragment.TeacherFragmentUncompletedQuiz
import com.example.goquiz.teacher.uncompleted_fragment.TeacherUncompletedQuizListFragment
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

        supportFragmentManager.beginTransaction()
            .add(R.id.viewPagerTeacher,TeacherUncompletedQuizListFragment())
            .addToBackStack("")
            .commit()


        findViewById<Button>(R.id.buttonUncompleted).setOnClickListener{
            supportFragmentManager.beginTransaction()
                .add(R.id.viewPagerTeacher,TeacherUncompletedQuizListFragment())
                .addToBackStack("")
                .commit()
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

    fun navigateWithData(kuis: TempQuiz, quizID : String){
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.viewPagerTeacher, TeacherUncompletedQuizDetail(kuis))
//            .addToBackStack("")
//            .commit()
//
//        var intent = Intent(applicationContext, TeacherDetailQuiz(kuis)::class.java)
//        startActivity(intent)

        var intent = Intent(applicationContext, TeacherDetailQuiz(kuis)::class.java)
        intent.putExtra("UID_KUIS", kuis.teacher_uid);
        intent.putExtra("ID_KUIS", quizID);
        intent.putExtra("DESKRIPSI_KUIS", kuis.description);
        intent.putExtra("START_TIME_KUIS", kuis.start_time);
        intent.putExtra("END_TIME_KUIS", kuis.end_time);

        startActivity(intent)
    }

    fun signOut(view: View) {
        auth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}