package com.example.greatify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greatify.databinding.AdapterPeriodBinding
import com.example.greatify.model.recylerviewModelclass.Period

class PeriodAdapter(
    private val periodList : List<Period>
): RecyclerView.Adapter<PeriodAdapter.PeriodViewHolder>(){

    inner class PeriodViewHolder(private val itemBinding: AdapterPeriodBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(itemList: Period){
            itemBinding.periodNum.text = itemList.period
            itemBinding.subjectName.text = itemList.subjectName
            itemBinding.timings.text = itemList.timing
        }
    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeriodViewHolder {
            return PeriodViewHolder(AdapterPeriodBinding.inflate(LayoutInflater.from(parent.context), parent, false))
         }

    override fun getItemCount(): Int {
       return periodList.size
    }

    override fun onBindViewHolder(holder: PeriodViewHolder, position: Int) {
        val period = periodList[position]
        holder.bind(itemList = period)
    }

}