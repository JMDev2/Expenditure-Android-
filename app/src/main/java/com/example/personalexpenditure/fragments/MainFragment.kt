package com.example.personalexpenditure.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.personalexpenditure.R
import com.example.personalexpenditure.databinding.FragmentMainBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        openIncome()
        openExpenses()

    }


    private fun openIncome() {
        binding.incomeLinearLayout.setOnClickListener {
            val newIncomeFragment = NewIncomeFragment()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, newIncomeFragment)
            transaction.commit()
//            val action = MainFragmentDirections.actionMainFragmentToNewIncomeFragment()
//            findNavController().navigate(R.id.action_mainFragment_to_newIncomeFragment)
        }

    }

    private fun openExpenses() {
        binding.expenseLinearLayout.setOnClickListener {
            val newExpensesFragment = NewExpensesFragment()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, newExpensesFragment)
            transaction.commit()
//            val action = MainFragmentDirections.actionMainFragmentToNewExpensesFragment()
//            requireView().findNavController().navigate(action)
        }

    }

}