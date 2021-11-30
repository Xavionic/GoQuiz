package com.example.goquiz.teacher.uncompleted_fragment


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.goquiz.R
import com.example.goquiz.data.quiz.Quiz

class TeacherUncompletedListQuizAdapter(
    private val listQuiz:ArrayList<Quiz>,
    private val onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<TeacherUncompletedListQuizAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.teacher_item_row_uncompleted_quiz, viewGroup ,false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (photo, name, description) = listQuiz[position]

//        holder.imgPhoto.setImageResource(photo)
//        holder.tvName.text = name
//        holder.tvDetail.text = description
//
//        holder.imgPhoto.setOnClickListener{
//            onItemClickListener.onItemClicked(listHero[position])
////            Toast.makeText(holder.itemView.context, listHero[position].name, Toast.LENGTH_SHORT).show()
//        }
    }

    override fun getItemCount(): Int = listQuiz.size


    inner class ListViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        var tvDescriptionQuiz: TextView = itemView.findViewById(R.id.tv_description_quiz)
        var tvStartTime: TextView = itemView.findViewById(R.id.tv_start_time)
        var tvEndTime: TextView = itemView.findViewById(R.id.tv_end_time)
    }

    interface OnItemClickListener{
        fun onItemClicked(productModel:Quiz)
    }


}

