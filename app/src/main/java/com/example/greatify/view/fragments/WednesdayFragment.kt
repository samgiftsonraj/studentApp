package com.example.greatify.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greatify.R
import com.example.greatify.adapter.timeTablePeriodsAdapter
import com.example.greatify.databinding.FragmentTuesdayBinding
import com.example.greatify.databinding.FragmentWednesdayBinding
import com.example.greatify.model.timeTablePeriods


class WednesdayFragment : Fragment() {

    private  var _binding : FragmentWednesdayBinding? = null
    private val binding get() = _binding!!
    private lateinit var  recyclerView: RecyclerView
    private var PeriodTimeList = ArrayList<timeTablePeriods>()
    private lateinit var timeTableAdapter: timeTablePeriodsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding  = FragmentWednesdayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvPeriodTimetable()
    }
    private fun rvPeriodTimetable(){
        recyclerView = binding.wednesdayRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =  LinearLayoutManager(context)
        PeriodTimeList = ArrayList()
        TimetablePeriods()
        timeTableAdapter = timeTablePeriodsAdapter(PeriodTimeList)
        recyclerView.adapter = timeTableAdapter
    }


    private fun TimetablePeriods(){
        PeriodTimeList.add(timeTablePeriods("Mathematics", "Hettlie Jones"))
        PeriodTimeList.add(timeTablePeriods("Mathematics", "Hettlie Jones"))
        PeriodTimeList.add(timeTablePeriods(" ", ""))
        PeriodTimeList.add(timeTablePeriods("Mathematics", "Hettlie Jones"))
        PeriodTimeList.add(timeTablePeriods("Mathematics", "Hettlie Jones"))


    }
}