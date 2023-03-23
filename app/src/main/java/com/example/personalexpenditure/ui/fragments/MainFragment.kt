package com.example.personalexpenditure.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.personalexpenditure.R
import com.example.personalexpenditure.databinding.FragmentMainBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.personalexpenditure.utils.Status
import com.example.personalexpenditure.viewmodels.GetIncomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
   // private lateinit var navController: NavController

    private lateinit var binding: FragmentMainBinding
    private val viewModel: GetIncomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
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

        home()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        setupOnBackPressedCallback()

        observeIncome()

    }

    private fun observeIncome() {
        viewModel.observeIncomeLiveData().observe(
            viewLifecycleOwner
        ) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    val response = response.data?.get(0)


                    response?.let {
                        binding.income.text = response.income.toString()
                    }
                }
                // if error state
                Status.ERROR -> {
                    // TODO Dismiss progress dialog
                    // TODO Show error message in dialog.
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_LONG)
                        .show()
                }
                // if still loading
                Status.LOADING -> {
                    // TODO Show progress dialog
                }
            }
        }
    }

private fun setupOnBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Do nothing to prevent navigating back to the onboarding screen
            }
        })
    }


    private fun home() {
        binding.apply {
            home.setOnClickListener {
                    val action = MainFragmentDirections.actionMainFragmentToNewExpensesFragment()
                    findNavController().navigate(action)
                }
            hospital.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToNewExpensesFragment()
                findNavController().navigate(action)

            }
            transport.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToNewExpensesFragment()
                findNavController().navigate(action)

            }
            entertainment.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToNewExpensesFragment()
                findNavController().navigate(action)

            }
            education.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToNewExpensesFragment()
                findNavController().navigate(action)

            }
            food.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToNewExpensesFragment()
                findNavController().navigate(action)

            }
            miscellenious.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToNewExpensesFragment()
                findNavController().navigate(action)

            }
            glocery.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToNewExpensesFragment()
                findNavController().navigate(action)

            }
            userProfile.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToProfileFragment()
                findNavController().navigate(action)
            }
        }

        }




    private fun openIncome() {
        binding.incomeLinearLayout.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToNewIncomeFragment()
            findNavController().navigate(action)
        }

    }

    private fun openExpenses() {
        binding.expenseLinearLayout.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToNewExpensesFragment()
            findNavController().navigate(action)
        }

    }

}