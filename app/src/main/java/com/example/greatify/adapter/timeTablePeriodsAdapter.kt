package com.example.greatify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greatify.databinding.AdapterSubjectsCardBinding
import com.example.greatify.databinding.AdapterTimetableCardBinding
import com.example.greatify.databinding.AdapterTimetableperiodsCardBinding
import com.example.greatify.model.recylerviewModelclass.Subjects
import com.example.greatify.model.recylerviewModelclass.timeTable
import com.example.greatify.model.timeTablePeriods


class timeTablePeriodsAdapter(
    private val timeTablePeriodsList : List<timeTablePeriods>
): RecyclerView.Adapter<timeTablePeriodsAdapter.TimeTablePeriodViewHolder>(){

    inner class TimeTablePeriodViewHolder(private val itemBinding: AdapterTimetableperiodsCardBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(itemList: timeTablePeriods){
            itemBinding.PeriodName .text = itemList.periodName
            itemBinding.TeacherName.text = itemList.TeacherName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTablePeriodViewHolder {
        return TimeTablePeriodViewHolder(AdapterTimetableperiodsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
       return timeTablePeriodsList.size
    }

    override fun onBindViewHolder(holder: TimeTablePeriodViewHolder, position: Int) {
        val period = timeTablePeriodsList[position]
        holder.bind(itemList = period)
    }
}


