package com.example.goquiz.teacher

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.goquiz.data.Kuis
import com.example.goquiz.R
import com.example.goquiz.teacher.uncompleted_fragment.TeacherFragmentUncompletedQuiz
import com.example.goquiz.teacher.uncompleted_fragment.TeacherUncompletedQuizListFragment
import com.google.firebase.auth.FirebaseAuth

import org.w3c.dom.Text

//class TeacherDetailQuiz(
//    private val kuis: Kuis
//) : Fragment() {
//    // TODO: Rename and change types of parameters
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

class TeacherDetailQuiz( private val kuis: Kuis) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teacher_uncompleted_fragment_detail)

        val tit = findViewById<TextView>(R.id.title_detail)
        val detail = findViewById<TextView>(R.id.desc_detail)
        detail.text = kuis.description
        tit.text = kuis.start_time
    }
}