package com.example.personalexpenditure.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.personalexpenditure.databinding.FragmentMainBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.personalexpenditure.utils.Status
import com.example.personalexpenditure.viewmodels.GetIncomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainFragment : Fragment() {
   // private lateinit var navController: NavController

    private lateinit var binding: FragmentMainBinding
    private val viewModel: GetIncomeViewModel by viewModels()
    private val args: MainFragmentArgs by navArgs()


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


        Log.d("MainFragment","checkexpenditureId:${args.expenditureId}")
        viewModel.getExpenditure(args.expenditureId.toString())

        viewModel.getIncome(args.incomeIdToHome.toString()) //call this when no init method in viewmodel
        Log.d("MainFragment","incomeIdToHome mainaF:${args.incomeIdToHome}")

        observeIncome()
        observeExpenditure()
        displayDate()

    }

    private fun displayDate() {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("d yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        binding.dateText.text = formattedDate
    }

    private fun observeExpenditure() {
        viewModel.observeExpenditureLiveData().observe(
            viewLifecycleOwner
        ) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                   val res = response.data
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.GONE

                    res?.let {
                        binding.constraint.visibility = View.VISIBLE
                        binding.expenses.text = res.total.toString()
                        binding.rentPercentage.text = res.rentPercentage.toString()
                        binding.foodPercentage.text = res.foodPercentage.toString()
                        binding.healthPercentage.text = res.healthPercentage.toString()
                        binding.entertainmentPercentage.text = res.entertainmentPercentage.toString()
                        binding.transportPercentage.text = res.transportPercentage.toString()
                        binding.feePercentage.text = res.schoolFeePercentage.toString()
                        binding.shoppingPercentage.text = res.shoppingPercentage.toString()
                        Log.d("MainFragment", "expendureId ${it.id}")
                        Log.d("MainFragment", "totalExpenditure ${it.total}")
                        Log.d("MainFragment", "fee ${it.foodPercentage}")

                    }
                }
                // if error state
                Status.ERROR -> {
                    // TODO Dismiss progress dialog
                    binding.progressBar.visibility = View.GONE
                    // TODO Show error message in dialog.
                    binding.constraint.visibility = View.GONE
                    binding.errorText.visibility = View.VISIBLE
                    binding.errorText.text = "set income"
                //    Toast.makeText(requireContext(), response.message, Toast.LENGTH_LONG)
//                        .show()
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


    private fun observeIncome() {
        viewModel.observeIncomeLiveData().observe(
            viewLifecycleOwner
        ) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                   // val total = response.data
                    val totalIncome = response.data

                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.GONE

                    totalIncome?.let {
                        binding.constraint.visibility = View.VISIBLE
                        binding.income.text = totalIncome.income.toString()
                        Log.d("MainFragment", "incomeId ${it.id}")
                        Log.d("MainFragment", "income ${it.income}")

                        openExpenses(totalIncome.id)

                    }
                }
                // if error state
                Status.ERROR -> {
                    // TODO Dismiss progress dialog
                    binding.progressBar.visibility = View.GONE
                    // TODO Show error message in dialog.
                    binding.constraint.visibility = View.GONE
                    binding.errorText.visibility = View.VISIBLE

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
                    val action = MainFragmentDirections.actionMainFragmentToTestCategoryFragment()
                    findNavController().navigate(action)
                }
            hospital.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToTestCategoryFragment()
                findNavController().navigate(action)

            }
            transport.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToTestCategoryFragment()
                findNavController().navigate(action)

            }
            entertainmentImage.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToTestCategoryFragment()
                findNavController().navigate(action)

            }
            education.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToTestCategoryFragment()
                findNavController().navigate(action)

            }
            food.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToTestCategoryFragment()
                findNavController().navigate(action)

            }

            glocery.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToTestCategoryFragment()
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
            val action = MainFragmentDirections.actionMainFragmentToTestCategoryFragment(incomeId)
            findNavController().navigate(action)
        }

    }

}