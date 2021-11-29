package com.example.goquiz.authentification

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.goquiz.R
import com.example.goquiz.student.StudentMainMenuActivity
import com.example.goquiz.teacher.TeacherMainMenuActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference
//    private lateinit var userArrayList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title="Login"
        auth= FirebaseAuth.getInstance()
    }

    fun login(view: View){
        val email=editTextEmailAddress.text.toString()
        val password=editTextPassword.text.toString()

        if (email == "" || password == ""){
            Toast.makeText(this, "Fill the form correctly!", Toast.LENGTH_SHORT).show()
        }else{
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    //Integrating firebase auth and realtime database
                    dbref = FirebaseDatabase.getInstance().getReference("users/${auth.uid.toString()}")
                    val addValueEventListener =
                        dbref.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
//                            var name = snapshot.child("name").value.toString()
                                var role_id = snapshot.child("role_id").value.toString()

                                if (role_id.toInt() == 1){
                                    var intent = Intent(applicationContext, StudentMainMenuActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }else{
                                    var intent = Intent(applicationContext, TeacherMainMenuActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show()
                            }
                        })
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }


    }

    fun goToRegister(view: View){
        val intent= Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}