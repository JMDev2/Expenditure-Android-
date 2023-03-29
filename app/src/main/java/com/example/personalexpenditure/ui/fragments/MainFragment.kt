package com.example.personalexpenditure.ui.fragments

import android.annotation.SuppressLint
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

        home()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        setupOnBackPressedCallback()

        observeIncome()

    }

    @SuppressLint("SetTextI18n")
    private fun observeIncome() {
        viewModel.observeIncomeLiveData().observe(
            viewLifecycleOwner
        ) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    val total = response.data?.size
                    val response = response.data?.get(total!! - 1)
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.GONE

                    response?.let {
                        binding.constraint.visibility = View.VISIBLE
                        binding.income.text = response.income.toString()
                        binding.expenses.text = response.budget.toString()
                        openExpenses(response.id)

                    }
                }
                // if error state
                Status.ERROR -> {
                    // TODO Dismiss progress dialog
                    binding.progressBar.visibility = View.GONE
                    // TODO Show error message in dialog.
                    binding.errorText.visibility = View.VISIBLE
                    binding.errorText.text = "set income"
                }
                // if still loading
                Status.LOADING -> {
                    binding.constraint.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE

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
                    val action = MainFragmentDirections.actionMainFragmentToNewExpensesCategoryFragment()
                    findNavController().navigate(action)
                }
            hospital.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToNewExpensesCategoryFragment()
                findNavController().navigate(action)

            }
            transport.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToNewExpensesCategoryFragment()
                findNavController().navigate(action)

            }
            entertainment.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToNewExpensesCategoryFragment()
                findNavController().navigate(action)

            }
            education.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToNewExpensesCategoryFragment()
                findNavController().navigate(action)

            }
            food.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToNewExpensesCategoryFragment()
                findNavController().navigate(action)

            }
            miscellenious.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToNewExpensesCategoryFragment()
                findNavController().navigate(action)

            }
            glocery.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToNewExpensesCategoryFragment()
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

    private fun openExpenses(incomeId: Int) {
        binding.expenseLinearLayout.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToNewExpensesCategoryFragment(incomeId)
            findNavController().navigate(action)
        }

    }

}