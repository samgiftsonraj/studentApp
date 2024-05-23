package com.example.greatify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greatify.databinding.AdapterCourseCardBinding
import com.example.greatify.model.recylerviewModelclass.Courses

class CoursecardAdapter(
    private val courseList : List<Courses>
): RecyclerView.Adapter<CoursecardAdapter.CourseViewHolder>(){

    inner class CourseViewHolder(private val itemBinding: AdapterCourseCardBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(itemList: Courses){
            itemBinding.CourseImg.setImageResource(itemList.courseImage)
            itemBinding.courseName.text = itemList.courseName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(AdapterCourseCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
       return courseList.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courseList[position]
        holder.bind(itemList = course)
    }


}