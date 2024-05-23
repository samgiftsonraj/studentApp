package com.example.greatify.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.greatify.databinding.FragmentHomeBinding
import com.example.greatify.databinding.FragmentLearnBinding
import com.example.greatify.view.activities.TimeTableActivity

class LearnFragment : Fragment() {
    private  var _binding : FragmentLearnBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding  = FragmentLearnBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}