package com.example.goquiz.teacher

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.goquiz.R
import com.example.goquiz.data.TempAnswer
import com.example.goquiz.data.TempQuestion
import com.example.goquiz.data.quiz.Answer


//class TeacherDetailQuiz(
//    private val kuis: Kuis
//) : Fragment() {
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.teacher_uncompleted_fragment_detail, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val tit = view.findViewById<TextView>(R.id.title_detail)
//        val detail = view.findViewById<TextView>(R.id.desc_detail)
//        detail.text = kuis.description
//        tit.text = kuis.start_time
//    }
//}

class TeacherQuestionDetail( val question : TempQuestion = TempQuestion()) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teacher_question_detail)

        val desc = findViewById<TextView>(R.id.tvQuestion)
        val one = findViewById<TextView>(R.id.tvAnswer1)
        val two = findViewById<TextView>(R.id.tvAnswer2)
        val three = findViewById<TextView>(R.id.tvAnswer3)
        val four = findViewById<TextView>(R.id.tvAnswer4)

//
//        var fragQuest = TeacherQuestionListFragment()
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.viewPagerQuestion, fragQuest)
//            commit()
//        }

        title = "Detail of question"
        desc.text = intent.getStringExtra("QUESTION")
//        val answersList = intent.getSerializableExtra("ANSWER_LIST") as ArrayList<TempAnswer>

        one.text = intent.getStringExtra("ANSWER1")
        two.text = intent.getStringExtra("ANSWER2")
        three.text = intent.getStringExtra("ANSWER3")
        four.text = intent.getStringExtra("ANSWER4")


    }
}