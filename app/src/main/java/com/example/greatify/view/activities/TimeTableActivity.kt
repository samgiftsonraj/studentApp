package com.example.greatify.view.activities

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.greatify.R
import com.example.greatify.adapter.TabFragmentPageAdapter
import com.example.greatify.adapter.timeTableAdapter
import com.example.greatify.databinding.ActivityTimeTableBinding
import com.example.greatify.model.TimetabelData
import com.example.greatify.model.recylerviewModelclass.timeTable
import com.example.greatify.network.local.SharedPreference
import com.example.greatify.network.remote.ApiResponse
import com.example.greatify.viewmodel.TimeTableViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch
import kotlin.math.log

class TimeTableActivity : AppCompatActivity() {


    private var PeriodTimeList = ArrayList<timeTable>()
    private lateinit var timeTableAdapter: timeTableAdapter
    private lateinit var  recyclerView: RecyclerView
    private lateinit var  tabLayout:TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: TabFragmentPageAdapter
    private lateinit var binding: ActivityTimeTableBinding
    private  val timeTableViewModel: TimeTableViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTimeTableBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.topappbar.setNavigationOnClickListener {
            finish()
        }
        rvPeriodTimetable()
        window.statusBarColor =  Color.parseColor("#7A60E0")

        tabLayout = binding.tabLayout
        viewPager2 = binding.viewPager2

        adapter = TabFragmentPageAdapter(supportFragmentManager,lifecycle)
        tabLayout.addTab(tabLayout.newTab().setText("Monday"))
        tabLayout.addTab(tabLayout.newTab().setText("Tuesday"))
        tabLayout.addTab(tabLayout.newTab().setText("Wednesday"))
        tabLayout.addTab(tabLayout.newTab().setText("Thursday"))
        tabLayout.addTab(tabLayout.newTab().setText("Friday"))
        tabLayout.addTab(tabLayout.newTab().setText("Saturday"))

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 1
        viewPager2.setPageTransformer { page, position ->
            page.translationX = position * (-250)
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab !=null){
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

        })
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })


        TimeTableDatas(
            SharedPreference(this@TimeTableActivity).getUserData().userToken,
            SharedPreference(this@TimeTableActivity).getDetails().student_token,
            SharedPreference(this@TimeTableActivity).getUserData().school)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun TimeTableDatas(token: String, student_token: String, school: String) {
        lifecycleScope.launch {
            timeTableViewModel.fetchTimeTable(
                token,student_token,school
            )
        }

    }


    private fun rvPeriodTimetable(){
        recyclerView = binding.rvPeriodTimeslots
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =  LinearLayoutManager(this)
        PeriodTimeList = ArrayList()
        Timetable()
        timeTableAdapter = timeTableAdapter(PeriodTimeList)
        recyclerView.adapter = timeTableAdapter
    }


    private fun Timetable(){
        PeriodTimeList.add(timeTable("Period 1", "08:00 AM - 09:00 AM"))
        PeriodTimeList.add(timeTable("Period 2", "09.01 AM - 10:00 AM"))
        PeriodTimeList.add(timeTable("Break", "10:01 AM - 10:30 AM"))
        PeriodTimeList.add(timeTable("Period 3", "10:31 AM - 11:30 AM"))
        PeriodTimeList.add(timeTable("Period 4", "11:31 AM - 12:30 PM"))
    }
}