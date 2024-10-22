package com.example.dicodingevent.ui.upcoming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dicodingevent.R
import com.example.dicodingevent.databinding.FragmentUpcomingBinding


class UpcomingFragment : Fragment() {
    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = binding!!
    private val upcomingViewModel: UpcomingViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }

}