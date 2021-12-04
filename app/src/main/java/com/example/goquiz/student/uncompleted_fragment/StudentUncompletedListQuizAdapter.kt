package com.example.goquiz.student.uncompleted_fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.goquiz.R
import com.example.goquiz.data.TempQuiz
import com.example.goquiz.teacher.TeacherDetailQuiz2
import java.security.AccessController.getContext


class StudentUncompletedListQuizAdapter(private val quizList : ArrayList<TempQuiz>, private val teacherNameList : ArrayList<String>): RecyclerView.Adapter<StudentUncompletedListQuizAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.student_item_row_uncompleted_quiz, viewGroup ,false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (teacher_uid, description, start_time, end_time) = quizList[position]
        holder.tvDescriptionQuiz.text = description
        holder.tvStartTime.text = "Start $start_time"
        holder.tvEndTime.text =   "End   $end_time"
        holder.tvTeacherName.text = "Quiz by ${teacherNameList[position]}"

        holder.itemView.setOnClickListener {
            //@TODO how to start activity inside setOnClickListener
            var intent = Intent(holder.itemView.context, TeacherDetailQuiz2::class.java)
            intent.putExtra("QUIZ_DESCRIPTION", description)
            intent.putExtra("QUIZ_START_TIME", start_time)
            intent.putExtra("QUIZ_END_TIME", end_time)
            Toast.makeText(holder.itemView.context, "Kamu ${teacherNameList[position]} memilih " + quizList[holder.adapterPosition].description, Toast.LENGTH_SHORT).show()

        }
    }

    override fun getItemCount(): Int = quizList.size


    inner class ListViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        var tvDescriptionQuiz: TextView = itemView.findViewById(R.id.tv_description_quiz)
        var tvTeacherName: TextView = itemView.findViewById(R.id.tv_teacher_name)
        var tvStartTime: TextView = itemView.findViewById(R.id.tv_start_time)
        var tvEndTime: TextView = itemView.findViewById(R.id.tv_end_time)
    }
}