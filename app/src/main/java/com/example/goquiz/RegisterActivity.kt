package com.example.goquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        title="Register"
        radioGroup = findViewById(R.id.radioRoles)
        auth= FirebaseAuth.getInstance()
    }

    fun register(view: View){

        if(editTextEmailAddress.text.isEmpty() || editTextPassword.text.isEmpty() || editTextName.text.isEmpty()){
            Toast.makeText(this, "Fill entire form correctly!", Toast.LENGTH_SHORT).show()
        }else{
            val email=editTextEmailAddress.text.toString()
            val password=editTextPassword.text.toString()
            val name= editTextName.text.toString()

            //role_id 1 = student, 2 = teacher
            val role_id:Int
            if (radioButtonStudent.isChecked){
                role_id = 1
            }else{
                role_id = 2
            }

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                var mail: String = auth.currentUser?.email.toString()
                var uid: String = auth.uid.toString()

                val dataPengguna = HashMap<String, Any>()
                dataPengguna.put("name", name)
                dataPengguna.put("role_id", role_id)

                database = FirebaseDatabase.getInstance().getReference("users")
                val user = database.child(uid)

                //Memasukkan anak baru ke database "users"
                user.setValue(dataPengguna)
                        //debug
//                    .addOnSuccessListener {
//                    Toast.makeText(applicationContext, "email $mail  uidnya $uid role idnya $role_id", Toast.LENGTH_LONG).show()
//                }

                val intent: Intent
                if (role_id == 1){
                    intent = Intent(this, StudentMainMenuActivity::class.java)
                }else{
                    intent = Intent(this, TeacherMainMenuActivity::class.java)
                }

                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }
        }


    }

    fun goToLogin(view: View){
        val intent= Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

}