package com.example.goquiz.teacher

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.goquiz.data.TempQuiz
import com.example.goquiz.R
import com.example.goquiz.data.TempQuestion
import com.example.goquiz.teacher.uncompleted_fragment.TeacherUncompletedQuizListFragment

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

class TeacherDetailQuiz( val kuis: TempQuiz = TempQuiz("-", "TERJADI KESALAHAN", "-", "-")) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teacher_uncompleted_fragment_detail)

        val desc = findViewById<TextView>(R.id.tvQuizDescription)
        val start = findViewById<TextView>(R.id.tvQuizStartTime)
        val end = findViewById<TextView>(R.id.tvQuizEndTime)

//        var fragQuest = TeacherFragmentQuestion()
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.viewPagerQuestion, fragQuest)
//            commit()
//        }

        supportFragmentManager.beginTransaction()
            .add(R.id.viewPagerQuestion, TeacherQuestionListFragment())
            .addToBackStack("")
            .commit()

        title = "Detail of ${intent.getStringExtra("DESKRIPSI_KUIS")}"
        desc.text = intent.getStringExtra("DESKRIPSI_KUIS")
        start.text = "Start ${intent.getStringExtra("START_TIME_KUIS")}"
        end.text = "End ${intent.getStringExtra("END_TIME_KUIS")}"


    }

    fun navigateWithData(question: TempQuestion){
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.viewPagerTeacher, TeacherUncompletedQuizDetail(kuis))
//            .addToBackStack("")
//            .commit()
//
//        var intent = Intent(applicationContext, TeacherDetailQuiz(kuis)::class.java)
//        startActivity(intent)

        var intent = Intent(applicationContext, TeacherQuestionDetail(question)::class.java)
        intent.putExtra("KUIS_ID", question.quiz_id);
        intent.putExtra("QUESTION", question.question);
//        intent.putExtra("ANSWER_LIST", question.answers);
        intent.putExtra("ANSWER_LIST0", question.answers[0].answer);
        intent.putExtra("ANSWER_LIST1", question.answers[1].answer);
        intent.putExtra("ANSWER_LIST2", question.answers[2].answer);
        intent.putExtra("ANSWER_LIST3", question.answers[3].answer);

        startActivity(intent)
    }
}