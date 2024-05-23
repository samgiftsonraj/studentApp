package com.example.greatify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greatify.databinding.AdapterSubjectsCardBinding
import com.example.greatify.databinding.AdapterTimetableCardBinding
import com.example.greatify.model.recylerviewModelclass.Subjects
import com.example.greatify.model.recylerviewModelclass.timeTable


class timeTableAdapter(
    private val timeTablePeriods : List<timeTable>
): RecyclerView.Adapter<timeTableAdapter.TimeTableViewHolder>(){

    inner class TimeTableViewHolder(private val itemBinding: AdapterTimetableCardBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(itemList: timeTable){
            itemBinding.PeriodNum.text = itemList.periodNum
            itemBinding.timings.text = itemList.timings
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTableViewHolder {
        return TimeTableViewHolder(AdapterTimetableCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
       return timeTablePeriods.size
    }

    override fun onBindViewHolder(holder: TimeTableViewHolder, position: Int) {
        val period = timeTablePeriods[position]
        holder.bind(itemList = period)
    }
}


