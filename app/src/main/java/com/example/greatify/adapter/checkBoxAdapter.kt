package com.example.greatify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greatify.databinding.AdapterCheckboxCardBinding
import com.example.greatify.databinding.AdapterSubjectsCardBinding
import com.example.greatify.model.recylerviewModelclass.Subjects
import com.example.greatify.model.recylerviewModelclass.checkBox


class checkBoxAdapter(
    private val checkboxList : List<checkBox>
): RecyclerView.Adapter<checkBoxAdapter.CheckBoxViewHolder>(){

    inner class CheckBoxViewHolder(private val itemBinding: AdapterCheckboxCardBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(itemList: checkBox){
            itemBinding.checkboxes.text = itemList.diseaseName
        }
        fun bind2(itemList: checkBox){
            itemBinding.checkboxes.text = itemList.diseaseName
        }
        fun bind3(itemList: checkBox){
            itemBinding.checkboxes.text = itemList.diseaseName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckBoxViewHolder {
        return CheckBoxViewHolder(AdapterCheckboxCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
       return checkboxList.size
    }

    override fun onBindViewHolder(holder: CheckBoxViewHolder, position: Int) {
        val disease = checkboxList[position]
        holder.bind(itemList = disease)
        holder.bind2(itemList = disease)
        holder.bind3(itemList = disease)
    }
}


