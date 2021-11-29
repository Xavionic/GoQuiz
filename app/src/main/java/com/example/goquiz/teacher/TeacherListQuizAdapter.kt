package com.example.goquiz.teacher


import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.goquiz.R
import com.example.goquiz.data.quiz.Quiz

class TeacherListQuizAdapter(private val listHero:ArrayList<Quiz>, private val onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<TeacherListQuizAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.teacher_item_row_uncompleted_quiz, viewGroup ,false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (photo, name, description) = listHero[position]
//        holder.imgPhoto.setImageResource(photo)
//        holder.tvName.text = name
//        holder.tvDetail.text = description
//
//        holder.imgPhoto.setOnClickListener{
//            onItemClickListener.onItemClicked(listHero[position])
////            Toast.makeText(holder.itemView.context, listHero[position].name, Toast.LENGTH_SHORT).show()
//        }
    }

    override fun getItemCount(): Int = listHero.size


    inner class ListViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
    }

    interface OnItemClickListener{
        fun onItemClicked(productModel:Quiz)
    }


}

