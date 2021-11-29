package com.example.goquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goquiz.data.Quiz

/*
class ListUncompletedQuiz : Fragment(), ListQuizAdapter.OnItemClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.student_rv_uncompleted_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listQuiz = ArrayList<Quiz>()
        var quizTitle: Array<String> = arrayOf("UTS 1", "UTS 2","UTS 3","UTS 4", "UTS 5")
        var teacherName: Array<String> = arrayOf("Wiwid", "AAN", "Heru", "Komang", "Denny")
        var quizDuration: Array<String> = arrayOf("60 Min", "90 Min","100 Min", "60 Min", "90")
        var quizDateTime: Array<String> = arrayOf("2021-12-15 19:00", "2021-11-29 10:00","2021-11-25 10:00","2021-12-13 10:00","2021-11-19 10:00")
        var status: Array<String> = arrayOf("Opened", "Closed","Closed","Opened","Closed")

        listQuiz.add(Quiz(quizTitle[0], teacherName[0], quizDuration[0], quizDateTime[0], status[0]))
        listQuiz.add(Quiz(quizTitle[1], teacherName[1], quizDuration[1], quizDateTime[1], status[1]))
        listQuiz.add(Quiz(quizTitle[2], teacherName[2], quizDuration[2], quizDateTime[2], status[2]))
        listQuiz.add(Quiz(quizTitle[3], teacherName[3], quizDuration[3], quizDateTime[3], status[3]))
        listQuiz.add(Quiz(quizTitle[4], teacherName[4], quizDuration[4], quizDateTime[4], status[4]))

        val rv_uncompleted_quiz: RecyclerView = view.findViewById(R.id.rv_uncompleted_quiz)
        val listQuizAdapter = ListQuizAdapter(listQuiz, this)
        rv_uncompleted_quiz.adapter = listQuizAdapter
        rv_uncompleted_quiz.layoutManager = LinearLayoutManager(requireContext())

    //        childFragmentManager?.beginTransaction()
    }

    override fun onItemClicked(productModel: Quiz) {
        if (productModel.status == "Opened") {
            Toast.makeText(activity as StudentMainMenuActivity, "Buka Activity Soal Quiz",
                Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(activity as StudentMainMenuActivity, "Buka Activity Soal Quiz",
                Toast.LENGTH_LONG).show();
        }
    }
}
*/