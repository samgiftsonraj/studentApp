package com.example.greatify.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greatify.R
import com.example.greatify.adapter.SubjectAdapter
import com.example.greatify.adapter.ViewPagerAdapter
import com.example.greatify.adapter.timeTableAdapter
import com.example.greatify.adapter.timeTablePeriodsAdapter
import com.example.greatify.databinding.FragmentHomeBinding
import com.example.greatify.databinding.FragmentMondayBinding
import com.example.greatify.model.recylerviewModelclass.timeTable
import com.example.greatify.model.timeTablePeriods
import com.google.android.material.tabs.TabLayoutMediator


class MondayFragment : Fragment() {
    private  var _binding : FragmentMondayBinding? = null
    private val binding get() = _binding!!
    private lateinit var  recyclerView: RecyclerView
    private var PeriodTimeList = ArrayList<timeTablePeriods>()
    private lateinit var timeTableAdapter: timeTablePeriodsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding  = FragmentMondayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvPeriodTimetable()
    }

    private fun rvPeriodTimetable(){
        recyclerView = binding.mondayRecyclerView
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