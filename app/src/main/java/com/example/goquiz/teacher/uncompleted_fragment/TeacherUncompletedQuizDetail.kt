package com.example.goquiz.teacher.uncompleted_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.goquiz.R
import com.example.goquiz.data.Kuis

class TeacherUncompletedQuizDetail(private val kuis: Kuis) : Fragment() {
    // TODO: Rename and change types of parameters
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.teacher_uncompleted_fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = view.findViewById<TextView>(R.id.tvQuizDescription)
        val detail = view.findViewById<TextView>(R.id.tvQuizStartTime)
        detail.text = "DETAIL"
        title.text = "JUDUL"
    }
}