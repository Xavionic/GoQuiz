package com.example.goquiz.teacher.uncompleted_fragment

import com.example.goquiz.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goquiz.data.Kuis
import com.example.goquiz.teacher.TeacherMainMenuActivity



class TeacherQuestionListFragment : Fragment(), TeacherUncompletedListQuizAdapter.OnItemClickListener {
    // TODO: Rename and change types of parameters


//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.teacher_fragment_uncompleted_quiz, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val list = ArrayList<Kuis>()
//        val kuis1 = Kuis("hx4AafymSfduHRNw0vlCkL8uYD73", "Kuis Matematika Peminatan 1", "2022/02/02 08:00:00", "2022/02/03 23:59:59")
//        val kuis2 = Kuis("hx4AafymSfduHRNw0vlCkL8uYD73", "UTS Sejarah 1", "2021/11/30 10:00:00", "2022/02/02 23:59:59")
//        val kuis3 = Kuis("hx4AafymSfduHRNw0vlCkL8uYD73", "Remedial Matematika Wajib 2", "2022/02/02 08:00:00", "2022/02/02 23:59:59")
//
//        list.add(kuis1)
//        list.add(kuis2)
//        list.add(kuis3)
//        list.add(kuis2)
//        list.add(kuis2)
//        list.add(kuis2)
//
//
//        val rvQuiz: RecyclerView = view.findViewById(R.id.rv_quizes)
//        val listQuizAdapter = TeacherUncompletedListQuizAdapter(list, this)
//        rvQuiz.adapter = listQuizAdapter
//        rvQuiz.layoutManager = LinearLayoutManager(requireContext())
//    }

    override fun onItemClicked(productModel: Kuis) {
        (activity as TeacherMainMenuActivity).navigateWithData(kuis = productModel)
    }
}