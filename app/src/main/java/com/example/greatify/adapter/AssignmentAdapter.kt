package com.example.greatify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greatify.databinding.AdapterAssignmentChildCardBinding
import com.example.greatify.model.HomeAssignmentItem


class AssignmentAdapter(
   private val assignmentList : List<HomeAssignmentItem.Assigment>
): RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder>(){

    inner class AssignmentViewHolder(private val itemBinding: AdapterAssignmentChildCardBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(itemList: HomeAssignmentItem.Assigment){
            itemBinding.subjectTitle.text= itemList.title
            itemBinding.unitName.text = itemList.description
            itemBinding.Date .text = itemList.dueDate
            itemBinding.remainingday.text = itemList.dayCount
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        return AssignmentViewHolder(AdapterAssignmentChildCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun getItemCount(): Int {
       return assignmentList.size
    }

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        holder.bind(itemList = assignmentList[position])

    }

}