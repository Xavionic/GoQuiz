package com.example.goquiz.teacher.uncompleted_fragment

import android.content.Intent
import com.example.goquiz.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goquiz.data.TempQuiz
import com.example.goquiz.student.StudentMainMenuActivity
import com.example.goquiz.teacher.TeacherMainMenuActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.teacher_fragment_uncompleted_quiz.*


class TeacherUncompletedQuizListFragment2 : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference
    private lateinit var quizRecyclerView : RecyclerView
    private lateinit var quizArrayList: ArrayList<TempQuiz>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.teacher_fragment_uncompleted_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        quizRecyclerView = rv_quizes
        quizRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        quizArrayList = arrayListOf<TempQuiz>()

        showQuiz()
    }

    fun showQuiz() {

        dbref = FirebaseDatabase.getInstance().getReference("/tmp_quizess")

        dbref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (quizSnapshot in snapshot.children) {
                        if (quizSnapshot.child("teacher_uid").value == auth.uid){
                            val quiz = quizSnapshot.getValue(TempQuiz::class.java)
                            quizArrayList.add(quiz!!)
                        }else{
                            continue
                        }
                    }

                    quizRecyclerView.adapter = TeacherUncompletedListQuizAdapter2(quizArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                print("Ada error di data quiz")
            }

        })
    }

}