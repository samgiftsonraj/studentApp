package com.example.greatify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greatify.databinding.AdapterSubjectsCardBinding
import com.example.greatify.model.recylerviewModelclass.Subjects


class SubjectAdapter(
    private val subjectList : List<Subjects>
): RecyclerView.Adapter<SubjectAdapter.PeriodViewHolder>(){

    inner class PeriodViewHolder(private val itemBinding: AdapterSubjectsCardBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(itemList: Subjects){
            itemBinding.subName.text = itemList.subjectName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeriodViewHolder {
        return PeriodViewHolder(AdapterSubjectsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
       return subjectList.size
    }

    override fun onBindViewHolder(holder: PeriodViewHolder, position: Int) {
        val sub = subjectList[position]
        holder.bind(itemList = sub)
    }
}


