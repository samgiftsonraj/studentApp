package com.example.greatify.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greatify.R
import com.example.greatify.adapter.ParentAdapter
import com.example.greatify.adapter.ViewPagerAdapter
import com.example.greatify.adapter.BookAdapter
import com.example.greatify.adapter.CoursecardAdapter
import com.example.greatify.adapter.GridAdapter
import com.example.greatify.adapter.PeriodAdapter
import com.example.greatify.adapter.SubjectAdapter
import com.example.greatify.databinding.FragmentHomeBinding
import com.example.greatify.model.LogoutData
import com.example.greatify.model.HomeAssignmentItem
import com.example.greatify.model.recylerviewModelclass.Book
import com.example.greatify.model.recylerviewModelclass.Courses
import com.example.greatify.model.recylerviewModelclass.Gridclass
import com.example.greatify.model.recylerviewModelclass.Period
import com.example.greatify.model.recylerviewModelclass.Subjects
import com.example.greatify.network.local.SharedPreference
import com.example.greatify.network.remote.ApiResponse
import com.example.greatify.view.activities.MainActivity
import com.example.greatify.viewmodel.HomeViewModel
import com.example.greatify.viewmodel.LoginViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private  var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var  recyclerView: RecyclerView
    private  var periodList: ArrayList<Period> = ArrayList()
    private lateinit var periodAdapter: PeriodAdapter
    private  var subjectList: ArrayList<Subjects> = ArrayList()
    private lateinit var subjectAdapter: SubjectAdapter
    private  var courseList: ArrayList<Courses> = ArrayList()
    private lateinit var courseAdapter: CoursecardAdapter
    private  var bookList: ArrayList<Book> = ArrayList()
    private lateinit var bookAdapter: BookAdapter
    private val parentList by lazy { ArrayList<HomeAssignmentItem>() }
    private lateinit var parentAdapter: ParentAdapter
    private  var gridList: ArrayList<Gridclass> = ArrayList()
    private lateinit var gridAdapter: GridAdapter
    private  val authenticationViewModel: LoginViewModel by viewModels()
    private  val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
            _binding  = FragmentHomeBinding.inflate(inflater, container, false)
            return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          allData()
        val message = listOf(
            "Tomorrow will be a holiday for classes LKG to \n" +
                    "5th std because of heavy rains. Students are \n" +
                    "requested to stay indoors and stay safe.",
            "Tomorrow will be a holiday for classes LKG to \n" +
                    "5th std because of heavy rains. Students are \n" +
                    "requested to stay indoors and stay safe."
        )
        binding.viewpager.adapter = ViewPagerAdapter(message)
        TabLayoutMediator(binding.intoTabLayout, binding.viewpager) { tab, position -> }.attach()
        loggingOut(
            activity?.let { SharedPreference(it).getUserData().userToken },
            activity?.let { SharedPreference(it).getUserData().school }
        )
        homeViewModel.homeassignmentData.observe(viewLifecycleOwner){
            it.data?.let { it1 -> parentList.addAll(it1) }
            parentAdapter = ParentAdapter(parentList)
            binding.parentassignment.adapter = parentAdapter
            binding.parentassignment.layoutManager = LinearLayoutManager(context)
        }
        homeViewModel.studentData.observe(this@HomeFragment.viewLifecycleOwner){it1->
            activity.let { SharedPreference(it).setDetails(it1.data[0].token, it1.data[0].sectionToken) }
        }
        authenticationViewModel.validationError.observe(this@HomeFragment.viewLifecycleOwner, validationObserve)

    }
    private fun allData(){
        rvPeriods()
        rvSubjects()
        rvCoursecard()
        rvBooks()
        rvcategoriesGrid()
        assignmentTask()
        studentTask()
    }
    private fun assignmentTask(){
        lifecycleScope.launch {
            homeViewModel.homeassignment(
                activity.let { SharedPreference(it).getUserData().userToken },
                activity.let { SharedPreference(it).getDetails().student_token },
                activity.let { SharedPreference(it).getDetails().section_token },
                activity.let { SharedPreference(it).getUserData().school }
            )
        }
    }
    private fun studentTask() {
        lifecycleScope.launch {
            homeViewModel.fetchStudentDetails(
                activity.let { SharedPreference(it).getUserData().userToken },
                activity.let { SharedPreference(it).getUserData().school }
            )
        }
    }
    private fun loggingOut(token: String?, school: String?) {
        binding.logout.setOnClickListener {
            lifecycleScope.launch {
                token?.let { it1 -> school?.let { it2 ->
                    authenticationViewModel.logout(it1, it2)
                } }
            }
        }
        authenticationViewModel.logoutData.observe(
            this@HomeFragment.viewLifecycleOwner, logoutObserve
        )
    }
    private val logoutObserve = Observer<ApiResponse<LogoutData>> {
        when (it) {
            is ApiResponse.Success -> {
                activity?.let { it1 -> SharedPreference(it1).setUserData("", "","") }
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                Log.d("logout", "logged out successfully")
                Toast.makeText(activity, "Logged Out Successfully", Toast.LENGTH_SHORT).show()
            }
            is ApiResponse.Error -> {
                Toast.makeText(activity, "Error Occured", Toast.LENGTH_SHORT).show()
            }
            is ApiResponse.Loading -> {}
        }
    }
    private fun rvPeriods(){
        recyclerView = binding.cards
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        periodList = ArrayList()
        periods()
        periodAdapter = PeriodAdapter(periodList)
        recyclerView.adapter = periodAdapter
    }
    private fun rvSubjects(){
        recyclerView = binding.subjects
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =  LinearLayoutManager(activity)
        subjectList = ArrayList()
        subjects()
        subjectAdapter = SubjectAdapter(subjectList)
        recyclerView.adapter = subjectAdapter
    }
    private fun rvCoursecard(){
        recyclerView = binding.courseCard
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        courseList = ArrayList()
        courses()
        courseAdapter = CoursecardAdapter(courseList)
        recyclerView.adapter = courseAdapter
    }
    private fun rvBooks(){
        recyclerView = binding.books
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        bookList = ArrayList()
        books()
        bookAdapter = BookAdapter(bookList)
        recyclerView.adapter = bookAdapter
    }
    private fun rvcategoriesGrid(){
        recyclerView = binding.rvgrid
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        gridList = ArrayList()
        categoriesGrid()
        gridAdapter = GridAdapter(gridList)
        recyclerView.adapter = gridAdapter
    }
    //below are the static data given inside recyclerview
    private fun periods(){
        periodList.add(Period("Period 1", "8AM - 9AM", "Mathematics"))
        periodList.add(Period("Period 2", "9AM - 10AM", "Science"))
        periodList.add(Period("Period 3", "10AM - 11AM", "Social Science"))
        periodList.add(Period("Period 4", "11AM - 12PM", "English"))
        periodList.add(Period("Period 5", "12PM - 1PM", "Tamil"))
        periodList.add(Period("Period 6", "1PM- 2PM", "Japanese"))
    }
    private fun subjects(){
        subjectList.add(Subjects("Physics"))
        subjectList.add(Subjects("Chemistry"))
        subjectList.add(Subjects("Mathematics"))
        subjectList.add(Subjects("English"))
    }
    private fun courses(){
        courseList.add(Courses(R.drawable.course_imageone, "Organic Chemistry"))
        courseList.add(Courses(R.drawable.course, "Basics of Verbal"))
    }
    private fun books(){
      bookList.add(Book(R.drawable.book2, "Newton's Laws"))
      bookList.add(Book(R.drawable.book4, "Accounting Basics"))
      bookList.add(Book(R.drawable.book5, "Mathematics"))
    }
    private fun categoriesGrid(){
        gridList.add(Gridclass(R.drawable.class__assignments_1,R.drawable.class__assignments_2,"Class \n Assignment"))
        gridList.add(Gridclass(R.drawable.my_attendance_1,R.drawable.my_attendance_2,"My \n" + "Attendance"))
        gridList.add(Gridclass(R.drawable.daily__homeworks_1,R.drawable.daily__homeworks_2,"Daily \n" +"Homeworks"))
        gridList.add(Gridclass(R.drawable.class__assignments_1,R.drawable.class__assignments_2,"Class \n" + "Assignments"))
        gridList.add(Gridclass(R.drawable.books_1,R.drawable.books_2,"Study \n" + "Books"))
        gridList.add(Gridclass(R.drawable.educational__videos_1,R.drawable.educational__videos_2,"Educational \n" +"Videos"))
        gridList.add(Gridclass(R.drawable.my_test_1,R.drawable.my_test_2,"My \n" + "Tests"))
        gridList.add(Gridclass(R.drawable.my__examinations_1,R.drawable.my__examinations_2,"My \n" + "Examinations"))
    }
    private val validationObserve = Observer<String> {
        Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
    }

}
