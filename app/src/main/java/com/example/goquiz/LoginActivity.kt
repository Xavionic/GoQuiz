package com.example.goquiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.goquiz.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference
    private lateinit var userArrayList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title="Login"

        auth= FirebaseAuth.getInstance()
    }

    fun getRoleData(){
        dbref = FirebaseDatabase.getInstance().getReference("users/${auth.uid.toString()}")
        val addValueEventListener =
            dbref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var name = snapshot.child("name").value.toString()
                    var role_id = snapshot.child("role_id").value.toString()
                    var user = User(name, role_id)
                    Toast.makeText(applicationContext, "nama ${name} role idnya ${role_id}", Toast.LENGTH_LONG).show()
                    userArrayList.add(user)
//                    var name2 : String = userArrayList.last().name.toString()
//                    var role_id2: String = userArrayList.last().role_id.toString()

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        applicationContext,
                        error.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }

    fun login(view: View){
        val email=editTextEmailAddress.text.toString()
        val password=editTextPassword.text.toString()
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                //Integrating firebase auth and realtime database
                getRoleData()


//                val intent= Intent(this,MainActivity::class.java)
//                startActivity(intent)
//                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    fun goToRegister(view: View){
        val intent= Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
}