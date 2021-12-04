package com.example.goquiz.teacher.uncompleted_fragment

import android.content.ContentValues.TAG
import android.content.Intent
import com.example.goquiz.R
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goquiz.data.TempQuiz
import com.example.goquiz.student.uncompleted_fragment.StudentUncompletedListQuizAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.student_fragment_uncompleted_quiz.*
import kotlinx.android.synthetic.main.teacher_fragment_uncompleted_quiz.*


class StudentUncompletedQuizListFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference
    private lateinit var quizRecyclerView: RecyclerView
    private lateinit var quizArrayList: ArrayList<TempQuiz>
    private lateinit var teacherNameList: ArrayList<String>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.student_fragment_uncompleted_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        quizRecyclerView = rv_quizes_student
        quizRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        quizArrayList = arrayListOf<TempQuiz>()
        teacherNameList = arrayListOf<String>()

        showQuiz()
    }

    fun showQuiz() {

        dbref = FirebaseDatabase.getInstance().getReference()
        dbref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (quiz in snapshot.child("/tmp_quizess").children){

                    for (participan in quiz.child("/participants").children){
                        if (participan.exists()){
                            if (participan.key.toString() == auth.uid.toString()){
                                val quizData = quiz.getValue(TempQuiz::class.java)
                                quizArrayList.add(quizData!!)
                                var teacherName = snapshot.child("/users").child(quizData.teacher_uid).child("name").value.toString()
                                teacherNameList.add(teacherName)
                            }
                        }

                    }
                }
                quizRecyclerView.adapter = StudentUncompletedListQuizAdapter(quizArrayList, teacherNameList)
            }

            override fun onCancelled(error: DatabaseError) {
                print("Ada error di data quiz")
            }
        }


        )

    }
}