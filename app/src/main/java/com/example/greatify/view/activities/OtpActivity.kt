package com.example.greatify.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.greatify.R
import com.example.greatify.databinding.ActivityOtpBinding
import com.example.greatify.model.SendOTPData
import com.example.greatify.model.VerifyOTPData
import com.example.greatify.network.local.SharedPreference
import com.example.greatify.network.remote.ApiResponse
import com.example.greatify.viewmodel.LoginViewModel
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.NumberFormat

class OtpActivity : AppCompatActivity() {
    private lateinit var binding:ActivityOtpBinding
    private  val authenticationViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topappbar.setNavigationOnClickListener {
            finish()
        }
        binding.submitOtp.setOnClickListener {
            otpverifyTask()
        }

        object : CountDownTimer(30000, 1000) {
            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                val f: NumberFormat = DecimalFormat("00")
                val min = millisUntilFinished / 60000 % 60
                val sec = millisUntilFinished / 1000 % 60

                ("Resend OTP in : " + f.format(min) + ":" + f.format(sec)).also { binding.timer.text = it }
            }


            override fun onFinish() {
                "Resend".also { binding.timer.text = it }
                binding.timer.setOnClickListener {
                    start()
                    binding.editText1.setText("")
                    binding.editText2.setText("")
                    binding.editText3.setText("")
                    binding.editText4.setText("")
                    binding.editText5.setText("")
                    binding.editText6.setText("")
                }
            }
        }.start()

        binding.editText1.addTextChangedListener(textWatcher)
        binding.editText2.addTextChangedListener(textWatcher)
        binding.editText3.addTextChangedListener(textWatcher)
        binding.editText4.addTextChangedListener(textWatcher)
        binding.editText5.addTextChangedListener(textWatcher)
        binding.editText6.addTextChangedListener(textWatcher)
        authenticationViewModel.sendOtpData.observe(this, sendOtpObserve)
        authenticationViewModel.verifyOtpData.observe(this, verifyOtpObserve)
        authenticationViewModel.validationError.observe(this, validationObserve)
        }

    private val sendOtpObserve = Observer<ApiResponse<SendOTPData>> { stats->
        when (stats) {
            is ApiResponse.Success -> {
                Toast.makeText(this, "OTP Succesfully Sent to the  Registered Mobile Number", Toast.LENGTH_SHORT).show()
            }
            is ApiResponse.Error -> {
                Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show()
            }
            is ApiResponse.Loading -> {}
        }
    }

    private val verifyOtpObserve = Observer<ApiResponse<VerifyOTPData>> { status->
        when(status) {
            is ApiResponse.Success -> {
                Toast.makeText(this, "OTP verified", Toast.LENGTH_SHORT).show()
                val name = status.data?.data?.name
                val token = status.data?.data?.token
                val school = intent.getStringExtra("school")
                name?.let { it1 -> token?.let { it2 ->
                    school?.let { it3 ->
                            SharedPreference(this).setUserData(it1, it2, it3)
                    }
                } }
                startActivity(Intent(this, HomeActivity::class.java))

//                val intent = Intent(this, HomeActivity::class.java)
//                startActivity(intent)

            }
            is ApiResponse.Error -> {
                Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show()
            }
            is ApiResponse.Loading -> {}
        }
    }

    private fun otpverifyTask(){
        lifecycleScope.launch{
            intent.getStringExtra("mobileNumber")?.let {
                authenticationViewModel.fetchVerifyOTP(
                    it,
                    binding.editText1.text.toString(),
                    binding.editText2.text.toString(),
                    binding.editText3.text.toString(),
                    binding.editText4.text.toString(),
                    binding.editText5.text.toString(),
                    binding.editText6.text.toString(),
                    intent.getStringExtra("countryCode")!!,
                    intent.getStringExtra("SchoolToken")!!,
                    intent.getStringExtra("school")!!,
                    intent.getStringExtra("studentId")!!
                )
            }

        }
    }

    private val validationObserve = Observer<String> {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }




   private var textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(count == 0 && before== 1 && s.isNullOrEmpty()){
                if(binding.editText6.text.isEmpty()){
                    binding.editText5.requestFocus()
                }
                if(binding.editText5.text.isEmpty()){
                    binding.editText4.requestFocus()
                }
                if(binding.editText4.text.isEmpty()){
                    binding.editText3.requestFocus()
                }
                if(binding.editText3.text.isEmpty()){
                    binding.editText2.requestFocus()
                }
                if(binding.editText2.text.isEmpty()){
                    binding.editText1.requestFocus()
                }

            }
            else{
                if(binding.editText1.text.length==1){
                    binding.editText2.requestFocus()
                }
                if(binding.editText2.text.length==1){
                    binding.editText3.requestFocus()
                }
                if(binding.editText3.text.length==1){
                    binding.editText4.requestFocus()
                }
                if(binding.editText4.text.length==1){
                    binding.editText5.requestFocus()
                }
                if(binding.editText5.text.length==1){
                    binding.editText6.requestFocus()
                }

            }
        }

        override fun afterTextChanged(s: Editable?) {

        }





    }


}


