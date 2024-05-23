package com.example.greatify.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.greatify.R
import com.example.greatify.databinding.ActivityMainBinding
import com.example.greatify.model.SchoolListData
import com.example.greatify.model.SendOTPData
import com.example.greatify.network.remote.ApiResponse
import com.example.greatify.viewmodel.LoginViewModel
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  val authenticationViewModel: LoginViewModel by viewModels()
    private var schoolNames = ArrayList<SchoolListData.Data>()
    private var schoolInfo = ArrayList<String>()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor =  Color.parseColor("#00373A")
        supportActionBar?.hide()
            binding.sendOtp.setOnClickListener {
                lifecycleScope.launch {
                    authenticationViewModel.fetchOTP(
                        schoolNames[binding.spSchool.selectedItemPosition].schoolToken,
                        binding.ccp.selectedCountryCode,
                        binding.mobileNumberInput.text.toString()       ,
                        schoolNames[binding.spSchool.selectedItemPosition].databaseName,
                        binding.studentIdInput.text.toString()
                    )
                }
            }

            //dropdown menu
            val profession = listOf("Student", "Teacher", "Principal","Vice-Principal")
            val professionarrayAdapter = ArrayAdapter(this, R.layout.textview_blue,profession)
            professionarrayAdapter.setDropDownViewResource(R.layout.dropmenu)
              binding.spProfession.adapter = professionarrayAdapter
              getSchoolList()

            authenticationViewModel.sendOtpData.observe(this, sendOtpObserve)
             authenticationViewModel.validationError.observe(this, validationObserve)

    }

            private fun getSchoolList(){
                // Fetch school list from the ViewModel
                lifecycleScope.launch {
                    authenticationViewModel.fetchSchoolData()
                }
                // Observe the school list LiveData
                authenticationViewModel.dropdownList.observe(this) {
                    it.let { data ->
                        data.data.let { dataList ->
                            schoolNames.addAll(dataList)
                            for(i in 0 until  schoolNames.size){
                                schoolInfo.add(schoolNames[i].name)
                            }
                            val schoolArrayAdapter = ArrayAdapter(this, R.layout.textview_blue, schoolInfo)
                            schoolArrayAdapter.setDropDownViewResource(R.layout.dropmenu)
                            binding.spSchool.adapter = schoolArrayAdapter
                        }
                    }
                }

            }

    private val sendOtpObserve = Observer<ApiResponse<SendOTPData>> {stats->
        when (stats) {
            is ApiResponse.Success -> {
                Toast.makeText(this, "OTP sent", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, OtpActivity::class.java)
                intent.putExtra("SchoolToken", schoolNames[binding.spSchool.selectedItemPosition].schoolToken)
                intent.putExtra("countryCode", binding.ccp.selectedCountryCodeWithPlus)
                intent.putExtra("mobileNumber", binding.mobileNumberInput.text.toString())
                intent.putExtra("school",schoolNames[binding.spSchool.selectedItemPosition].databaseName)
                intent.putExtra("studentId", binding.studentIdInput.text.toString())
                startActivity(intent)

            }
            is ApiResponse.Error -> {
                Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show()
            }
            is ApiResponse.Loading -> {}
        }
    }

        private val validationObserve = Observer<String> {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

    }
