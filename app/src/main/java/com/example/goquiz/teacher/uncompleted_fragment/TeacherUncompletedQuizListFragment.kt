package com.example.goquiz.teacher.uncompleted_fragment

import android.content.Intent
import com.example.goquiz.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goquiz.data.TempQuiz
import com.example.goquiz.student.StudentMainMenuActivity
import com.example.goquiz.teacher.TeacherMainMenuActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class TeacherUncompletedQuizListFragment : Fragment()
    , TeacherUncompletedListQuizAdapter.OnItemClickListener
{
    // TODO: Rename and change types of parameters

    private lateinit var  auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.teacher_fragment_uncompleted_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        dbref = FirebaseDatabase.getInstance().getReference("/tmp_quizess")
//        val addValueEventListener =
//            dbref.addValueEventListener(object : ValueEventListener {
//                val listQuiz = ArrayList<TempQuiz>()
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    if (snapshot.exists()){
//                        for (quiz in snapshot.children){
//                            val teacher_uid = quiz.child("teacher_uid").value.toString()
//                            val description = quiz.child("description").value.toString()
//                            val start_time = quiz.child("start_time").value.toString()
//                            val end_time = quiz.child("end_time").value.toString()
//
//                            val quiz = TempQuiz(teacher_uid, description, start_time, end_time)
//                            listQuiz.add(quiz)
//                        }
//                    }
//                    val kuis2 = TempQuiz("hx4AafymSfduHRNw0vlCkL8uYD73", "UTS Sejarah 1", "2021/11/30 10:00:00", "2022/02/02 23:59:59")
//                    val kuis3 = TempQuiz("hx4AafymSfduHRNw0vlCkL8uYD73", "Remedial Matematika Wajib 2", "2022/02/02 08:00:00", "2022/02/02 23:59:59")
//
//
//                    listQuiz.add(kuis2)
//                    listQuiz.add(kuis3)
//                    listQuiz.add(kuis2)
//                    listQuiz.add(kuis2)
//                    listQuiz.add(kuis2)
//
//                    val rvQuiz: RecyclerView = view.findViewById(R.id.rv_quizes)
//                    val listQuizAdapter = TeacherUncompletedListQuizAdapter(listQuiz, this)
//                    rvQuiz.adapter = listQuizAdapter
//                    rvQuiz.layoutManager = LinearLayoutManager(requireContext())
//
//
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    print("Error")
//                }
//            })


        val list = ArrayList<TempQuiz>()
        val kuis1 = TempQuiz("hx4AafymSfduHRNw0vlCkL8uYD73", "Kuis Matematika Peminatan 1", "2022/02/02 08:00:00", "2022/02/03 23:59:59")
        val kuis2 = TempQuiz("hx4AafymSfduHRNw0vlCkL8uYD73", "UTS Sejarah 1", "2021/11/30 10:00:00", "2022/02/02 23:59:59")
        val kuis3 = TempQuiz("hx4AafymSfduHRNw0vlCkL8uYD73", "Remedial Matematika Wajib 2", "2022/02/02 08:00:00", "2022/02/02 23:59:59")
        val kuis4 = TempQuiz("hx4AafymSfduHRNw0vlCkL8uYD73", "Ujian Akhir", "2022/02/02 08:00:00", "2022/02/02 23:59:59")
        val kuis5 = TempQuiz("hx4AafymSfduHRNw0vlCkL8uYD73", "UAS Matematika Peminatan", "2022/02/02 08:00:00", "2022/02/02 23:59:59")
        val kuis6 = TempQuiz("hx4AafymSfduHRNw0vlCkL8uYD73", "Remedial Sejarah 1", "2022/02/02 08:00:00", "2022/02/02 23:59:59")



        list.add(kuis1)
        list.add(kuis2)
        list.add(kuis3)
        list.add(kuis4)
        list.add(kuis5)
        list.add(kuis6)


        val rvQuiz: RecyclerView = view.findViewById(R.id.rv_quizes)
        val listQuizAdapter = TeacherUncompletedListQuizAdapter(list
            , this
        )
        rvQuiz.adapter = listQuizAdapter
        rvQuiz.layoutManager = LinearLayoutManager(requireContext())


        //Real Data
//        val quiz1 = Quiz(
//            teacher_uid = "hx4AafymSfduHRNw0vlCkL8uYD73",
//            description = "Kuis 1 Matematika Wajib",
//            start_time = "12/11/2021 08:00:00",
//            end_time = "12/03/2022 23:59:59",
//            questions = ArrayList<Question>(
//                Question(
//                    question = "Berapakah jumlah satu permen ditambah dengan satu permen?",
//                    answers = ArrayList<Answer>(
//                        Answer(answer = "Satu"),
//                        Answer(answer = "Dua"),
//                        Answer(answer = "Empat"),
//                        Answer(answer = "Jakarta Selatan")
//                    ),
//                    correct_answer = Answer(answer = "Dua")
//                )
//            ),
//            responses = ArrayList<Response>(
//                Response(
//                    student_uid = "spMiLlJh2FWpXtpXAhydGYW9Qu63",
//                    response_answer = ArrayList<ResponseAnswer>(
//                        ResponseAnswer()
//                    )
//                )
//            )
//
//        )

//        val hero1 = Hero(name="Ahmad Dahlan", description = "iai Haji Ahmad Dahlan atau Muhammad Darwis (bahasa Arab: أحمد دحلان\u200E; 1 Agustus 1868 – 23 Februari 1923) adalah seorang Pahlawan Nasional Indonesia yang merupakan pendiri Muhammadiyah. ",photo = R.drawable.ahmaddahlan)
//        val hero2 = Hero(name = "Hatta", description = "egarawan dan ekonom Indonesia yang menjabat sebagai Wakil Presiden Indonesia pertama.", photo = R.drawable.hatta)
//        val hero3 = Hero(name = "Soekarno",description = " Presiden pertama Republik Indonesia yang menjabat pada periode 1945–1967", photo = R.drawable.soekarno)
//        val hero4 = Hero(name = "Sutomo",description = "atau lebih dikenal dengan sapaan akrab Bung Tomo adalah pahlawan nasional Indonesia yang terkenal karena peranannya dalam Pertempuran 10 November 1945.", photo = R.drawable.sutomo)
//        val hero5 = Hero(name = "Diponegoro",description = "salah seorang pahlawan nasional Republik Indonesia, yang memimpin Perang Diponegoro atau Perang Jawa selama periode tahun 1825 hingga 1830 melawan pemerintah Hindia Belanda.", photo = R.drawable.diponegoro)

//        list.add(hero1)
//        list.add(hero2)
//        list.add(hero3)
//        list.add(hero4)
//        list.add(hero5)


//        childFragmentManager?.beginTransaction()
    }

    override fun onItemClicked(productModel: TempQuiz) {
        (activity as TeacherMainMenuActivity).navigateWithData(kuis = productModel)
    }
}