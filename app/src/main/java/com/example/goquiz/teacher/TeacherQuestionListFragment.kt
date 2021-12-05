package com.example.goquiz.teacher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goquiz.R
import com.example.goquiz.data.TempAnswer
import com.example.goquiz.data.TempQuestion
import com.example.goquiz.data.TempQuiz
import com.example.goquiz.teacher.uncompleted_fragment.TeacherUncompletedListQuizAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.teacher_fragment_question.*
import kotlinx.android.synthetic.main.teacher_fragment_uncompleted_quiz.*


class TeacherQuestionListFragment(private val quizID : String) : Fragment()
    , TeacherQuestionListAdapter.OnItemClickListener
{
    // TODO: Rename and change types of parameters
    private lateinit var  auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference
    private lateinit var questionRecyclerView : RecyclerView
    private lateinit var questionArrayList: ArrayList<TempQuestion>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.teacher_fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        questionRecyclerView = rv_questions
        questionRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        questionArrayList = arrayListOf<TempQuestion>()
        showQuestions()
    }

    fun showQuestions(){
        dbref = FirebaseDatabase.getInstance().getReference("/tmp_quizess")

        dbref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (question in snapshot.child(quizID).child("questions").children){
                    val quest = question.getValue(TempQuestion::class.java)
                    questionArrayList.add(quest!!)
                }

                questionRecyclerView.adapter = TeacherQuestionListAdapter(questionArrayList, this@TeacherQuestionListFragment)
            }

            override fun onCancelled(error: DatabaseError) {
                print("Ada error di data question")
            }

        })
    }

    override fun onItemClicked(productModel: TempQuestion) {
        (activity as TeacherDetailQuiz).navigateWithData(question = productModel)
    }
}