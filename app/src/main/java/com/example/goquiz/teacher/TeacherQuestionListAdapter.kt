package com.example.goquiz.teacher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.goquiz.R
import com.example.goquiz.data.TempQuestion

class TeacherQuestionListAdapter(
    private val listQuestion:ArrayList<TempQuestion>
    , private val onItemClickListener: OnItemClickListener
):
    RecyclerView.Adapter<TeacherQuestionListAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.teacher_item_row_questions, viewGroup ,false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (quiz_id, question, answers) = listQuestion[position]
//        holder.tvDescriptionQuiz.text = quiz_id
        holder.tvQuestion.text = question
//        holder.tvEndTime.text =  answers[0].toString()

        holder.itemView.setOnClickListener {
//            Toast.makeText(holder.itemView.context, "Kamu memilih " + listQuestion[holder.adapterPosition].question, Toast.LENGTH_SHORT).show()

            onItemClickListener.onItemClicked(listQuestion[position])

        }
    }

    override fun getItemCount(): Int = listQuestion.size


    inner class ListViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
//        var tvDescriptionQuiz: TextView = itemView.findViewById(R.id.tv_description_quiz)
//        var tvStartTime: TextView = itemView.findViewById(R.id.tv_start_time)
//        var tvEndTime: TextView = itemView.findViewById(R.id.tv_end_time)
        var tvQuestion: TextView = itemView.findViewById(R.id.tv_question)
    }

    interface OnItemClickListener{
        fun onItemClicked(productModel:TempQuestion)
    }
}

