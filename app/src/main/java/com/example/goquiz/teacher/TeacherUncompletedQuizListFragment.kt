package com.example.goquiz.teacher

import com.example.goquiz.R
import com.example.goquiz.data.quiz.Quiz
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class TeacherUncompletedQuizListFragment : Fragment(), TeacherListQuizAdapter.OnItemClickListener {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.teacher_fragment_uncompleted_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = ArrayList<Quiz>()
//        val hero1 = Hero(name="Ahmad Dahlan", description = "iai Haji Ahmad Dahlan atau Muhammad Darwis (bahasa Arab: أحمد دحلان\u200E; 1 Agustus 1868 – 23 Februari 1923) adalah seorang Pahlawan Nasional Indonesia yang merupakan pendiri Muhammadiyah. ",photo = R.drawable.ahmaddahlan)
//        val hero2 = Hero(name = "Hatta", description = "egarawan dan ekonom Indonesia yang menjabat sebagai Wakil Presiden Indonesia pertama.", photo = R.drawable.hatta)
//        val hero3 = Hero(name = "Soekarno",description = " Presiden pertama Republik Indonesia yang menjabat pada periode 1945–1967", photo = R.drawable.soekarno)
//        val hero4 = Hero(name = "Sutomo",description = "atau lebih dikenal dengan sapaan akrab Bung Tomo adalah pahlawan nasional Indonesia yang terkenal karena peranannya dalam Pertempuran 10 November 1945.", photo = R.drawable.sutomo)
//        val hero5 = Hero(name = "Diponegoro",description = "salah seorang pahlawan nasional Republik Indonesia, yang memimpin Perang Diponegoro atau Perang Jawa selama periode tahun 1825 hingga 1830 melawan pemerintah Hindia Belanda.", photo = R.drawable.diponegoro)
//        val hero6 = Hero(name = "Sutomo",description = "atau lebih dikenal dengan sapaan akrab Bung Tomo adalah pahlawan nasional Indonesia yang terkenal karena peranannya dalam Pertempuran 10 November 1945.", photo = R.drawable.sutomo)
//        val hero7 = Hero(name = "Sutomo",description = "atau lebih dikenal dengan sapaan akrab Bung Tomo adalah pahlawan nasional Indonesia yang terkenal karena peranannya dalam Pertempuran 10 November 1945.", photo = R.drawable.sutomo)
//        val hero8 = Hero(name = "Sutomo",description = "atau lebih dikenal dengan sapaan akrab Bung Tomo adalah pahlawan nasional Indonesia yang terkenal karena peranannya dalam Pertempuran 10 November 1945.", photo = R.drawable.sutomo)
//        val hero9 = Hero(name = "Sutomo",description = "atau lebih dikenal dengan sapaan akrab Bung Tomo adalah pahlawan nasional Indonesia yang terkenal karena peranannya dalam Pertempuran 10 November 1945.", photo = R.drawable.sutomo)
//
//        list.add(hero1)
//        list.add(hero2)
//        list.add(hero3)
//        list.add(hero4)
//        list.add(hero5)
//        list.add(hero6)
//        list.add(hero7)
//        list.add(hero8)
//        list.add(hero9)


        val rvHeroes: RecyclerView = view.findViewById(R.id.rv_heroes)
        val listHeroAdapter = TeacherListQuizAdapter(list, this)
        rvHeroes.adapter = listHeroAdapter
        rvHeroes.layoutManager = LinearLayoutManager(requireContext())

//        childFragmentManager?.beginTransaction()
    }

    override fun onItemClicked(productModel: Quiz) {
        (activity as TeacherMainMenuActivity).navigateWithData(quiz = productModel)
    }
}