package com.example.personalexpenditure.ui.fragments

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.personalexpenditure.R
import com.example.personalexpenditure.databinding.FragmentNewIncomeBinding
import com.example.personalexpenditure.model.PostData
import com.example.personalexpenditure.repository.IncomeRepository
import com.example.personalexpenditure.utils.Resource
import com.example.personalexpenditure.utils.Status
import com.example.personalexpenditure.viewmodels.IncomePostViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class NewIncomeFragment : Fragment() {

    private lateinit var binding: FragmentNewIncomeBinding

    private val viewModel: IncomePostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewIncomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //actionbar
        activity?.setTitle((Html.fromHtml("<font color=\"#333333\">" + getString(R.string.newincome) + "</font>")));
        openBudgetFragment()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        postData()
        observeIncomePost()
        cancelBtn()
        displayDate()

    }

    /*
    display date
     */
    private fun displayDate() {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("d, EEEE, yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        binding.dateText.text = formattedDate
    }

    private fun cancelBtn() {
        binding.cancel.setOnClickListener {
            binding.setIncome.setText("")
        }
    }


    /*
    This method opens the budget fragment
     */
    private fun openBudgetFragment() {
        binding.setBudget.setOnClickListener {
            val newBudgetFragment = NewBudgetFragment()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, newBudgetFragment)
            transaction.addToBackStack("")
            transaction.commit()
        }
    }


    /*
    post income
     */
    private fun postData() {
        binding.setBudget.setOnClickListener {
            val income = binding.setIncome.text.toString()
            if (income.isNotBlank()) {
                //viewModel.postIncome(PostData(income))
                val action =
                    NewIncomeFragmentDirections.actionNewIncomeFragmentToNewBudgetFragment(income.toInt())
                requireView().findNavController().navigate(action)
            } else {

                Toast.makeText(requireContext(), "Input must not be null", Toast.LENGTH_LONG).show()

            }

        }
    }

    private fun observeIncomePost() {
        viewModel.incomeLiveData.observe(
            viewLifecycleOwner
        ){ response ->
            when (response.status) {
                Status.SUCCESS ->{
                    Toast.makeText(requireContext(), "saved", Toast.LENGTH_LONG).show()

                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_LONG)
                        .show()

                }
                Status.LOADING ->{

                }

            }
        }
    }


}