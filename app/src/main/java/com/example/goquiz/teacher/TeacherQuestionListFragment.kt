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


class TeacherQuestionListFragment : Fragment()
    , TeacherQuestionListAdapter.OnItemClickListener
{
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.teacher_fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ansList = ArrayList<TempAnswer>()
        val ans1 = TempAnswer("Dua Puluh")
        val ans2 = TempAnswer("Jakarta Selatan")
        val ans3 = TempAnswer("381,56432111", true)
        val ans4 = TempAnswer("Ya")
        ansList.add(ans1)
        ansList.add(ans2)
        ansList.add(ans3)
        ansList.add(ans4)

        val listQuestion = ArrayList<TempQuestion>()
        val question1 = TempQuestion("hx4AafymSfduHRNw0vlCkL8uYD73", "Berapakah satu ditambah satu dengan asumsi satu bukan bilangan yang dapat ditambah?", ansList)
        val question2 = TempQuestion("hx4AafymSfduHRNw0vlCkL8uYD73", "Lorem ipsum dolor sit amet", ansList)
        val question3 = TempQuestion("hx4AafymSfduHRNw0vlCkL8uYD73", "Pertanyaan pertanyaan pertanyaan pertanyaan pertanyaan pertanyaan?", ansList)

        listQuestion.add(question1)
        listQuestion.add(question2)
        listQuestion.add(question3)
        listQuestion.add(question2)
        listQuestion.add(question2)
        listQuestion.add(question2)


        val rvQuestion: RecyclerView = view.findViewById(R.id.rv_questions)
        val listQuestionAdapter = TeacherQuestionListAdapter(listQuestion, this)
        rvQuestion.adapter = listQuestionAdapter
        rvQuestion.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onItemClicked(productModel: TempQuestion) {
        (activity as TeacherDetailQuiz).navigateWithData(question = productModel)
    }
}