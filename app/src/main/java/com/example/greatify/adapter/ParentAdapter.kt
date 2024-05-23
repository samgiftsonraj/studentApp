package com.example.greatify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greatify.databinding.AdapterAssignmentParentBinding
import com.example.greatify.model.HomeAssignmentItem


class ParentAdapter(
    private val parentsubjectsList : ArrayList<HomeAssignmentItem>
): RecyclerView.Adapter<ParentAdapter.AssignmentViewHolder>(){
    inner class AssignmentViewHolder(val itemBinding: AdapterAssignmentParentBinding  ): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(itemList: HomeAssignmentItem){
            itemBinding.subject.text = itemList.subjectName
            itemBinding.assignments.setHasFixedSize(true)
            itemBinding.assignments.layoutManager = LinearLayoutManager(itemBinding.assignments.context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
            return AssignmentViewHolder(AdapterAssignmentParentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
       return parentsubjectsList.size
    }

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        val parentItem = parentsubjectsList[position]
        holder.bind(itemList = parentItem)
        holder.itemBinding.assignments.adapter  = AssignmentAdapter(parentItem.assigments as ArrayList<HomeAssignmentItem.Assigment>)
    }


}