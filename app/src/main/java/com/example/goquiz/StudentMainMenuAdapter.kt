package com.example.goquiz

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.goquiz.data.Quiz
import java.time.format.DateTimeFormatter

class StudentMainMenuAdapter(private val listQuiz: ArrayList<Quiz>):
    RecyclerView.Adapter<StudentMainMenuAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvQuizTitle: TextView = itemView.findViewById(R.id.quizTitle)
        var tvTeacherName: TextView = itemView.findViewById(R.id.nameTeacher)
        var tvQuizDuration: TextView = itemView.findViewById(R.id.quizDuration)
        var tvQuizDateTime: TextView = itemView.findViewById(R.id.quizDateTime)
        var tvStatus: TextView = itemView.findViewById(R.id.status)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.rv_uncompleted_quiz, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listQuiz.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (quizTitle, teacherName, quizDuration, quizDateTime, status) = listQuiz
        holder.tvQuizTitle.text = quizTitle.toString()
        holder.tvTeacherName.text = teacherName.toString()
        holder.tvQuizDuration.text = quizDuration.toString()
        holder.tvQuizDateTime.text = quizDateTime.toString()
        holder.tvStatus.text = status.toString()

        if (holder.tvStatus.text == "Close") {
            holder.itemView.setOnClickListener{
                Toast.makeText(holder.itemView.context, "Quiz will be opened at " + listQuiz[holder.adapterPosition].quizDateTime,
                    Toast.LENGTH_SHORT).show();
            }
        } else {
            holder.itemView.setOnClickListener{
                Toast.makeText(holder.itemView.context, "Buka Activity Soal Quiz",
                    Toast.LENGTH_LONG).show();
            }
        }
    }
}

