package com.example.greatify.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greatify.R
import com.example.greatify.adapter.MedDetailsAdapter
import com.example.greatify.adapter.SubjectAdapter
import com.example.greatify.adapter.checkBoxAdapter
import com.example.greatify.databinding.FragmentMedicalReportBinding
import com.example.greatify.databinding.FragmentProfileBinding
import com.example.greatify.model.UserData
import com.example.greatify.model.recylerviewModelclass.checkBox

class ProfileFragment : Fragment() {
    private  var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var  recyclerView: RecyclerView
    private  var checkBoxList: ArrayList<checkBox> = ArrayList()
    private lateinit var checkBoxAdapter: checkBoxAdapter
    private var userList : ArrayList<UserData> = ArrayList()
    private lateinit var userAdapter : MedDetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding  = FragmentProfileBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        healthConditions()
        rvfoodallegeriesGrid()
        rvVaccineallegeriesGrid()


        binding.submitBtn.setOnClickListener {
            startActivity(Intent(context, MedicalReportFragment::class.java))
        }

        binding.rvMedicalDetails.setHasFixedSize(true)
        userList = ArrayList()
        userList.add(UserData("",""))
        binding.rvMedicalDetails.layoutManager = LinearLayoutManager(context)
        binding.rvMedicalDetails.adapter = MedDetailsAdapter(userList)
        binding.Remove.visibility = View.INVISIBLE


        binding.addMedication.setOnClickListener {
            val newItem = UserData("","")
            binding.Remove.visibility = View.VISIBLE
            if (userList.size < 5) {
                userList.add(newItem)
                binding.rvMedicalDetails.adapter?.notifyItemInserted(userList.size+1)
                binding.rvMedicalDetails.scrollToPosition(userList.size-1)
            }
        }
       binding.Remove.setOnClickListener {
           if(userList.size==1){
               binding.Remove.visibility = View.INVISIBLE
           }
           if (userList.size !=1) {
               userList.removeLast()
               binding.rvMedicalDetails.adapter?.notifyItemRemoved(userList.size - 1)
               binding.rvMedicalDetails.scrollToPosition(userList.size-1)
           }
       }


    }


    private fun healthConditions(){
        binding.rvCheckbox.setHasFixedSize(true)
        checkBoxList = ArrayList()
        checkBoxList.add(checkBox("Asthma"))
        checkBoxList.add(checkBox("Asthma"))
        checkBoxList.add(checkBox("Asthma"))
        checkBoxList.add(checkBox("Asthma"))
        checkBoxList.add(checkBox("Asthma"))
        checkBoxList.add(checkBox("Asthma"))
        checkBoxList.add(checkBox("Asthma"))
        checkBoxList.add(checkBox("Asthma"))
        binding.rvCheckbox.adapter = checkBoxAdapter(checkBoxList)
    }
    private fun rvfoodallegeriesGrid(){
        binding.rvCheckbox2.setHasFixedSize(true)
        checkBoxList = ArrayList()
        checkBoxList.add(checkBox("Milk"))
        checkBoxList.add(checkBox("Fish"))
        checkBoxList.add(checkBox("Peanuts"))
        checkBoxList.add(checkBox("Wheat"))
        checkBoxList.add(checkBox("Sesame"))
        checkBoxList.add(checkBox("Eggs"))
        checkBoxList.add(checkBox("Tree nut"))
        checkBoxList.add(checkBox("Soybeans"))
        binding.rvCheckbox2.adapter = checkBoxAdapter(checkBoxList)
    }
    private fun rvVaccineallegeriesGrid(){
        binding.rvCheckbox3.setHasFixedSize(true)
        checkBoxList = ArrayList()
        checkBoxList.add(checkBox("Aspirin"))
        checkBoxList.add(checkBox("ibuprofen"))
        checkBoxList.add(checkBox("Mefenamic Acid"))
        checkBoxList.add(checkBox("Amoxicillin"))
        checkBoxList.add(checkBox("Penicillin"))
        binding.rvCheckbox3.adapter = checkBoxAdapter(checkBoxList)
        }

}