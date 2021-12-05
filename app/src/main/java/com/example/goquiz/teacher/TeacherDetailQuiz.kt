package com.example.goquiz.teacher

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.goquiz.R
import com.example.goquiz.data.TempQuestion
import com.example.goquiz.data.TempQuiz
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


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

class TeacherDetailQuiz() : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teacher_uncompleted_fragment_detail)

        val desc = findViewById<TextView>(R.id.tvQuizDescription)
        val quiz_id = findViewById<TextView>(R.id.tvQuizID)
        val start = findViewById<TextView>(R.id.tvQuizStartTime)
        val end = findViewById<TextView>(R.id.tvQuizEndTime)

        supportFragmentManager.beginTransaction()
            .add(R.id.viewPagerQuestion, TeacherQuestionListFragment(intent.getStringExtra("ID_KUIS").toString()))
            .addToBackStack("")
            .commit()

        title = "Detail of ${intent.getStringExtra("DESKRIPSI_KUIS")}"
        desc.text = intent.getStringExtra("DESKRIPSI_KUIS")
        quiz_id.text = "Enrollment key: ${intent.getStringExtra("ID_KUIS")}"
        start.text = "Start ${intent.getStringExtra("START_TIME_KUIS")}"
        end.text = "End ${intent.getStringExtra("END_TIME_KUIS")}"


    }

    fun navigateWithData(question: TempQuestion){

        var intent = Intent(applicationContext, TeacherQuestionDetail(question)::class.java)
        intent.putExtra("KUIS_ID", intent.getStringExtra("ID_KUIS"));
        intent.putExtra("QUESTION", question.question);
        intent.putExtra("ANSWER1", question.answer1);
        intent.putExtra("ANSWER2", question.answer2);
        intent.putExtra("ANSWER3", question.answer3);
        intent.putExtra("ANSWER4", question.answer4);
        intent.putExtra("ANSWERTRUE", question.true_answer);


        startActivity(intent)
    }

    fun delete(view: View){
        AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Delete Quiz Confirmation").setMessage("Are you sure you want to delete this quiz?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                finish()

                dbref = FirebaseDatabase.getInstance().getReference("/tmp_quizess")
                dbref.child(intent.getStringExtra("ID_KUIS").toString()).removeValue()

                Toast.makeText(applicationContext, "Quiz deleted", Toast.LENGTH_LONG).show()
                var intent = Intent(applicationContext, TeacherMainMenuActivity::class.java)
                startActivity(intent)
                finish()
            }).setNegativeButton("No", null).show()
    }

    fun addQuestion(view: View){
//        var intent2 = Intent(applicationContext, TeacherCreateQuestionActivity::class.java)
//        intent2.putExtra("KUIS_ID", intent.getStringExtra("ID_KUIS"))
        var intent2 = Intent(applicationContext, TeacherCreateQuestionActivity()::class.java)
        intent2.putExtra("DESKRIPSI_KUIS", intent.getStringExtra("DESKRIPSI_KUIS").toString())
        intent2.putExtra("ID_KUIS", intent.getStringExtra("ID_KUIS").toString())
        intent2.putExtra("START_TIME_KUIS", intent.getStringExtra("START_TIME_KUIS").toString())
        intent2.putExtra("END_TIME_KUIS", intent.getStringExtra("END_TIME_KUIS").toString())

        startActivity(intent2)
    }
}