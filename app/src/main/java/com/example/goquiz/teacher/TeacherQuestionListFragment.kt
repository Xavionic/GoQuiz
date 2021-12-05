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


//        val ansList = ArrayList<TempAnswer>()
//        val ans1 = TempAnswer("Dua Puluh")
//        val ans2 = TempAnswer("Jakarta Selatan")
//        val ans3 = TempAnswer("381,56432111", true)
//        val ans4 = TempAnswer("Ya")
//        ansList.add(ans1)
//        ansList.add(ans2)
//        ansList.add(ans3)
//        ansList.add(ans4)
//
//        val listQuestion = ArrayList<TempQuestion>()
//        val question1 = TempQuestion("hx4AafymSfduHRNw0vlCkL8uYD73", "Berapakah satu ditambah satu dengan asumsi satu bukan bilangan yang dapat ditambah?", ansList)
//        val question2 = TempQuestion("hx4AafymSfduHRNw0vlCkL8uYD73", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque gravida nunc vitae quam porttitor pellentesque. Aliquam non quam a mi sodales congue sit amet ut ex. Donec blandit nunc eu risus posuere, ut interdum diam feugiat. Praesent dignissim auctor pharetra. Ut id interdum odio, ac venenatis metus. Integer sed accumsan nibh, non consequat elit. Phasellus eget mattis turpis. Curabitur vitae consectetur ipsum, at ornare lacus. Praesent eget libero non quam suscipit consectetur. In iaculis mauris ipsum, vitae ultrices mauris blandit at.", ansList)
//        val question3 = TempQuestion("hx4AafymSfduHRNw0vlCkL8uYD73", "Pertanyaan tiga", ansList)
//        val question4 = TempQuestion("hx4AafymSfduHRNw0vlCkL8uYD73", "Pertanyaan empat", ansList)
//        val question5 = TempQuestion("hx4AafymSfduHRNw0vlCkL8uYD73", "Pertanyaan lima", ansList)
//        val question6 = TempQuestion("hx4AafymSfduHRNw0vlCkL8uYD73", "Pertanyaan enam", ansList)
//        val question7 = TempQuestion("hx4AafymSfduHRNw0vlCkL8uYD73", "Pertanyaan tujuh", ansList)
//
//
//        listQuestion.add(question1)
//        listQuestion.add(question2)
//        listQuestion.add(question3)
//        listQuestion.add(question4)
//        listQuestion.add(question5)
//        listQuestion.add(question6)
//        listQuestion.add(question7)
//
//        val rvQuestion: RecyclerView = view.findViewById(R.id.rv_questions)
//        val listQuestionAdapter = TeacherQuestionListAdapter(listQuestion, this)
//        rvQuestion.adapter = listQuestionAdapter
//        rvQuestion.layoutManager = LinearLayoutManager(requireContext())
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