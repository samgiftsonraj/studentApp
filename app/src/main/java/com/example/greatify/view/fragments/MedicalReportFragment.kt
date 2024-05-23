package com.example.greatify.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.greatify.R
import com.example.greatify.databinding.FragmentMedicalReportBinding
import com.example.greatify.view.activities.MainActivity


class MedicalReportFragment : Fragment() {
    private var _binding: FragmentMedicalReportBinding? =null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMedicalReportBinding.inflate(inflater,container,false)
        return binding.root
    }


}