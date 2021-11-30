package com.example.goquiz.teacher

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.goquiz.data.TempQuiz
import com.example.goquiz.R

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

        var fragQuest = TeacherFragmentQuestion()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.viewPagerQuestion, fragQuest)
            commit()
        }

        title = "Detail of ${intent.getStringExtra("DESKRIPSI_KUIS")}"
        desc.text = intent.getStringExtra("DESKRIPSI_KUIS")
        start.text = "Start ${intent.getStringExtra("START_TIME_KUIS")}"
        end.text = "End ${intent.getStringExtra("END_TIME_KUIS")}"


    }
}